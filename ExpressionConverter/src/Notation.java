/**
 * This class implements methods to convert a postfix expression to infix, infix to postfix,
 * and to evaluate postfix expressions.
 * 
 * @author Raushan Oshan    
 * @throws InvalidNotationFormatException if the format is invalid
 */
public class Notation {

    /**
     * Evaluates a postfix expression and returns the result.
     * 
     * @param postfixExpr the postfix expression as a string
     * @return the result of the postfix evaluation
     * @throws InvalidNotationFormatException if the format of the expression is invalid
     */
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
        MyStack<String> stack = new MyStack<>(50);
        double result = 0;

        for (int i = 0; i < postfixExpr.length(); i++) {
            char current = postfixExpr.charAt(i);

            if (current == ' ') {
                continue;
            } else if (Character.isDigit(current) || current == '(') {
                try {
                    stack.push(String.valueOf(current));
                } catch (StackOverflowException e) {
                    e.printStackTrace();
                }
            } else if (current == '+' || current == '-' || current == '*' || current == '/') {
                if (stack.size() < 2) {
                    throw new InvalidNotationFormatException();
                }

                try {
                    double rightOperand = Double.valueOf(stack.pop());
                    double leftOperand = Double.valueOf(stack.pop());

                    switch (current) {
                        case '+':
                            result = leftOperand + rightOperand;
                            break;
                        case '-':
                            result = leftOperand - rightOperand;
                            break;
                        case '*':
                            result = leftOperand * rightOperand;
                            break;
                        case '/':
                            result = leftOperand / rightOperand;
                            break;
                    }
                    stack.push(String.valueOf(result));
                } catch (StackUnderflowException | StackOverflowException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            if (stack.size() == 1) {
                return Double.valueOf(stack.pop());
            }
        } catch (StackUnderflowException e) {
            e.printStackTrace();
            throw new InvalidNotationFormatException();
        }
        return result;
    }

    /**
     * Converts a postfix expression to an infix expression.
     * 
     * @param postfix the postfix expression as a string
     * @return the corresponding infix expression
     * @throws InvalidNotationFormatException if the format is invalid
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
        MyStack<String> stack = new MyStack<>(50);

        for (int i = 0; i < postfix.length(); i++) {
            char current = postfix.charAt(i);

            if (current == ' ') {
                continue;
            } else if (Character.isDigit(current)) {
                try {
                    stack.push(String.valueOf(current));
                } catch (StackOverflowException e) {
                    e.printStackTrace();
                }
            } else if (current == '+' || current == '-' || current == '*' || current == '/') {
                if (stack.size() < 2) {
                    throw new InvalidNotationFormatException();
                }

                try {
                    String leftOperand = stack.pop();
                    String rightOperand = stack.pop();
                    String result = "(" + rightOperand + current + leftOperand + ")";
                    stack.push(result);
                } catch (StackUnderflowException | StackOverflowException e) {
                    e.printStackTrace();
                }
            }
        }

        if (stack.size() == 1) {
            return stack.toString();
        } else {
            throw new InvalidNotationFormatException();
        }
    }

    /**
     * Converts an infix expression to a postfix expression.
     * 
     * @param infix the infix expression as a string
     * @return the corresponding postfix expression
     * @throws InvalidNotationFormatException if the format is invalid
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
        MyStack<Character> stack = new MyStack<>(50);
        MyQueue<Character> queue = new MyQueue<>(50);

        for (int i = 0; i < infix.length(); i++) {
            char current = infix.charAt(i);

            if (current == ' ') {
                continue;
            } else if (Character.isDigit(current)) {
                try {
                    queue.enqueue(current);
                } catch (QueueOverflowException e) {
                    e.printStackTrace();
                }
            } else if (current == '(') {
                try {
                    stack.push(current);
                } catch (StackOverflowException e) {
                    e.printStackTrace();
                }
            } else if (current == '+' || current == '-' || current == '*' || current == '/') {
                if(!stack.isEmpty()){
					try{
						if(((stack.top() == '*' || stack.top() == '/') && (current == '-' || current == '+')) || ((stack.top() == '-' || 
                            stack.top() == '+') && (current == '-' || current == '+')) || ((stack.top() == '*' || stack.top() == '/') && 
                            (current == '*' || current == '/'))){
							queue.enqueue(stack.pop());
						}
					}catch(StackUnderflowException e){
						e.printStackTrace();
					}catch(QueueOverflowException e){
						e.printStackTrace();
					}
                }
                try {
                    stack.push(current);
                } catch (StackOverflowException e) {
                    e.printStackTrace();
                }
            } else if (current == ')') {
                try {
                    char topOp = stack.pop();
                    while (topOp != '(') {
                        queue.enqueue(topOp);
                        topOp = stack.pop();
                    }
                } catch (StackUnderflowException | QueueOverflowException e) {
                    e.printStackTrace();
                    throw new InvalidNotationFormatException();
                }
            }
        }

        while (!stack.isEmpty()) {
            try {
                queue.enqueue(stack.pop());
            } catch (StackUnderflowException | QueueOverflowException e) {
                e.printStackTrace();
            }
        }

        return queue.toString();
    }

}
