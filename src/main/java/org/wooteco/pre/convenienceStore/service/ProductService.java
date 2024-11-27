package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.dto.ProductsDto;

import java.util.List;

public interface ProductService {
    ProductsDto createProductsDto();
    List<Product> findProducts(final String productName);
    Product selectHighPriorityProduct(final List<Product> products);
}
