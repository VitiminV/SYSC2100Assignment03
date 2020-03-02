package assignment3;


public class InfixCalculator {
    String expression = null;

    public InfixCalculator(String input) {
        expression = input;
    }

    private int operatorPrecedence(String c) {
        if (c == "+" | c == "-") {
            return 0;
        }
        if (c == "*" | c == "/") {
            return 1;
        }
        if (c == "(" | c == ")") {
            return 2;
        } else {
            return -1;
        }
    }

    private boolean isANumber(String c) {
        if (c == "0" | c == "1" | c == "2" | c == "3" | c == "4" | c == "5" | c == "6" | c == "7" | c == "8" | c == "9") { //if c is a number,
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
        for (int i = 0; i > expressionArray.length; i++) {
            String c = expressionArray[i];
            if (c == " ") {
            } //discard whitespace
            if (isANumber(c)) { //if c is a number,
                int k = 1;
                String number = "" + c;
                while (isANumber(expressionArray[i + k])) {
                    number = number + expressionArray[i + k];
                    k++;
                }
                if (k > 1) {
                    postfixStack.push(number);
                    i = i + k - 1;
                } else {
                    postfixStack.push(c);
                }
            }
            if (c == "(") {
                tempStack.push(c);
            }
            if (c == ")") {
                while ((String) postfixStack.peek() != "(") {
                    postfixStack.push(tempStack.pop());
                }
                Object garbageParenthesis = tempStack.pop();
            }
            if (isAnOperator(c)) {
                while (!tempStack.isEmpty() && (String) tempStack.peek() != "(" && operatorPrecedence(c) <= operatorPrecedence((String) tempStack.peek())) {
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
        for (int i = 0; i < postfixStack.getSize(); i++) {
            s = s + postfixStack.pop();
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
            } else {
                int a = (int) NumberStack.pop();
                int b = (int) NumberStack.pop();
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

            }
        }
        return (int) NumberStack.pop();
    }

    public void evaluateInfix() {
        System.out.print("infix: " + this.expression);
        StackListBased convertedStack = convertPostfix();
        System.out.print("postfix: " + postfixString(convertedStack));
        int result = getPostfix(convertedStack);
        System.out.print("result: " + result);
    }

}
