package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.dao.ProductDao;
import org.wooteco.pre.convenienceStore.domain.order.OrderItem;
import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.domain.product.ProductFactory;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;
import org.wooteco.pre.convenienceStore.dto.ProductsDto;
import org.wooteco.pre.convenienceStore.exception.CustomIllegalException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultProductService implements ProductService {
    private static final String NON_EXIST_PRODUCT = "존재하지 않는 상품입니다";
    private final ProductDao productDao;

    public DefaultProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void createProductData(List<String> productData, List<Promotion> promotions) {
        ProductFactory.createProductStorage(productDao, productData, promotions);
    }

    @Override
    public ProductsDto createProductsDto() {
        Map<String, List<Product>> products = productDao.findAll();
        return ProductsDto.createProductDtos(products);
    }

    @Override
    public List<Product> findProducts(final String productName) {
        List<Product> products = productDao.findProducts(productName);
        if (products == null || products.isEmpty()) {
            throw new CustomIllegalException(NON_EXIST_PRODUCT);
        }
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product selectHighPriorityProduct(final String productName) {
        List<Product> products = findProducts(productName);
        return getPriorityProduct(products);
    }

    @Override
    public int sumProductAllStock(final Product product) {
        return productDao.findProductAllStock(product.getName());
    }

    @Override
    public int getPromotionFreeQuantity(final String productName, int quantity) {
        Product product = selectHighPriorityProduct(productName);
        return product.calcPromotionFreeQuantity(quantity);
    }

    @Override
    public void reduceStock(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            List<Product> products = findProducts(orderItem.getProduct().getName());
            int remainQuantity = reducePriorityProductFirst(products, orderItem.getQuantity());
            reduceProducts(products, remainQuantity);
        }
    }

    private Product getPriorityProduct(final List<Product> products) {
        return products.stream()
                .filter(Product::isProductHasPromotion)
                .findFirst()
                .orElseGet(products::getFirst);
    }

    private int reducePriorityProductFirst(final List<Product> products, final int quantity) {
        Product priorityProduct = getPriorityProduct(products);
        if (!priorityProduct.isStockOut()) {
            return priorityProduct.decreaseStock(quantity);
        }
        return quantity;
    }

    private void reduceProducts(final List<Product> products, final int quantity) {
        int remainQuantity = quantity;
        for (Product product : products) {
            if (remainQuantity == 0) {
                return;
            }
            if (product.isStockOut()) {
                continue;
            }
            remainQuantity = product.decreaseStock(remainQuantity);
        }
    }
}
