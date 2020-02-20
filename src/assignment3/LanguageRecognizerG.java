package assignment3;

import java.util.Stack;

public class LanguageRecognizerG {
    String word = "";
    Stack<Character> characterStack = new Stack<Character>();
    int initialSize;
    LanguageRecognizerG(String input){
        word = input;
        for (char c: input.toCharArray()) {
            characterStack.push(c);
        }
        initialSize = characterStack.size();
    }

    private boolean recursiveRecogG(){
        //BASE CASE: list is 1 chars long, and it is <E>
        if (this.initialSize == 1){
            Character cur = characterStack.pop();
            if (cur.equals("&") || cur.equals("#")) {
                return true;
            }
        }
        //BASE CASE: list is empty
        if (this.characterStack.size() == 0) {
            return true;
        }
        //DIFFERENT BASE CASES FOR characterStack > 2 chars long
        else {
            //BASE CASE: we're at the last char in the list. is it <V>?
            if (this.characterStack.size() == 1) {
                Character cur = characterStack.pop();
                if (cur.equals("W") || cur.equals("A")) {
                    return true;
                }
            }
            //BASE CASE: we have 2 chars left. are they <V> <E>?
            if (this.characterStack.size() == 2) {
                Character cur = characterStack.pop();
                if (cur.equals("W") || cur.equals("A")) {
                    cur = characterStack.pop();
                    if (cur.equals("&") || cur.equals("#")) {
                        return true;
                    }
                }
            }
            else {
                return recursiveRecogG();
            }
        }
        return false;
    }

    public void recursivePrintG(){


    }

}
