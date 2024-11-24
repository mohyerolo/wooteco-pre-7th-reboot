package org.wooteco.pre.convenienceStore.dao;

import org.wooteco.pre.convenienceStore.domain.product.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductDao {
    private static final ProductDao productDao = new ProductDao();

    private final Map<String, List<Product>> products;

    private ProductDao() {
        this.products = new LinkedHashMap<>();
    }

    public static ProductDao getInstance() {
        return productDao;
    }

    public void addProduct(final Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }
}
