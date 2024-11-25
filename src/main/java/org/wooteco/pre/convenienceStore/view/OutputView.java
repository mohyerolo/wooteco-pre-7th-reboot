package org.wooteco.pre.convenienceStore.view;

import org.wooteco.pre.convenienceStore.constants.OutputMessage;
import org.wooteco.pre.convenienceStore.dto.ProductDto;
import org.wooteco.pre.convenienceStore.dto.ProductsDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public void printGreetings() {
        System.out.println(OutputMessage.GREETINGS.getMessage());
    }

    public void printPresentProductsStatus(final ProductsDto productsMap) {
        System.out.println(OutputMessage.PRESENT_PRODUCTS.getMessage());
        System.out.println();
        String productList = appendProductSentence(productsMap.productMap());
        System.out.print(productList);
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

    public String createBaseProductSentence(final ProductDto productDto) {
        return String.format(OutputMessage.PRODUCT_TEMPLATE.getMessage(),
                productDto.name(), productDto.price(), getStockMessage(productDto));
    }

    public String createPromotionSentence(final ProductDto productDto) {
        String baseSentence = createBaseProductSentence(productDto);
        return String.format(OutputMessage.PRODUCT_PROMOTION_TEMPLATE.getMessage() + "%n",
                baseSentence, productDto.promotionName());
    }

    private String getStockMessage(final ProductDto productDto) {
        if (productDto.isStockOut()) {
            return OutputMessage.PRODUCT_STOCK_OUT.getMessage();
        }
        return String.format(OutputMessage.PRODUCT_STOCK_TEMPLATE.getMessage(), productDto.stock());
    }

}
