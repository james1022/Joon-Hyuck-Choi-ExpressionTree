//ExpressionTree.java
/*
 * Name: Joon Hyuck Choi
 * CS226 Homework 2
 * Date: Feb 26, 2015
 * Last edited: 22:56 02/26/15
 * email: jchoi100@jhu.edu
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class extends the BinaryTree226Linked class and 
 * implements the ExpressionTreeInterface.
 * This means that it can form a tree and has an iterator.
 * 
 * @author Joon Hyuck Choi
 *
 */
public class ExpressionTree
                extends BinaryTree226Linked<ExpressionTreeToken> 
                implements ExpressionTreeInterface {
    
    /**
     * Constructor that constructs a single node tree.
     * 
     * @param token the data to be the root of the single node tree.
     */
    public ExpressionTree(ExpressionTreeToken token) {
        super(token);
    } //end Constructor1
    
    /**
     * Constructor that sets a new root reference to a node with "data",
     * and sets the root node's left and right children.
     * 
     * @param data an operator to be the new root that takes two subtrees as 
     *        its left and right children.
     * @param left the left subtree of the operator.
     * @param right the right subtree of the operator.
     */
    public ExpressionTree(ExpressionTreeToken data, 
                            ExpressionTree left, 
                            ExpressionTree right) {
        super(data, left, right);
    } //end Constructor2
  
    /**
     * An iterator that does preorder traversing of the tree.
     * 
     * @return returns the Iterator that traverses in prefix order.
     */
    public Iterator<ExpressionTreeToken> iterator() {
        ArrayList<ExpressionTreeToken> list = 
                new ArrayList<ExpressionTreeToken>(size());
        this.preOrder(root, list);
        return new ExpressionTreeIterator(list);
    } //end iterator()
    
    //another method to get the list of everything in the tree in preorder
    //pass that list to the iterator for it to run over it.
    /**
     * This is a helper method to accomplish a preorder traversal of the tree.
     * 
     * @param current the current node that is being checked.
     * @param list the ArrayList in which tokens will be put.
     */
    private void preOrder(BinaryNode<ExpressionTreeToken> current, 
                            ArrayList<ExpressionTreeToken> list) {

        if (current != null) {
            list.add(current.data);
            this.preOrder(current.left, list);
            this.preOrder(current.right, list);
        }
        
    } //end preOrder()
    
    /**
     * This is a nested iterator class to be used in the outer class and main.
     * @author Joon Hyuck Choi
     *
     * @param <ExpressionTreeToken> The data type used in the iterator.
     */
    
    private class ExpressionTreeIterator 
                        implements Iterator<ExpressionTreeToken> {

        /**
         * The list of ExpressionTreeTokens with a known size.
         */
        private ArrayList<ExpressionTreeToken> list;
        
        /**
         * The current position that the iterator is looking at.
         */
        private int current;

        /**
         * Constructor for the Iterator class.
         * Takes in no parameters and sets the current position to 0.
         * 
         * @param pList the list of EpxressionTreeTokens in preorder fashion.
         */
        public ExpressionTreeIterator(ArrayList<ExpressionTreeToken> pList) {
            this.current = 0;
            this.list = pList;
        } //end Constructor
        
        /**
         * @return returns true if the tree has a next expression; false if not.
         */
        @Override
        public boolean hasNext() {
            return this.current < ExpressionTree.this.size();
        } //end hasNext()

        /**
         * @return returns the ArrayList element at the specified count index.
         */
        @Override
        public ExpressionTreeToken next() {
            
            if (!this.hasNext()) {
                return null;
                //throw new StackEmptyException("Nothing left in the tree!"); 
            } else {
                ExpressionTreeToken nextToken = this.list.get(this.current);
                this.current++;
                return nextToken;
            } //end if-else

        } //end next()
        
    } //end class ExpressionTreeIterator<ExpressionTreeToken>

    @Override
    /**
     * This is the getParenthesizedInfix that actually gets called in main.
     */
    public String getParenthesizedInfix() {
        return this.getParenthesizedInfix(root).trim();
    } //end getParenthesizedInfix()

    /**
     * This is a helper method for the getParenthesizedInfix() method
     * that takes no arguments. This is so that the user who wants
     * this functionality can call the method without having to worry
     * about what to input in as its parameter.
     * 
     * @param curr the current node.
     * @return the parenthesized infix notation of the expression.
     */
    private String getParenthesizedInfix(BinaryNode<ExpressionTreeToken> curr) {
       
        //If the current node is null, return nothing
        if (curr == null) {
            return "";
        } //end if
        
        //If the current data is an operator, have a set of parentheses
        if (curr.data.isOperator()) {
            return "(" + this.getParenthesizedInfix(curr.left).trim() + " " 
                    + curr.data + " " 
                    + this.getParenthesizedInfix(curr.right).trim() + ")";
        } else {
            return this.getParenthesizedInfix(curr.left).trim() + " " 
                    + curr.data + " " 
                    + this.getParenthesizedInfix(curr.right).trim();
        } //end if-else
        
    } //end getParenthesizedInfix() helper method

} //end class
