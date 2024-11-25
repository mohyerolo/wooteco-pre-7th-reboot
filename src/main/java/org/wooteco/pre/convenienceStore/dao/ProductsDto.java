package org.wooteco.pre.convenienceStore.dao;

import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.dto.ProductDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record ProductsDto(Map<String, List<ProductDto>> productMap) {
    public static ProductsDto createProductDtos(final Map<String, List<Product>> products) {
        Map<String, List<ProductDto>> dtos = products.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        ProductsDto::getProductDtos,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
        return new ProductsDto(dtos);
    }

    private static List<ProductDto> getProductDtos(Map.Entry<String, List<Product>> entry) {
        return entry.getValue().stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }
}
