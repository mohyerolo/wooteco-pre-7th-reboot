package org.wooteco.pre.convenienceStore.util;

import org.wooteco.pre.convenienceStore.validator.InputValidator;
import org.wooteco.pre.convenienceStore.validator.OrderItemValidator;

public class OrderItemParser {
    private static final String ORDER_ITEM_REGEX = "^\\[|]$";
    private static final String DELIMITER = "-";

    public static String[] splitOrderItem(final String input) {
        String[] splitOrderItem = deleteWrapper(input).split(DELIMITER);
        validateSplitData(splitOrderItem);
        return splitOrderItem;
    }

    private static String deleteWrapper(final String input) {
        return input.replaceAll(ORDER_ITEM_REGEX, "");
    }

    private static void validateSplitData(final String[] splitInput) {
        OrderItemValidator.validateOrderItemField(splitInput.length);
        InputValidator.validateString(splitInput[0]);
        InputValidator.validateNumber(splitInput[1]);
    }

}
