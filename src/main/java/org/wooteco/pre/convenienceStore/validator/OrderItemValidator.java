package org.wooteco.pre.convenienceStore.validator;

import org.wooteco.pre.convenienceStore.constants.ErrorMessage;
import org.wooteco.pre.convenienceStore.exception.CustomIllegalException;

public class OrderItemValidator {
    private static final String ORDER_ITEM_REGEX = "^\\[.*]$";
    private static final int ORDER_FIELD = 2;

    public static void validateOrder(final String input) {
        if (!input.matches(ORDER_ITEM_REGEX)) {
            throw new CustomIllegalException(ErrorMessage.INPUT_TYPE_ERROR.getMessage());
        }
    }

    public static void validateOrderItemField(final int length) {
        if (length != ORDER_FIELD) {
            throw new CustomIllegalException(ErrorMessage.INPUT_TYPE_ERROR.getMessage());
        }
    }

    public static void validateStock(final int productAllStock, final int orderQuantity) {
        if (productAllStock < orderQuantity) {
            throw new CustomIllegalException(ErrorMessage.EXCEEDED_STOCK.getMessage());
        }
    }
}
