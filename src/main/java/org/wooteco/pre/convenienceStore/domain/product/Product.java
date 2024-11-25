package org.wooteco.pre.convenienceStore.domain.product;

import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;
import org.wooteco.pre.convenienceStore.util.InputParser;

public class Product {
    private final String name;
    private final int price;
    private final int stock;
    private final Promotion promotion;

    private Product(final String name, final int price, final int stock, final Promotion promotion) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.promotion = promotion;
    }

    public static Product of(final String[] productData, final Promotion promotion) {
        String name = productData[0];
        int price = InputParser.parseStringToInt(productData[1]);
        int stock = InputParser.parseStringToInt(productData[2]);
        return new Product(name, price, stock, promotion);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
