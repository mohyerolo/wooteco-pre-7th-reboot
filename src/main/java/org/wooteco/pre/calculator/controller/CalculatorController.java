package org.wooteco.pre.calculator.controller;

import org.wooteco.pre.calculator.domain.Numbers;
import org.wooteco.pre.calculator.service.InputService;
import org.wooteco.pre.calculator.view.InputView;
import org.wooteco.pre.calculator.view.OutputView;

public class CalculatorController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputService inputService;

    public CalculatorController(final InputView inputView, final OutputView outputView, final InputService inputService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputService = inputService;
    }

    public void start() {
        String inputData = inputView.readInput();
        Numbers numbers = inputService.createInput(inputData);
        int sum = numbers.sum();
        outputView.printResult(sum);
    }
}
