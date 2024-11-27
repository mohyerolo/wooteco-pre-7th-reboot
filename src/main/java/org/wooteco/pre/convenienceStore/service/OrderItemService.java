package org.wooteco.pre.convenienceStore.service;

import org.wooteco.pre.convenienceStore.domain.order.Order;
import org.wooteco.pre.convenienceStore.domain.order.OrderItem;
import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.util.OrderItemParser;

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

}
