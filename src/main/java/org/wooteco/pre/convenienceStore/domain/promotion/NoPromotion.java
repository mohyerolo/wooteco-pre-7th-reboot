package org.wooteco.pre.convenienceStore.domain.promotion;

import java.time.LocalDate;

public class NoPromotion extends Promotion {
    public NoPromotion() {
        super("null", 0, 0, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
