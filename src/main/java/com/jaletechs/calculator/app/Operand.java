package com.jaletechs.calculator.app;

import lombok.Builder;
import lombok.Data;

/**
 * The Operand represents a number in a mathematical expression
 */
@Data
@Builder(toBuilder = true)
public class Operand {
    private double value;
}
