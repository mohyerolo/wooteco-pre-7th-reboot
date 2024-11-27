package org.wooteco.pre.convenienceStore.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.wooteco.pre.convenienceStore.constants.ErrorMessage;

class OrderServiceTest {

    private final OrderItemService orderItemService = new OrderItemService(new TestProductService());
    private final OrderService orderService = new OrderService(orderItemService);

    @ParameterizedTest
    @ValueSource(strings = {"[콜라-2],[오렌지주스-1", "[콜라#2][오렌지주스-1]", "[콜라#2],[오렌지주스#1]", "[콜라-0],[오렌지주스-1]"})
    void 주문_아이템_에러_실패(final String input) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderService.createOrder(input));
    }

    @Test
    void 하나_없는_상품_에러_실패() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderService.createOrder("[콜라-2],[사과-1]"))
                .withMessageContaining(ErrorMessage.NON_EXIST_PRODUCT.getMessage());

    }

    @Test
    void 하나_상품_재고_에러_실패() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderService.createOrder("[콜라-2],[오렌지주스-100]"))
                .withMessageContaining(ErrorMessage.EXCEEDED_STOCK.getMessage());

    }
}
