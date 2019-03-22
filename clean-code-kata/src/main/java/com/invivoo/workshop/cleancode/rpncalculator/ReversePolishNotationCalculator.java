package com.invivoo.workshop.cleancode.rpncalculator;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ReversePolishNotationCalculator {

    private static final String RPN_INPUT_SEPARATOR = " ";

    private ReversePolishNotationCalculator() {
    }

    static int computeFrom(String rpnInput) {
        Deque<Integer> inputDeque = new ArrayDeque<>();

        Arrays.stream(rpnInput.split(RPN_INPUT_SEPARATOR))
              .forEach(integerOrOperand -> inputDeque.push(getNextOrApplyOperand(inputDeque, integerOrOperand)));

        return inputDeque.pop();
    }

    private static Integer getNextOrApplyOperand(Deque<Integer> inputDeque, String integerOrOperand) {
        return RPNOperand.getOperandFromSign(integerOrOperand)
                         .map(op -> op.apply(inputDeque.pop(), inputDeque.pop()))
                         .orElseGet(() -> Integer.parseInt(integerOrOperand));
    }

}
