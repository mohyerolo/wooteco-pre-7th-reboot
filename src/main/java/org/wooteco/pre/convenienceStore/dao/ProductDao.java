package org.wooteco.pre.convenienceStore.dao;

import org.wooteco.pre.convenienceStore.domain.product.Product;

import java.util.*;

public class ProductDao {
    private final Map<String, List<Product>> products;

    public ProductDao() {
        this.products = new LinkedHashMap<>();
    }

    public void addProduct(final Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public Map<String, List<Product>> findAll() {
        return Collections.unmodifiableMap(products);
    }

    public List<Product> findProducts(final String productName) {
        return products.get(productName);
    }

    public int findProductAllStock(final String productName) {
        return products.get(productName).stream().mapToInt(Product::getStock).sum();
    }
}
