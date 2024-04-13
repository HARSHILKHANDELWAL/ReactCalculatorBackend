package CalculatorController;//package Controller;
import java.util.Stack;

public class ArthmeticEvaluator {

    public static double calculate(String expression) {
        // Stack to store operands
        Stack<Double> operands = new Stack<>();
        // Stack to store operators
        Stack<Character> operators = new Stack<>();

        // Iterating through each character of the expression
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
//            System.out.println(ch);

            if (ch == ' ') {
                continue; // Skip whitespace
            }
            if (Character.isDigit(ch) || ch == '.') {
                // If character is a digit or a decimal point, parse the number
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
//                System.out.println(num+" first if");
                i--; // Move the index back by 1 to account for the last character of the number
                operands.push(Double.parseDouble(num.toString()));
            } else if (ch == '+' || ch == '-' || ch == 'x' || ch == '/') {
                // If character is an operator
                if (ch == '-' && (i == 0 || expression.charAt(i - 1) == '(')) {
                    // If '-' is at the beginning of expression or after '(' it is unary negation
                    operators.push('~');
                } else {
                    while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                        applyOperator(operators.pop(), operands);
                    }
                    operators.push(ch);
//                    System.out.println(" 2nd else if");

                }
            }

        }

        // Apply remaining operators
        while (!operators.isEmpty()) {
            applyOperator(operators.pop(), operands);
        }

        // The result will be the only element left in the operands stack
        return operands.pop();
    }

    // Function to apply the operator to the top two operands on the operands stack
    private static void applyOperator(char operator, Stack<Double> operands) {
        double operand2 = operands.pop();
        double operand1 = operands.isEmpty() ? 0 : operands.pop(); // Handle unary negation
        double result = 0;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case 'x':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
            case '~':
                result = -operand2; // Unary negation
                break;
        }
        operands.push(result);
    }

    // Function to check operator precedence
    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == 'x' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }
public boolean isInRange(int lowerBound,int upperBound,String numberString){

    try {
//        int number = Integer.parseInt(numberString);
        double number= Double.parseDouble(numberString);
        return number >= lowerBound && number <= upperBound;
    } catch (NumberFormatException e) {
        // If parsing fails, the string is not a valid integer
        return false;
    }
}
    public static void main(String[] args) {
        // Test the calculator
        String expression = "-2-2";
        double result = calculate(expression);
        System.out.println("Result: " + result);
    }
}

