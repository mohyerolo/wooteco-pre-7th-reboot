package org.wooteco.pre.convenienceStore.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.wooteco.pre.convenienceStore.constants.ErrorMessage;
import org.wooteco.pre.convenienceStore.constants.Membership;
import org.wooteco.pre.convenienceStore.domain.order.Order;

class OrderServiceTest {

    private final OrderItemService orderItemService = new OrderItemService(new TestProductService());
    private final OrderService orderService = new OrderService(orderItemService);

    @ParameterizedTest
    @ValueSource(strings = {"[콜라-2],[오렌지주스-1", "[콜라#2][오렌지주스-1]", "[콜라#2],[오렌지주스#1]", "[콜라-0],[오렌지주스-1]"})
    void 주문_아이템_에러_실패(final String input) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderService.createOrder(input, Membership.DEFAULT));
    }

    @Test
    void 하나_없는_상품_에러_실패() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderService.createOrder("[콜라-2],[사과-1]", Membership.DEFAULT))
                .withMessageContaining(ErrorMessage.NON_EXIST_PRODUCT.getMessage());

    }

    @Test
    void 하나_상품_재고_에러_실패() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderService.createOrder("[콜라-2],[오렌지주스-100]", Membership.DEFAULT))
                .withMessageContaining(ErrorMessage.EXCEEDED_STOCK.getMessage());

    }

    @Test
    void 중복_처리_성공() {
        Order order = orderService.createOrder("[콜라-2],[오렌지주스-1],[콜라-5]", Membership.DEFAULT);
        Assertions.assertThat(order.getOrderItems().size()).isEqualTo(2);
        Assertions.assertThat(order.getOrderItems().getFirst().getQuantity()).isEqualTo(7);

    }
}
