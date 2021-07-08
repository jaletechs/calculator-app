package com.jaletechs.calculator.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    @Test
    void testNullExpressionThrowsException() {
        assertThrows(NullPointerException.class, () -> new Expression(null));
    }

    @Test
    void testExpressionEvaluatesCorrectly() {
        Expression expression = new Expression("2+3+3");
        Expression expression2 = new Expression("2+3-3");
        Expression expression3 = new Expression("5/2-3");

        assertEquals(8.0 , expression.evaluate());
        assertEquals(2.0 , expression2.evaluate());
        assertEquals(-0.5 , expression3.evaluate());
    }
}