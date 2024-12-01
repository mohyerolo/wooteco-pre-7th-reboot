package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.domain.order.Order;
import org.wooteco.pre.convenienceStore.domain.order.OrderItem;
import org.wooteco.pre.convenienceStore.domain.order.UpdateOrderItem;
import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.util.OrderItemParser;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService {
    private final ProductService productService;

    public OrderItemService(final ProductService productService) {
        this.productService = productService;
    }

    public void createOrUpdateOrderItem(final String orderItemData, final Order order) {
        String[] splitInput = OrderItemParser.splitOrderItem(orderItemData);

        Product product = productService.selectHighPriorityProduct(splitInput[0]);
        int productAllStock = productService.sumProductAllStock(product);

        OrderItem newItem = OrderItem.of(product, productAllStock, Integer.parseInt(splitInput[1]));
        order.addOrUpdate(newItem, productAllStock);
    }

    public List<UpdateOrderItem> collectItemNeedUpdate(final List<OrderItem> orderItems) {
        List<UpdateOrderItem> updateOrderItems = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            proceedCollection(updateOrderItems, orderItem);
        }
        return updateOrderItems;
    }

    public List<OrderItem> findFreeItem(final List<OrderItem> orderItems) {
        List<OrderItem> freeItems = new ArrayList<>();
        orderItems.stream()
                .filter(OrderItem::isExistPromotion)
                .forEach(orderItem -> addFreeItem(freeItems, orderItem));
        return freeItems;
    }

    private void proceedCollection(final List<UpdateOrderItem> updateOrderItems, final OrderItem orderItem) {
        if (orderItem.isPromotionLack()) {
            addUnAvailableItem(updateOrderItems, orderItem);
            return;
        }
        addAddableItem(updateOrderItems, orderItem);
    }

    private void addUnAvailableItem(final List<UpdateOrderItem> updateOrderItems, final OrderItem orderItem) {
        updateOrderItems.add(getUnAvailableItem(orderItem));
    }

    private UpdateOrderItem getUnAvailableItem(final OrderItem orderItem) {
        int remainQuantity = orderItem.getRemainQuantity();
        return UpdateOrderItem.unavailableOf(orderItem, remainQuantity);
    }

    private void addAddableItem(final List<UpdateOrderItem> updateOrderItems, final OrderItem orderItem) {
        int addableQuantity = orderItem.getAddableQuantity();
        if (addableQuantity > 0) {
            updateOrderItems.add(UpdateOrderItem.addableOf(orderItem, addableQuantity));
        }
    }

    private void addFreeItem(final List<OrderItem> freeItems, final OrderItem orderItem) {
        int promotionFreeQuantity = productService.getPromotionFreeQuantity(orderItem.getProduct().getName(), orderItem.getQuantity());
        if (promotionFreeQuantity != 0) {
            freeItems.add(OrderItem.itemOf(orderItem, promotionFreeQuantity));
        }
    }

}
