package org.wooteco.pre.convenienceStore.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.wooteco.pre.convenienceStore.constants.ErrorMessage;
import org.wooteco.pre.convenienceStore.domain.order.OrderItem;

class OrderItemServiceTest {

    private final ProductService productService = new TestProductService();
    private final OrderItemService orderItemService = new OrderItemService(productService);

    @ParameterizedTest
    @ValueSource(strings = {"[콜라-2]", "[콜라-20]"})
    void 주문_아이템_하나_생성_성공() {
        String orderItem = "[콜라-2]";
        Assertions.assertThat(orderItemService.createOrderItem(orderItem))
                .isExactlyInstanceOf(OrderItem.class);
    }

    @Test
    void 주문_아이템_없어서_실패() {
        String orderItem = "[사자-1]";
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderItemService.createOrderItem(orderItem))
                .withMessageContaining(ErrorMessage.NON_EXIST_PRODUCT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"[콜라-2", "[ ]", "콜라-2", "{콜라-2}"})
    void 주문_형식_잘못돼서_실패(final String input) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderItemService.createOrderItem(input))
                .withMessageContaining(ErrorMessage.INPUT_TYPE_ERROR.getMessage());
    }

    @Test
    void 주문_수량_0이하() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderItemService.createOrderItem("[콜라-0]"))
                .withMessageContaining(ErrorMessage.INPUT_TYPE_ERROR.getMessage());
    }

    @Test
    void 주문_상품_수량_초과() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> orderItemService.createOrderItem("[콜라-30]"))
                .withMessageContaining(ErrorMessage.EXCEEDED_STOCK.getMessage());
    }


}
