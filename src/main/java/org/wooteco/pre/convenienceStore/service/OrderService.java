package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.constants.Membership;
import org.wooteco.pre.convenienceStore.domain.order.Order;
import org.wooteco.pre.convenienceStore.domain.order.OrderItem;
import org.wooteco.pre.convenienceStore.domain.order.Receipt;
import org.wooteco.pre.convenienceStore.domain.order.UpdateOrderItem;
import org.wooteco.pre.convenienceStore.util.InputParser;

import java.util.List;

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

    public List<UpdateOrderItem> getItemNeedUpdate(final Order order) {
        List<OrderItem> orderItems = order.getHavingPromotionItem();
        return orderItemService.collectItemNeedUpdate(orderItems);
    }

    public Receipt divideItems(final Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItem> freeItems = orderItemService.findFreeItem(orderItems);
        return new Receipt(order.getMembership(), orderItems, freeItems);
    }
}
