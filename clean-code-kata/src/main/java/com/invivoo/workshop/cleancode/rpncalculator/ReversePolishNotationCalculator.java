package com.invivoo.workshop.cleancode.rpncalculator;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ReversePolishNotationCalculator {

    private static final String RPN_INPUT_SEPARATOR = " ";

    private ReversePolishNotationCalculator() {
    }

    static int computeFrom(String rpnInput) {
        Deque<Integer> numbers = new ArrayDeque<>();

        Arrays.stream(rpnInput.split(RPN_INPUT_SEPARATOR))
              .forEach(integerOrOperand -> numbers.push(getNextOrApplyOperand(numbers, integerOrOperand)));

        return numbers.pop();
    }

    private static Integer getNextOrApplyOperand(Deque<Integer> numbers, String integerOrOperand) {
        return RPNOperand.getOperandFromSign(integerOrOperand)
                         .map(op -> op.apply(numbers.pop(), numbers.pop()))
                         .orElseGet(() -> Integer.parseInt(integerOrOperand));
    }

}
