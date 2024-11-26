package org.wooteco.pre.convenienceStore.dao;

import org.wooteco.pre.convenienceStore.domain.product.Product;

import java.util.*;

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

    public Map<String, List<Product>> findAll() {
        return Collections.unmodifiableMap(products);
    }

    public List<Product> findProducts(final String productName) {
        return this.products.get(productName);
    }
}
