package datastructure;

import java.util.ArrayList;
import java.util.List;

public class MyStackByArray {

    private int numElements;
    //we can either use Array or LinkedList to implement stack
    private List<Integer> underlyingArray;

    public MyStackByArray(){
        numElements = 0;
        underlyingArray = new ArrayList<Integer>();
    }
    
    public void push(int val){
        underlyingArray.add(val);
        numElements++;
    }

    public boolean pop(){
        if(numElements == 0) return false;
        else underlyingArray.remove(numElements - 1);
        return true;

    }

    public int top(){
        return underlyingArray.get(numElements - 1);
    }

    public boolean isEmpty(){
        return numElements == 0;
    }
    
    
}
