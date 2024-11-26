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

    public boolean isSameProduct(final OrderItem orderItem) {
        return this.product.equals(orderItem.product);
    }

    public void addQuantity(final int productStock, final int quantity) {
        OrderItemValidator.validateStock(productStock, this.quantity + quantity);
        this.quantity += quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}
