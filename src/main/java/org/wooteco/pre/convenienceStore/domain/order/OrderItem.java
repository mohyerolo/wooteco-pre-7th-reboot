package org.wooteco.pre.convenienceStore.domain.order;

import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.validator.OrderItemValidator;

public class OrderItem {
    private final Product product;
    private int quantity;

    private OrderItem(final Product product, final int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public static OrderItem of(final Product product, final int productAllStock, final int quantity) {
        OrderItemValidator.validateStock(productAllStock, quantity);
        return new OrderItem(product, quantity);
    }

    public static OrderItem itemOf(final OrderItem orderItem, final int quantity) {
        return new OrderItem(orderItem.getProduct(), quantity);
    }

    public boolean isSameProduct(final OrderItem orderItem) {
        return this.product.equals(orderItem.product);
    }

    public void addQuantity(final int quantity) {
        this.quantity += quantity;
    }

    public void addQuantity(final int productStock, final int quantity) {
        OrderItemValidator.validateStock(productStock, this.quantity + quantity);
        this.quantity += quantity;
    }

    public void decreaseQuantity(final int quantity) {
        this.quantity -= quantity;
    }

    public boolean isExistPromotion() {
        return product.isAvailablePromotion();
    }

    public boolean isPromotionLack() {
        return product.isPromotionLack(quantity);
    }

    public int getRemainQuantity() {
        return quantity - product.calcPromotionQuantity(quantity);
    }

    public int getAddableQuantity() {
        return product.calcAddableQuantity(quantity);
    }

    public boolean exist() {
        return quantity != 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
