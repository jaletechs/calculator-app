package com.jaletechs.calculator.app;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * SubExpression is a mathematical expression that has 2 operands and an operator
 * This can be evaluated independently of the other parts of a multi-operand expression.
 */
@Data
@Getter
@Setter
@Builder(toBuilder = true)
public class SubExpression {

    private Operand firstOperand;
    private Operand secondOperand;
    private PredefinedOperator operator;

    public SubExpression(
            Operand firstOperand,
            Operand secondOperand,
            PredefinedOperator operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    public double evaluate() {
        double first = firstOperand.getValue();
        double second = secondOperand.getValue();

        double result = 0;
        if (operator.equals(PredefinedOperator.DIVISION)) {
            result = first/second;
        } else if (operator.equals(PredefinedOperator.MULTIPLICATION)) {
            result = first * second;
        } else if (operator.equals(PredefinedOperator.ADDITION)) {
            result = first + second;
        } else if (operator.equals(PredefinedOperator.SUBTRACTION)) {
            result = first - second;
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format(
            "%f%s%f",
            firstOperand.getValue(),
            operator.getSymbol(),
            secondOperand.getValue()
        );
    }
}
