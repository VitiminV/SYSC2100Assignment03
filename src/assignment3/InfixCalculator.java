package assignment3;


import java.util.Stack;

public class InfixCalculator {
    String expression = null;

    public InfixCalculator(String input) {
        expression = input;
    }

    private int operatorPrecedence(String c) {
        if (c.equals("+") | c.equals("-")) {
            return 0;
        }
        if (c.equals("*") | c.equals("/")) {
            return 1;
        }
        if (c.equals("(") | c.equals(")")) {
            return 2;
        } else {
            return -1;
        }
    }

    private boolean isANumber(String c) {
        if (c.equals("0") | c.equals("1") | c.equals("2") | c.equals("3") | c.equals("4") | c.equals("5") | c.equals("6") | c.equals("7") | c.equals("8") | c.equals("9")) { //if c is a number,
            return true;
        }
        return false;
    }

    private boolean isAnOperator(String c) {
        if (c.equals("+") | c.equals("-") | c.equals("*") | c.equals("/")) {
            return true;
        }
        return false;
    }

    private StackListBased convertPostfix() {
        StackListBased tempStack = new StackListBased(); //stack to hold operators temporarily
        StackListBased postfixStack = new StackListBased(); //stack to hold finished post fix expression
        String[] expressionArray = new String[expression.length()];
        int a = 0;
        for (Character c : expression.toCharArray()) {
            expressionArray[a] = c.toString();
            a++;
        }
        for (int i = 0; i < expressionArray.length; i++) {
            String c = expressionArray[i];
            if (c.equals(" ")) {
                continue;
            } //discard whitespace
            if (isANumber(c)) { //if c is a number,
                int k = 1;
                String number = "" + c;
                while (i + k < expressionArray.length && isANumber(expressionArray[i + k])) {
                    number = number + expressionArray[i + k];
                    k++;
                }
                if (k > 1) {
                    postfixStack.push(number);
                    i = i + k - 1;
                } else {
                    postfixStack.push(c);
                    continue;
                }
            }
            if (c.equals("(")) {
                tempStack.push(c);
            }
            if (c.equals(")")) {
                while (!((String) postfixStack.peek()).equals("(")) {
                    postfixStack.push(tempStack.pop());
                }
                Object garbageParenthesis = tempStack.pop();
            }
            if (isAnOperator(c)) {
                while (!tempStack.isEmpty() && !((String) tempStack.peek()).equals("(") && operatorPrecedence(c) <= operatorPrecedence((String) tempStack.peek())) {
                    postfixStack.push(tempStack.pop());
                }
                tempStack.push(c);
            }
        }
        while (!tempStack.isEmpty()) {
            postfixStack.push(tempStack.pop());
        }
        return postfixStack;
    }

    private String postfixString(StackListBased postfixStack) {
        String s = "";
        StackListBased reverseStack = new StackListBased();
        while (!postfixStack.isEmpty()) {
            reverseStack.push(postfixStack.pop());
        }
        while (!reverseStack.isEmpty()) {
            s = s + reverseStack.pop().toString();
        }
        return s;
    }

    private int getPostfix(StackListBased postfixStack) {
        StackListBased NumberStack = new StackListBased();
        int result;
        for (int i = 0; i < postfixStack.getSize(); i++) {
            String c = (String) postfixStack.pop();
            if (isANumber(c)) {
                NumberStack.push(c);
            }
            else {
                int a = (int) postfixStack.pop();
                int b = (int) postfixStack.pop();
                if (c.equals("+")) {
                    NumberStack.push(a + b);
                }
                if (c.equals("-")) {
                    NumberStack.push(a - b);
                }
                if (c.equals("*")) {
                    NumberStack.push(a * b);
                }
                if (c.equals("/")) {
                    NumberStack.push(a / b);
                }
                else {
                    String unconvertB = "";
                    NumberStack.push(unconvertB + (char) b);
                    String unconvertA = "";
                    NumberStack.push(unconvertA + (char) a);

                }
            }
        }
        return (int) NumberStack.pop();
    }

    public void evaluateInfix() {
        System.out.print("infix: " + this.expression + "\n");
        StackListBased convertedStack = convertPostfix();
        StackListBased convertedStack2 = convertPostfix();
        System.out.print("postfix: " + postfixString(convertedStack) + "\n");
        int result = getPostfix(convertedStack2);
        System.out.print("result: " + result + "\n");
    }
}
