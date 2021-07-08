package com.jaletechs.calculator.app;

import java.util.Scanner;

/**
 * CalculatorApp!
 *
 */
public class CalculatorApp {
    public static void main( String[] args ) {
        printWelcomeScreen();
        run();
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter an expression: ");
        String expressionString = scanner.nextLine();

        while (!expressionString.equalsIgnoreCase("exit")) {

            expressionString = expressionString.trim();
            Expression expression = new Expression(expressionString);
            System.out.println("Result: " + expression.evaluate());

            System.out.println("Please Enter an expression: ");
            expressionString = scanner.nextLine();
        }
    }

    private static void printWelcomeScreen() {
        System.out.println("***********************************");
        System.out.println("          CALCULATOR APP          ");
        System.out.println("  Valid Operations are: *, /, +, - ");
        System.out.println("  Type 'EXIT' to quit");
        System.out.println("***********************************");
        System.out.println();
    }
}
