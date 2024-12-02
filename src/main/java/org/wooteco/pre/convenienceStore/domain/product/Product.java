package org.wooteco.pre.convenienceStore.domain.product;

import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;
import org.wooteco.pre.convenienceStore.util.InputParser;

public class Product {
    private final String name;
    private final int price;
    private int stock;
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

    public boolean isProductHasPromotion() {
        return promotion.isPromotionExists();
    }

    public boolean isAvailablePromotion() {
        return promotion.isPromotionExists() && promotion.isAvailable();
    }

    public boolean isPromotionLack(final int quantity) {
        return !isAvailablePromotion() || (stock < quantity + promotion.calcAddableQuantity(quantity));
    }

    public int calcPromotionQuantity(final int quantity) {
        return promotion.calcPromotionStock(stock, quantity);
    }

    public int calcPromotionFreeQuantity(final int quantity) {
        return promotion.calcPromotionFree(stock, quantity);
    }

    public int calcAddableQuantity(final int quantity) {
        return promotion.calcAddableQuantity(quantity);
    }

    public int decreaseStock(final int quantity) {
        int decreaseQuantity = Math.min(stock, quantity);
        stock -= decreaseQuantity;
        return quantity - decreaseQuantity;
    }

    public boolean isStockOut() {
        return stock == 0;
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
