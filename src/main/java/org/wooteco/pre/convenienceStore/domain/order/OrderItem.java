package org.wooteco.pre.convenienceStore.domain.order;

import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.util.InputParser;
import org.wooteco.pre.convenienceStore.validator.OrderItemValidator;

public class OrderItem {
    private final Product product;
    private final int quantity;

    private OrderItem(final Product product, final int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public static OrderItem of(final Product product, final int sameProductsStock, final String orderQuantity) {
        int quantity = InputParser.parseStringToInt(orderQuantity);
        OrderItemValidator.validateQuantity(quantity);
        OrderItemValidator.validateStock(sameProductsStock, quantity);
        return new OrderItem(product, quantity);
    }

}
