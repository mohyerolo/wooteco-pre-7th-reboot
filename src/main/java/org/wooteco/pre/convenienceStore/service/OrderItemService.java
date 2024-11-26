package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.domain.order.OrderItem;
import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.util.OrderItemParser;
import org.wooteco.pre.convenienceStore.validator.OrderItemValidator;

import java.util.List;

public class OrderItemService {
    private final ProductService productService;

    public OrderItemService(final ProductService productService) {
        this.productService = productService;
    }

    public OrderItem createOrderItem(final String orderItem) {
        OrderItemValidator.validateOrder(orderItem);
        String[] splitInput = OrderItemParser.splitOrderItem(orderItem);

        List<Product> products = productService.findProducts(splitInput[0]);
        Product product = productService.selectHighPriorityProduct(products);
        int sameProductStock = products.stream().mapToInt(Product::getStock).sum();

        return OrderItem.of(product, sameProductStock, splitInput[1]);
    }


}
