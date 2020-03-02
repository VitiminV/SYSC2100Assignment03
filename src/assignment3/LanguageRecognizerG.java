package assignment3;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;

public class LanguageRecognizerG {

    String InputWord = "";
    ArrayList<Character> CharacterList = new ArrayList<Character>();
    int initialSize;

    LanguageRecognizerG(String input) {
        InputWord = input;
        for (char c : input.toCharArray()) {
            CharacterList.add(c);
        }
        initialSize = CharacterList.size();
        ListIterator WordItr = CharacterList.listIterator(0);
    }


    private boolean recursiveRecogG(ArrayList<Character> WordList) {
        if (WordList.size() == 0) {
            return true;
        }
        if (WordList.size() == 1) {
            if (WordList.get(0) == '&' | WordList.get(0) == '#') {
                return true;
            }
        }
        if (WordList.size() == 2) {
            if (WordList.get(0) == 'W' | WordList.get(0) == 'A') {
                if (WordList.get(1) == '&' | WordList.get(1) == '#') {
                    return true;
                }
            }
            if (WordList.get(0) == '&' | WordList.get(0) == '#') {
                if (WordList.get(1) == 'W' | WordList.get(1) == 'A') {
                    return true;
                }
            }
        }
        if (WordList.size() > 2) {
            if (WordList.get(0) == '&' | WordList.get(0) == '#') {
                if (WordList.get(WordList.size() - 1) == 'W' | WordList.get(WordList.size() - 1) == 'A') {
                    ArrayList NewList = (ArrayList) WordList.subList(1, WordList.size() - 2);
                    if (recursiveRecogG(NewList)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void recursivePrintG() {
        boolean result = recursiveRecogG(CharacterList);
        if (result) {
            System.out.print("Recursion: Word \"" + InputWord + "\" IS a word of the G language. \n \n");
        } else {
            System.out.print("Recursion: Word \"" + InputWord + "\" IS NOT a word of the G language. \n \n");
        }
    }

}
