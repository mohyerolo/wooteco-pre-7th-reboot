package org.wooteco.pre.convenienceStore.domain.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wooteco.pre.convenienceStore.constants.Membership;
import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;

import java.time.LocalDate;

class OrderTest {

    private final Order order = new Order(Membership.DEFAULT);
    private Product cola;

    @BeforeEach
    void setup() {
        String[] colaData = new String[]{"콜라", "1000", "10", "탄산2+1"};
        String[] data = new String[]{"탄산2+1", "2", "1", "2024-11-19", String.valueOf(LocalDate.now())};
        Promotion promotion = Promotion.from(data);
        cola = Product.of(colaData, promotion);
    }

    @Test
    void 새로운_아이템_추가_성공() {
        OrderItem orderItem = OrderItem.of(cola, 10, 3);
        order.addOrUpdate(orderItem, 10);
        Assertions.assertThat(order.getOrderItems().size()).isEqualTo(1);
    }

    @Test
    void 중복_아이템_수량_합치기_성공() {
        OrderItem orderItem = OrderItem.of(cola, 10, 3);
        order.addOrUpdate(orderItem, 10);
        order.addOrUpdate(orderItem, 10);
        Assertions.assertThat(order.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(order.getOrderItems().getFirst().getQuantity()).isEqualTo(6);
    }
}
