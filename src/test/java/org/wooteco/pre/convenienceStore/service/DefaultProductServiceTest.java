package org.wooteco.pre.convenienceStore.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.wooteco.pre.convenienceStore.dao.ProductDao;
import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.domain.promotion.NoPromotion;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DefaultProductServiceTest {
    private static final String NON_EXIST_PRODUCT = "존재하지 않는 상품입니다";

    private static DefaultProductService defaultProductService;
    private static Product cola;
    private static Product sprite;

    @BeforeAll
    static void setup() {
        ProductDao productDao = ProductDao.getInstance();
        defaultProductService = new DefaultProductService(productDao);

        String[] data = new String[]{"탄산2+1", "2", "1", "2024-11-19", String.valueOf(LocalDate.now())};
        Promotion promotion = Promotion.from(data);
        cola = Product.of(new String[]{"콜라", "1000", "10", "탄산2+1"}, promotion);
        Product colaNoPromotion = Product.of(new String[]{"콜라", "1000", "10", "null"}, new NoPromotion());
        sprite = Product.of(new String[]{"사이다", "1000", "10", "null"}, new NoPromotion());

        productDao.addProduct(cola);
        productDao.addProduct(colaNoPromotion);
        productDao.addProduct(sprite);
    }

    @Test
    void 같은_상품들_찾기() {
        List<Product> products = defaultProductService.findProducts(cola.getName());
        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void 상품중_프로모션상품_찾기() {
        List<Product> products = defaultProductService.findProducts(cola.getName());
        Product product = defaultProductService.selectHighPriorityProduct(products);
        assertThat(product).isEqualTo(cola);
    }

    @Test
    void 상품이_하나면_그걸로_가져오기() {
        List<Product> products = defaultProductService.findProducts(sprite.getName());
        Product product = defaultProductService.selectHighPriorityProduct(products);
        assertThat(product).isEqualTo(sprite);
    }

    @Test
    void 없는_상품_에러() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> defaultProductService.findProducts("없음"))
                .withMessageContaining(NON_EXIST_PRODUCT);
    }


}
