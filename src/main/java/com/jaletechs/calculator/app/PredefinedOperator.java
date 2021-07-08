package com.jaletechs.calculator.app;

/**
 * PredefinedOperator defines the mathematical operators currently supported.
 */
public enum PredefinedOperator {
    DIVISION("/"),
    MULTIPLICATION("*"),
    ADDITION("+"),
    SUBTRACTION("-");

    private final String symbol;

    PredefinedOperator(String symbol) {
        this.symbol = symbol;
    }

    public static PredefinedOperator getOperatorWithHighestPrecedence(String expression) {
        if (expression.contains("/")) {
            return DIVISION;
        }
        if (expression.contains("*")) {
            return MULTIPLICATION;
        }
        if (expression.contains("+")) {
            return ADDITION;
        }
        if (expression.contains("-")) {
            return SUBTRACTION;
        }

        throw new RuntimeException("Expression has no more operators");
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.format("Operator: %s", symbol);
    }
}
