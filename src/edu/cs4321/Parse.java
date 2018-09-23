package edu.cs4321;

import edu.cs4321.tree.*;

import java.util.Stack;

public class Parse {

    private static int precedence(char op) {
        switch (op) {
            case '-':
                return 1;
            case '+':
                return 1;
            case '*':
                return 2;
            default:
                throw new UnsupportedOperationException("unknown operator: " + op);
        }
    }

    private static void createNewOperand(Character operator, Stack<Operator> operands) {
        Operator rightOperand = operands.pop();
        Operator leftOperand = operands.pop();
        switch (operator) {
            case '-':
                //TODO: add subtraction operator here
                throw new UnsupportedOperationException();
            case '+':
                operands.push(new Addition(leftOperand, rightOperand));
                break;
            case '*':
                operands.push(new Multiplication(leftOperand, rightOperand));
                break;
            default:
                throw new UnsupportedOperationException("unknown operator: " + operator);
        }
    }

    public static Operator parse(String input) {
        int curIndex = 0;
        boolean afterOperand = false;
        Stack<Operator> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        while (curIndex < input.length()) {
            int startIndex = curIndex;
            char c = input.charAt(curIndex++);

            if (Character.isWhitespace(c))
                continue;

            if (afterOperand) {
                if (c == ')') {
                    Character operator;
                    while (!operators.isEmpty() && ((operator = operators.pop()) != '('))
                        createNewOperand(operator, operands);
                    continue;
                }
                afterOperand = false;
                while (!operators.isEmpty() && (operators.peek() != '(') && (precedence(operators.peek())>= precedence(c)))
                    createNewOperand(operators.pop(), operands);
                operators.push(c);
                continue;
            }

            if (c == '(') {
                operators.push('(');
                continue;
            }

            afterOperand = true;
            while (curIndex < input.length()) {
                c = input.charAt(curIndex);
                if (((c < '0') || (c > '9')) && (c != '.') &&
                        ((c < 'a') || (c > 'z')) && ((c < 'A') || (c > 'Z')))
                    break;
                curIndex++;
            }
            String token  = input.substring(startIndex, curIndex);
            if (token.matches("[0-9]*")) {
                operands.push(new IntegerConstant(Integer.valueOf(token)));
            } else if (token.matches("([a-z]|[A-Z]).*")) {
                operands.push(new Variable(token));
            } else {
                throw new RuntimeException("unknown token: " + token);
            }
        }

        while (!operators.isEmpty()) {
            Character operator = operators.pop();
            if (operator == '(')
                throw new IllegalArgumentException();
            createNewOperand(operator, operands);
        }

        Operator expression = operands.pop();
        if (!operands.isEmpty())
            throw new IllegalArgumentException();
        return expression;
    }
}
