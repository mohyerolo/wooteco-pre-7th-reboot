package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.constants.Membership;
import org.wooteco.pre.convenienceStore.domain.order.Order;
import org.wooteco.pre.convenienceStore.util.InputParser;

public class OrderService {

    private final OrderItemService orderItemService;

    public OrderService(final OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    public Order createOrder(final String input, final Membership membership) {
        Order order = new Order(membership);
        String[] splitInput = InputParser.splitOrderInput(input);
        for (String splitData : splitInput) {
            orderItemService.createOrUpdateOrderItem(splitData, order);
        }
        return order;
    }


}
