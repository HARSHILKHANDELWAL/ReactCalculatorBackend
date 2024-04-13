//package Controller;
//
//import java.util.*;
//public class ExpressionEvaluation {
//    public static String evaluateExpression(String[] tokens) {
//        try {
//            System.out.println(Arrays.toString(tokens)+"token");
//
//            if (tokens.length == 0) {
//                throw new Exception("Invalid expression");
//            }
//
//            Stack<Double> output = new Stack<>();
//            Stack<String> operators = new Stack<>();
//
//            // Define operator precedence
//            HashMap<String, Integer> precedence = new HashMap<>();
//            precedence.put("+", 1);
//            precedence.put("-", 1);
//            precedence.put("x", 2);
//            precedence.put("/", 2);
//
//            for (String token : tokens) {
//                if (token.matches("\\d+\\.\\d+|\\d+")) {
//                    System.out.println(token+  "  matches");
//                    output.push(Double.parseDouble(token));
//                } else if ("+-x/".contains(token)) {
//                    while (!operators.isEmpty() && precedence.get(operators.peek()) >= precedence.get(token)) {
//                        applyOperator(output, operators.pop());
//                    }
//                    operators.push(token);
//                }
//            }
//
//            while (!operators.isEmpty()) {
//                applyOperator(output, operators.pop());
//            }
//
//            if (output.size() != 1) {
//                throw new Exception("Invalid expression");
//            }
//            System.out.println(output + "output stack");
//
//
//            System.out.println(Arrays.toString(tokens) +"final");
//
//            return output.pop().toString();
//        } catch (Exception e) {
//            System.err.println("Error evaluating expression:");
//            return "Invalid";
//        }
//    }
//
//    private static void applyOperator(Stack<Double> output, String operator) {
//        double b = output.pop();
//        double a = output.pop();
//
//        switch (operator) {
//            case "+":
//                output.push(a + b);
//                break;
//            case "-":
//                output.push(a - b);
//                break;
//            case "x":
//                output.push(a * b);
//                break;
//            case "/":
//                output.push(a / b);
//                break;
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
//
//package Controller;
//
//import java.util.Arrays;
//import java.util.Stack;
//
//public class ArthmeticEvaluator {
//
//    public static Double evaluateArithmeticExpression(String expression) {
//        try {
//            //old
////            String[] tokens = expression.split("\\s*(?<=[+\\-x/()])|(?=[+\\-x/()])\\s*");
//            //new
//            String[] tokens = expression.split("\\s*(?<=[+\\-x/()])|(?=[+\\-x/()])\\s*");
//            System.out.println(Arrays.toString(tokens));
//            if (tokens.length == 0) {
//                throw new IllegalArgumentException("Invalid expression");
//            }
//
//            Stack<Double> output = new Stack<>();
//            Stack<String> operators = new Stack<>();
//
//            for (String token : tokens) {
//                if (token.matches("\\d+\\.\\d+|\\d+")) {
//                    output.push(Double.parseDouble(token));
//                } else if ("+-x/".contains(token)) {
//                    while (!operators.isEmpty() && getPrecedence(operators.peek()) >= getPrecedence(token)) {
//                        applyOperator(output, operators.pop());
//                    }
//                    operators.push(token);
//                } else if ("(".equals(token)) {
//                    operators.push(token);
//                } else if (")".equals(token)) {
//                    while (!operators.isEmpty() && !operators.peek().equals("(")) {
//                        applyOperator(output, operators.pop());
//                    }
//                    operators.pop();
//                }
//            }
//
//            while (!operators.isEmpty()) {
//                applyOperator(output, operators.pop());
//            }
//
//            if (output.size() != 1 || !operators.isEmpty()) {
//                throw new IllegalArgumentException("Invalid expression");
//            }
//
//            return output.pop();
//        } catch (Exception e) {
//            System.err.println("Error evaluating expression: " + e.getMessage());
//            return null;
//        }
//    }
//
//    private static int getPrecedence(String operator) {
//        switch (operator) {
//            case "+":
//            case "-":
//                return 1;
//            case "x":
//            case "/":
//                return 2;
//            default:
//                return 0;
//        }
//    }
//
//    private static void applyOperator(Stack<Double> output, String operator) {
//        double b = output.pop();
//        double a = output.pop();
//
//        switch (operator) {
//            case "+":
//                output.push(a + b);
//                break;
//            case "-":
//                output.push(a - b);
//                break;
//            case "x":
//                output.push(a * b);
//                break;
//            case "/":
//                output.push(a / b);
//                break;
//        }
//    }
//
//    public static void main(String[] args) {
//        // Example usage:
//        Double result = evaluateArithmeticExpression("9+9x6/3");
//        System.out.println(result); // Outputs: 11.0
//    }
//}
//

//public class ArthmeticEvaluator {
//
//    public static Double evaluateArithmeticExpression(String expression) {
//        try {
//            String[] tokens = expression.split("\\s*(?<=[+\\-/()])|(?=[+\\-/()])\\s*");
//
//            if (tokens.length == 0) {
//                throw new IllegalArgumentException("Invalid expression");
//            }
//
//            Stack<Double> output = new Stack<>();
//            Stack<String> operators = new Stack<>();
//
//            for (int i = 0; i < tokens.length; i++) {
//                String token = tokens[i];
//
//                if (token.matches("\\d+\\.\\d+|\\d+")) {
//                    output.push(Double.parseDouble(token));
//                } else if ("+-*/".contains(token)) {
//                    if (i == 0 || "(".equals(tokens[i - 1])) {
//                        // Handle unary minus
//                        output.push(0.0);
//                    }
//
//                    while (!operators.isEmpty() && getPrecedence(operators.peek()) >= getPrecedence(token)) {
//                        applyOperator(output, operators.pop());
//                    }
//                    operators.push(token);
//                } else if ("(".equals(token)) {
//                    operators.push(token);
//                } else if (")".equals(token)) {
//                    while (!operators.isEmpty() && !operators.peek().equals("(")) {
//                        applyOperator(output, operators.pop());
//                    }
//                    operators.pop();
//                }
//            }
//
//            while (!operators.isEmpty()) {
//                applyOperator(output, operators.pop());
//            }
//
//            if (output.size() != 1 || !operators.isEmpty()) {
//                throw new IllegalArgumentException("Invalid expression");
//            }
//
//            return output.pop();
//        } catch (Exception e) {
//            System.err.println("Error evaluating expression: " + e.getMessage());
//            return null;
//        }
//    }
//
//    private static int getPrecedence(String operator) {
//        switch (operator) {
//            case "+":
//            case "-":
//                return 1;
//            case "*":
//            case "/":
//                return 2;
//            default:
//                return 0;
//        }
//    }
//
//    private static void applyOperator(Stack<Double> output, String operator) {
//        double b = output.pop();
//        double a = output.pop();
//
//        switch (operator) {
//            case "+":
//                output.push(a + b);
//                break;
//            case "-":
//                output.push(a - b);
//                break;
//            case "*":
//                output.push(a * b);
//                break;
//            case "/":
//                output.push(a / b);
//                break;
//        }
//    }
//
//    public static void main(String[] args) {
//        // Example usage:
//        Double result = evaluateArithmeticExpression("-66+33/2+(2*5)");
//        System.out.println(result); // Outputs: 7.0
//    }
//}



//
//import java.util.Stack;
//
//public class ArthmeticEvaluator {
//
//    public  double calculate(String expression) {
//        // Handle negative sign at the beginning of the expression
//        if (expression.charAt(0) == '-') {
//            expression = "0" + expression;
//        }
//
//        // Stack to store operands
//        Stack<Double> operands = new Stack<>();
//        // Stack to store operators
//        Stack<Character> operators = new Stack<>();
//
//        // Iterating through each character of the expression
//        for (int i = 0; i < expression.length(); i++) {
//            char ch = expression.charAt(i);
//            if (ch == ' ') {
//                continue; // Skip whitespace
//            }
//            if (Character.isDigit(ch) || ch == '.') {
//                // If character is a digit or a decimal point, parse the number
//                StringBuilder num = new StringBuilder();
//                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
//                    num.append(expression.charAt(i));
//                    i++;
//                }
//                i--; // Move the index back by 1 to account for the last character of the number
//                operands.push(Double.parseDouble(num.toString()));
//            } else if (ch == '+' || ch == '-' || ch == 'x' || ch == '/') {
//                // If character is an operator, push it onto the operators stack
//                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
//                    applyOperator(operators.pop(), operands);
//                }
//                operators.push(ch);
//            }
//        }
//
//        // Apply remaining operators
//        while (!operators.isEmpty()) {
//            applyOperator(operators.pop(), operands);
//        }
//
//        // The result will be the only element left in the operands stack
//        return operands.pop();
//    }
//
//    // Function to apply the operator to the top two operands on the operands stack
//    private void applyOperator(char operator, Stack<Double> operands) {
//        double operand2 = operands.pop();
//        double operand1 = operands.pop();
//        double result = 0;
//        switch (operator) {
//            case '+':
//                result = operand1 + operand2;
//                break;
//            case '-':
//                result = operand1 - operand2;
//                break;
//            case 'x':
//                result = operand1 * operand2;
//                break;
//            case '/':
//                result = operand1 / operand2;
//                break;
//        }
//        operands.push(result);
//    }
//
//    // Function to check operator precedence
//    private  boolean hasPrecedence(char op1, char op2) {
//        return (op2 != '(' && op2 != ')' && (op1 == '*' || op1 == '/') || (op2 == '+' || op2 == '-'));
//    }
//
//    public static void main(String[] args) {
//        // Test the calculator
//        String expression = "-22-22";
//        ArthmeticEvaluator ae= new ArthmeticEvaluator();
//        double result = ae.calculate(expression);
//        System.out.println("Result: " + result);
//    }
//}