//StackOfIntegersArray.java
//keep bottom stack element at position 0 in array
/*
 * Name: Joon Hyuck Choi
 * CS226 Homework 2
 * Date: Feb 26, 2015
 * Last edited: 21:41 02/26/15
 * email: jchoi100@jhu.edu
 */

/**
 * This is a generic stack array implementation for HW2.
 * 
 * @author Joon Hyuck Choi
 * 
 * @param <T> The data type that will be stored in the Stack226Array.
 */
public class Stack226Array<T> implements Stack226<T> {

    /**
     * The default capacity of array of T objects.
     */    
    private static final int DEFAULT_CAPACITY = 100;
    
    /**
     * The stack of T objects.
     */
    private T[] stack;
    
    /**
     * Represents the index of the next position to use.
     */
    private int top;
    
    /**
     * Creates a stack226array with no arguments passed.
     */
    public Stack226Array() {
        this(DEFAULT_CAPACITY);
    } //end Constructor1
    
    /**
     * Creates a stack array with the specified capacity and
     * sets the top index to 0.
     * @param initialCapacity the capacity that the user specified.
     */
    public Stack226Array(int initialCapacity) {
        this.stack = (T[])new Object[initialCapacity];
        this.top = 0;
    } //end Constructor2

    /**
     * Inserts a new element at the top of the stack.
     * Increments top by 1 each time it is performed.
     * @param element T object to insert into the stack.
     */
    public void push(T element) {

        //check if there is still space left in the stack.
        if (this.stack.length <= this.top) {
            //increase capacity
            this.expandCapacity();
        }
        
        //add the new element at the top
        this.stack[this.top] = element;
        
        //increment the next place to save a new element
        this.top++;
    } //end push()

    /**
     * Expands the capacity of the stack to twice its original size.
     * It gets called wherever necessary.
     */
    private void expandCapacity() {

        //double the current capacity
        T[] temp = (T[])new Object[this.stack.length * 2];

        //copy over all the stack[] elements to temp[]
        for (int i = 0; i < this.stack.length; i++) {
            temp[i] = this.stack[i];
        }
        
        //point stack to what temp is pointing at.
        this.stack = temp;
    } //end expandCapacity()

    /**
     * Removes the object at the top of the stack.
     * @return the object that was removed.
     */
    public T pop() {
        
        //check if there is anything to pop in the stack.
        if (this.isEmpty()) {
            //throw an exception.
            throw new StackEmptyException("empty stack on pop");
        }
        //decrement the top index: where the next element is to be stored.
        this.top--;
        
        //put the popped element in T result
        T result = this.stack[this.top];
        
        //set the popped place to null
        this.stack[this.top] = null;
        
        //return what used to be at the top
        return result;
    } //end pop()

    /**
     * Shows the user what is currently at the top of the stack.
     * It does not remove the element, unlike pop.
     * @return the object currently at the top of the stack.
     */
    public T peek() {
        
        //check if the stack is empty
        if (this.isEmpty()) {
            //throw an exception
            throw new StackEmptyException("empty stack on peek");
        }
        
        //else, return the current element at the top of the stack.
        return this.stack[this.top - 1];
    } //end peek()

    /**
     * Checks if the stack is empty or not.
     * @return true if empty, false if not.
     */
    public boolean isEmpty() {
        return (this.size() == 0);
    } //end isEmpty()

    /**
     * Lets the user know how many elements are currently in the stack.
     * @return the size of the stack.
     */
    public int size() {
        return this.top;        
    } //end size()

    /**
     * Reports the current contents of the stack,
     * with the top of the stack at the right end of the String.
     * @return String representation of stack elements.
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < this.top; i++) {
            result += this.stack[i] + " ";
        }
        return result;
    } //end toString

} //end class