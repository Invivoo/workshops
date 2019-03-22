package com.invivoo.workshop.cleancode.rpncalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReversePolishNotationCalculatorTest {

    @Test
    public void should_return_1_for_input_1() {
        // Given
        String input = "1";
        int expectedResult = 1;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_return_2_for_input_2() {
        // Given
        String input = "2";
        int expectedResult = 2;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_return_3_for_input_1_2_add() {
        // Given
        String input = "1 2 +";
        int expectedResult = 3;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_return_6_for_input_3_2_ADD_1_ADD() {
        // Given
        String input = "3 2 + 1 +";
        int expectedResult = 6;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_return_6_for_input_3_2_2_ADD_ADD_1_SUB() {
        // Given
        String input = "3 2 2 + + 1 -";
        int expectedResult = 6;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_return_5_for_input_3_2_ADD() {
        // Given
        String input = "3 2 +";
        int expectedResult = 5;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_compute_6_for_input_3_2_ADD_1_ADD() {
        // Given
        String input = "3 2 + 1 +";
        int expectedResult = 6;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_compute_7_for_input_3_2_2_ADD_ADD() {
        // Given
        String input = "3 2 2 + +";
        int expectedResult = 7;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_compute_6_for_input_3_2_2_ADD_ADD_1_SUB() {
        // Given
        String input = "3 2 2 + + 1 -";
        int expectedResult = 6;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_compute_4_for_input_20_5_DIV() {
        // Given
        String input = "20 5 /";
        int expectedResult = 4;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_compute_100_for_input_20_5_MULT() {
        // Given
        String input = "20 5 *";
        int expectedResult = 100;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_compute_3_for_input_4_2_ADD_3_SUB() {
        // Given
        String input = "4 2 + 3 -";
        int expectedResult = 3;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

    @Test
    public void should_compute_3_for_input_3_5_8_MULT_7_ADD_MULT() {
        // Given
        String input = "3 5 8 * 7 + *";
        int expectedResult = 141;

        // When
        int result = ReversePolishNotationCalculator.calculate(input);

        // Then
        assertEquals(result, expectedResult);
    }

}