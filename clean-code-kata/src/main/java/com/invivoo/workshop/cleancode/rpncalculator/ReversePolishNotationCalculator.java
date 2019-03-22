package com.invivoo.workshop.cleancode.rpncalculator;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ReversePolishNotationCalculator {

    static final String RPN_INPUT_SEPARATOR = " ";

    static int calculate(String rpnInput) {
        Deque<Integer> inputDeque = new ArrayDeque<>();

        Arrays.stream(rpnInput.split(RPN_INPUT_SEPARATOR))
              .forEach(integerOrOperand -> pushResultOfOperationOrNewInteger(inputDeque, integerOrOperand));

        return inputDeque.pop();
    }

    private static void pushResultOfOperationOrNewInteger(Deque<Integer> inputDeque, String integerOrOperand) {
        inputDeque.push(RPNOperand.getOperandFromSign(integerOrOperand)
                                  .map(op -> op.getOperation().apply(inputDeque.pop(), inputDeque.pop()))
                                  .orElseGet(() -> Integer.parseInt(integerOrOperand)));
    }

}
