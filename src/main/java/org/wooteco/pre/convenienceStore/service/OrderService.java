package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.constants.Membership;
import org.wooteco.pre.convenienceStore.domain.order.Order;
import org.wooteco.pre.convenienceStore.domain.order.OrderItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private static final String DELIMITER = ",";
    private final OrderItemService orderItemService;

    public OrderService(final OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    public Order createOrder(final String input) {
        String[] splitInput = splitInput(input);
        List<OrderItem> orderItems = Arrays.stream(splitInput)
                .map(orderItemService::createOrderItem)
                .collect(Collectors.toList());
        return new Order(orderItems, Membership.DEFAULT);
    }

    private String[] splitInput(final String input) {
        return input.split(DELIMITER);
    }
}
