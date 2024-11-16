package org.wooteco.pre.calculator;

import org.wooteco.pre.calculator.controller.CalculatorController;
import org.wooteco.pre.calculator.view.InputView;
import org.wooteco.pre.calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new CalculatorController(new InputView(), new OutputView()).start();
    }
}
