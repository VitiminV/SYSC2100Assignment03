package assignment3;

import java.util.LinkedList;
import java.util.ListIterator;

public class StackListBased {
    LinkedList<Object> items;

    StackListBased() {
        items = new LinkedList<Object>();
    }

    public StackListBased CreateStack() {
        return new StackListBased();
    }

    public Object popAll() {
        int i = 0;
        int InitialSize = items.size();
        Object x = null;
        while (i < InitialSize) {
            x = items.pop();
            i++;
        }
        return x;
    }

    public int getSize() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void push(Object e) {
        items.push(e);
    }

    public Object pop() {
        return items.pop();
    }

    public Object peek() {
        return items.peek();
    }
}
