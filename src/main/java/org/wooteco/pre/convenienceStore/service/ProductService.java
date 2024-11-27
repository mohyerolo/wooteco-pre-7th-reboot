package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;
import org.wooteco.pre.convenienceStore.dto.ProductsDto;

import java.util.List;

public interface ProductService {
    void createProductData(final List<String> productData, final List<Promotion> promotions);

    ProductsDto createProductsDto();

    List<Product> findProducts(final String productName);

    Product selectHighPriorityProduct(final String productName);

    int sumProductAllStock(final Product product);
}
