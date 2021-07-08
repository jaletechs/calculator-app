package com.jaletechs.calculator.app;

import com.jaletechs.calculator.app.util.Tuple;

import java.util.Objects;

/**
 * Expression is a class that models a multi-operand mathematical expression.
 * This is the entry point to the calculator and is evaluated into a final result.
 */
public class Expression {

    private String expression;

    public Expression(String expression) {
        expression = Objects.requireNonNull(expression);
        expression = removeWhiteSpaces(expression);
        this.expression = expression;
    }

    public Double evaluate() {
        while (containsOperator()) {
            evaluateOperatorsInExpression();
            if (finalResultIsNegative()) {
                break;
            }
            PredefinedOperator highest = PredefinedOperator.getOperatorWithHighestPrecedence(expression);
            SubExpression subExpression = extractSubExpression(highest);
            evaluateSubExpressionInExpression(subExpression);
        }

        return getResult();
    }

    @Override
    public String toString() {
        return expression;
    }

    private boolean finalResultIsNegative() {
        return expression.charAt(0) == '-' && getNoOfOperators() == 1;
    }

    private int getNoOfOperators() {
        int operatorCount = 0;
        for (char c : this.expression.toCharArray()) {
            if (isOperator(c)) {
                operatorCount++;
            }
        }
        return operatorCount;
    }

    private void evaluateOperatorsInExpression() {
        this.expression = this.expression
            .replaceAll("\\+-", "-")
            .replaceAll("-\\+", "-")
            .replaceAll("--", "\\+");
    }

    private void evaluateSubExpressionInExpression(SubExpression subExpression) {
        double result = subExpression.evaluate();
        String symbol = subExpression.getOperator().getSymbol();

        int operatorIndex = expression.indexOf(symbol);
        StringBuilder subString = new StringBuilder();

        for (int i = operatorIndex - 1; i >= 0; i--) {
            if (isOperator(expression.charAt(i))) {
                break;
            }
            subString.append(expression.charAt(i));
        }
        subString.reverse();
        subString.append(symbol);
        for (int i = operatorIndex + 1; i < expression.length(); i++) {
            if ((expression.charAt(operatorIndex) != '*' && expression.charAt(operatorIndex) != '/') || i != (operatorIndex + 1)) {
                if (isOperator(expression.charAt(i))) {
                    break;
                }
            }
            subString.append(expression.charAt(i));
        }

        this.expression = expression.replace(subString.toString(), Double.toString(result));
    }

    private Double getResult() {
        return Double.parseDouble(expression);
    }

    private String removeWhiteSpaces(String expression) {
        return expression.replaceAll(" ", "").trim();
    }

    private boolean containsOperator() {
        boolean containsOperator = false;
        for (char c : expression.toCharArray()) {
            if (c == '*' || c == '/' || c == '+' || c == '-') {
                containsOperator = true;
                break;
            }
        }
        return containsOperator;
    }

    private SubExpression extractSubExpression(PredefinedOperator operator) {
        Tuple<Double,Double> operands = extractOperands(operator.getSymbol().charAt(0));

        return SubExpression.builder()
            .operator(operator)
            .firstOperand(Operand.builder()
                .value(operands._1())
                .build())
            .secondOperand(Operand.builder()
                .value(operands._2())
                .build())
            .build();
    }

    private Tuple<Double,Double> extractOperands(char operator) {
        int operatorIndex = expression.indexOf(operator);
        StringBuilder firstOperandString = new StringBuilder();
        StringBuilder secondOperandString = new StringBuilder();

        for (int i = operatorIndex - 1; i >= 0; i--) {
            if (isOperator(expression.charAt(i))) {
                break;
            }
            firstOperandString.append(expression.charAt(i));
        }
        firstOperandString.reverse();

        for (int i = operatorIndex + 1; i < expression.length(); i++) {
            if ((expression.charAt(operatorIndex) != '*' && expression.charAt(operatorIndex) != '/') || i != (operatorIndex + 1)) {
                if (isOperator(expression.charAt(i))) {
                    break;
                }
            }
            secondOperandString.append(expression.charAt(i));
        }

        double firstOperand = Double.parseDouble(firstOperandString.toString());
        double secondOperand = Double.parseDouble(secondOperandString.toString());

        return new Tuple<>(firstOperand, secondOperand);
    }

    private boolean isOperator(char c) {
        return c == '*' || c == '/' || c == '+' || c == '-';
    }
}
