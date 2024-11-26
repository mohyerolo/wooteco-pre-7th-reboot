package org.wooteco.pre.convenienceStore.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wooteco.pre.convenienceStore.domain.product.Product;
import org.wooteco.pre.convenienceStore.domain.promotion.NoPromotion;
import org.wooteco.pre.convenienceStore.domain.promotion.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

class ProductDaoTest {

    private ProductDao productDao;
    private Promotion promotion;
    private Map<String, List<Product>> all;

    @BeforeEach
    void setup() {
        productDao = ProductDao.getInstance();
        all = productDao.findAll();
        String[] data = new String[]{"탄산2+1", "2", "1", "2024-11-19", String.valueOf(LocalDate.now())};
        promotion = Promotion.from(data);
    }

    @Test
    void 데이터_추가_성공() {
        Assertions.assertThat(all.size()).isEqualTo(0);

        testColaAdd();
        testColaAdd2();
        testSpriteAdd();
    }

    private void testColaAdd() {
        Product cola = Product.of(new String[]{"콜라", "1000", "10", "탄산2+1"}, promotion);
        productDao.addProduct(cola);
        Assertions.assertThat(all.size()).isEqualTo(1);
        Assertions.assertThat(all.get("콜라").size()).isEqualTo(1);
    }

    private void testColaAdd2() {
        Product colaNoPromotion = Product.of(new String[]{"콜라", "1000", "10", "null"}, new NoPromotion());
        productDao.addProduct(colaNoPromotion);
        Assertions.assertThat(all.size()).isEqualTo(1);
        Assertions.assertThat(all.get("콜라").size()).isEqualTo(2);
    }

    private void testSpriteAdd() {
        Product sprite = Product.of(new String[]{"사이다", "1000", "10", "탄산2+1"}, promotion);
        productDao.addProduct(sprite);
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all.get("사이다").size()).isEqualTo(1);
    }

}
