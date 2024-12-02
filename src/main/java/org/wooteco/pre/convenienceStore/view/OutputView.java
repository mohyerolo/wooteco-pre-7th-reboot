package org.wooteco.pre.convenienceStore.view;

import org.wooteco.pre.convenienceStore.constants.OutputMessage;
import org.wooteco.pre.convenienceStore.dto.OrderItemDto;
import org.wooteco.pre.convenienceStore.dto.ProductDto;
import org.wooteco.pre.convenienceStore.dto.ProductsDto;
import org.wooteco.pre.convenienceStore.dto.ReceiptDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.wooteco.pre.convenienceStore.constants.OutputMessage.*;

public class OutputView {
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }

    public void printGreetings() {
        System.out.println(OutputMessage.GREETINGS.getMessage());
    }

    public void printPresentProductsStatus(final ProductsDto productsMap) {
        System.out.println(PRESENT_PRODUCTS.getMessage());
        System.out.println();
        String productList = appendProductSentence(productsMap.productMap());
        System.out.println(productList);
    }

    public void printReceipt(final ReceiptDto receiptDto) {
        System.out.println(RECEIPT_START.getMessage());
        System.out.println(RECEIPT_COL.getMessage());
        System.out.print(appendOrderList(receiptDto.getOrderItems()));
        System.out.println(RECEIPT_PROMOTION.getMessage());
        System.out.print(appendPromotionList(receiptDto.getFreeItems()));
        System.out.println(RECEIPT_DIVIDER.getMessage());
        System.out.printf(String.format(RECEIPT_TOTAL.getMessage() + "%n", receiptDto.getTotalQuantity(), receiptDto.getTotalPrice()));
        System.out.printf(String.format(RECEIPT_PROMOTION_DISCOUNT.getMessage() + "%n", receiptDto.getPromotionDiscount()));
        System.out.printf(String.format(RECEIPT_MEMBERSHIP_DISCOUNT.getMessage() + "%n", receiptDto.getMembershipDiscount()));
        System.out.printf(String.format(RECEIPT_REAL_AMOUNT.getMessage() + "%n", receiptDto.getRealAmount()));
    }

    private String appendProductSentence(final Map<String, List<ProductDto>> productsMap) {
        return productsMap.values().stream()
                .map(this::processProducts)
                .collect(Collectors.joining());
    }

    private String processProducts(final List<ProductDto> products) {
        StringBuilder sb = new StringBuilder();
        appendPromotionProduct(products, sb);
        products.stream()
                .filter(productDto -> !productDto.isPromotionExists())
                .forEach(productDto ->
                        sb.append(createBaseProductSentence(productDto)).append('\n')
                );
        return sb.toString();
    }

    private void appendPromotionProduct(final List<ProductDto> products, final StringBuilder sb) {
        products.stream()
                .filter(ProductDto::isPromotionExists)
                .findFirst()
                .ifPresent(promotionProduct -> sb.append(createPromotionSentence(promotionProduct)));
    }

    private String createBaseProductSentence(final ProductDto productDto) {
        return String.format(PRODUCT_TEMPLATE.getMessage(),
                productDto.name(), productDto.price(), getStockMessage(productDto));
    }

    private String createPromotionSentence(final ProductDto productDto) {
        String baseSentence = createBaseProductSentence(productDto);
        return String.format(PRODUCT_PROMOTION_TEMPLATE.getMessage() + "%n",
                baseSentence, productDto.promotionName());
    }

    private String getStockMessage(final ProductDto productDto) {
        if (productDto.isStockOut()) {
            return PRODUCT_STOCK_OUT.getMessage();
        }
        return String.format(PRODUCT_STOCK_TEMPLATE.getMessage(), productDto.stock());
    }

    private String appendOrderList(final List<OrderItemDto> orderItems) {
        StringBuilder sb = new StringBuilder();
        orderItems.forEach(orderItemDto -> sb.append(formatProduct(orderItemDto)));
        return sb.toString();
    }

    private String formatProduct(final OrderItemDto orderItemDto) {
        return String.format(RECEIPT_PRODUCT.getMessage() + "%n", orderItemDto.getName(), orderItemDto.getQuantity(), orderItemDto.getTotalPrice());
    }

    private String appendPromotionList(final List<OrderItemDto> freeItems) {
        StringBuilder sb = new StringBuilder();
        freeItems.forEach(freeItem -> sb.append(formatPromotionProduct(freeItem)));
        return sb.toString();
    }

    private String formatPromotionProduct(final OrderItemDto orderItemDto) {
        return String.format(RECEIPT_PROMOTION_PRODUCT.getMessage() + "%n", orderItemDto.getName(), orderItemDto.getQuantity());
    }
}
