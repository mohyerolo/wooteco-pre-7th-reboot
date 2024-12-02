package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.domain.order.OrderItem;
import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.domain.promotion.NoPromotion;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;
import org.wooteco.pre.convenienceStore.dto.ProductsDto;
import org.wooteco.pre.convenienceStore.exception.CustomIllegalException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestProductService implements ProductService {
    private static final String NON_EXIST_PRODUCT = "존재하지 않는 상품입니다";
    private final List<Product> products;

    public TestProductService() {
        String[] colaData = new String[]{"콜라", "1000", "10", "탄산2+1"};
        String[] orangeData = new String[]{"오렌지주스", "1000", "10", "null"};
        String[] promotionData = new String[]{"탄산2+1", "2", "1", "2024-11-19", String.valueOf(LocalDate.now())};
        Promotion promotion = Promotion.from(promotionData);

        Product cola = Product.of(colaData, promotion);
        Product cola2 = Product.of(colaData, new NoPromotion());
        Product orange = Product.of(orangeData, new NoPromotion());

        products = new ArrayList<>(List.of(cola, cola2, orange));
    }

    @Override
    public void createProductData(List<String> productData, List<Promotion> promotions) {
    }

    @Override
    public ProductsDto createProductsDto() {
        return null;
    }

    @Override
    public List<Product> findProducts(final String productName) {
        List<Product> productList = this.products.stream().filter(product -> product.getName().equals(productName)).toList();
        if (productList.isEmpty()) {
            throw new CustomIllegalException(NON_EXIST_PRODUCT);
        }
        return productList;
    }

    @Override
    public Product selectHighPriorityProduct(final String productName) {
        List<Product> products = findProducts(productName);
        return products.stream()
                .filter(Product::isProductHasPromotion)
                .findFirst()
                .orElseGet(products::getFirst);
    }

    @Override
    public int sumProductAllStock(final Product product) {
        List<Product> products1 = findProducts(product.getName());
        return products1.stream().mapToInt(Product::getStock).sum();
    }

    @Override
    public int getPromotionFreeQuantity(final String productName, final int quantity) {
        Product product = selectHighPriorityProduct(productName);
        return product.calcPromotionFreeQuantity(quantity);
    }

    @Override
    public void reduceStock(final List<OrderItem> orderItems) {

    }
}
