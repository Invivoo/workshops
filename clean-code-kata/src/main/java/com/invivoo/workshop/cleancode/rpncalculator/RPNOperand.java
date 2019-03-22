package com.invivoo.workshop.cleancode.rpncalculator;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public enum RPNOperand {

    ADD("+", (a, b) -> b + a),
    SUB("-", (a, b) -> b - a),
    MULT("*", (a, b) -> b * a),
    DIV("/", (a, b) -> b / a);

    RPNOperand(String operand, BinaryOperator<Integer> operation) {
        this.operand = operand;
        this.operation = operation;
    }

    private String operand;
    private BiFunction<Integer, Integer, Integer> operation;

    public static Optional<RPNOperand> getOperandFromSign(String operand) {
        return Arrays.stream(RPNOperand.values())
                     .filter(op -> op.getOperand().equals(operand))
                     .findFirst();
    }

    public String getOperand() {
        return operand;
    }

    public BiFunction<Integer, Integer, Integer> getOperation() {
        return operation;
    }

}
