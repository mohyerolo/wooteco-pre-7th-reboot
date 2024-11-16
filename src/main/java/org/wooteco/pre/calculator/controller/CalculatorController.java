package org.wooteco.pre.calculator.controller;

import org.wooteco.pre.calculator.domain.Input;
import org.wooteco.pre.calculator.view.InputView;
import org.wooteco.pre.calculator.view.OutputView;

public class CalculatorController {
    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        String inputData = inputView.readInput();
        Input input = new Input(inputData);
        
    }
}
