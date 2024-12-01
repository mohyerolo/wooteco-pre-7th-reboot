package org.wooteco.pre.convenienceStore.domain.order;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Receipt {
    private final Map<String, List<OrderItem>> orderItems;

    public Receipt(final Map<String, List<OrderItem>> orderItems) {
        this.orderItems = orderItems;
    }

    public static Receipt of(final List<OrderItem> orderItems, final List<OrderItem> freeItems) {
        Map<String, List<OrderItem>> items = Stream.concat(orderItems.stream(), freeItems.stream())
                .collect(Collectors.groupingBy(
                        orderItem -> orderItem.getProduct().getName(),
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
        return new Receipt(items);
    }

}
