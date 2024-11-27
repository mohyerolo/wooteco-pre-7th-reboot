package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.dao.ProductDao;
import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.dto.ProductsDto;
import org.wooteco.pre.convenienceStore.exception.CustomIllegalException;

import java.util.List;
import java.util.Map;

public class DefaultProductService implements ProductService {
    private static final String NON_EXIST_PRODUCT = "존재하지 않는 상품입니다";
    private final ProductDao productDao;

    public DefaultProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductsDto createProductsDto() {
        Map<String, List<Product>> products = ProductDao.getInstance().findAll();
        return ProductsDto.createProductDtos(products);
    }

    @Override
    public List<Product> findProducts(final String productName) {
        List<Product> products = productDao.findProducts(productName);
        if (products == null || products.isEmpty()) {
            throw new CustomIllegalException(NON_EXIST_PRODUCT);
        }
        return products;
    }

    @Override
    public Product selectHighPriorityProduct(final List<Product> products) {
        return products.stream()
                .filter(Product::isProductHasPromotion)
                .findFirst()
                .orElseGet(products::getFirst);
    }
}
