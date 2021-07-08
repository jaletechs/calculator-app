package com.jaletechs.calculator.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubExpressionTest {

    private Operand first;
    private Operand second;

    @BeforeAll
    public void setUp() {
        first = Operand.builder()
            .value(4)
            .build();
        second = Operand.builder()
            .value(2)
            .build();
    }

    @Test
    void testSubExpressionClassIsInstantiatedProperly() {
        SubExpression expression = SubExpression.builder()
            .firstOperand(first)
            .secondOperand(second)
            .operator(PredefinedOperator.ADDITION)
            .build();

        SubExpression expression2 = SubExpression.builder()
            .firstOperand(first)
            .secondOperand(second)
            .operator(PredefinedOperator.SUBTRACTION)
            .build();

        SubExpression expression3 = SubExpression.builder()
            .firstOperand(first)
            .secondOperand(second)
            .operator(PredefinedOperator.MULTIPLICATION)
            .build();

        SubExpression expression4 = SubExpression.builder()
            .firstOperand(first)
            .secondOperand(second)
            .operator(PredefinedOperator.DIVISION)
            .build();

        assertEquals("4.000000+2.000000", expression.toString());
        assertEquals("4.000000-2.000000", expression2.toString());
        assertEquals("4.000000*2.000000", expression3.toString());
        assertEquals("4.000000/2.000000", expression4.toString());
    }

    @Test
    void testSubExpressionComputesCorrectly() {
        SubExpression expression = SubExpression.builder()
            .firstOperand(first)
            .secondOperand(second)
            .operator(PredefinedOperator.ADDITION)
            .build();

        SubExpression expression2 = SubExpression.builder()
            .firstOperand(first)
            .secondOperand(second)
            .operator(PredefinedOperator.SUBTRACTION)
            .build();

        SubExpression expression3 = SubExpression.builder()
            .firstOperand(first)
            .secondOperand(second)
            .operator(PredefinedOperator.MULTIPLICATION)
            .build();

        SubExpression expression4 = SubExpression.builder()
            .firstOperand(first)
            .secondOperand(second)
            .operator(PredefinedOperator.DIVISION)
            .build();

        assertEquals(6.000000, expression.evaluate());
        assertEquals(2.000000, expression2.evaluate());
        assertEquals(8.000000, expression3.evaluate());
        assertEquals(2.000000, expression4.evaluate());
    }
}