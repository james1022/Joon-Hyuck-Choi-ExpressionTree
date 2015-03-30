//ExpressionTranslator.java
/*
 * Name: Joon Hyuck Choi
 * CS226 Homework 2
 * Date: Feb 26, 2015
 * Last edited: 22:56 02/26/15
 * email: jchoi100@jhu.edu
 */
import java.util.Iterator;
import java.util.Scanner;

/**
 * This is the main driver class for the ExpressionTree.
 * It includes a main method and a helper method that
 * forms a binary tree based on user input.
 * 
 * @author Joon Hyuck Choi
 */
public final class ExpressionTranslator {

    /**
     * Dummy constructor to disable the default constructor.
     */
    private ExpressionTranslator() {
        
    }
    
    /**
     * This is the driver main method for the PostfixEvaluator class.
     * 
     * @param args the argument that main takes in.
     */
    public static void main(String[] args) {
        
        /**
         * Takes in input from the keyboard.
         */
        Scanner kb = new Scanner(System.in);
        
        //set as an empty String for now. Sentinel for the do-while loop
        String answer = ""; 
        
        //General instructions;
        System.out.println("This program collects postfix expressions "
                                + "which consist");
        System.out.println("of operands and the operators +, -, or *.");
        System.out.println("It then generates equivalent expressions "
                                + "in various notations.");
        
        do { //keep looping unless the user inputs "n" or "N";
            
            System.out.println("Please enter an expression with "
                                    + "spaces between each token: ");
            
            //Take user input
            String expression = kb.nextLine();
            
            ExpressionTree result;
            
            try { //catch any exceptions that evaluate() may throw
                result = formTree(expression);
                System.out.println("Equivalent exprssions are: ");
                System.out.println("Postfix: " + result.postOrder());
                System.out.print("Prefix: ");
                Iterator<ExpressionTreeToken> iter = result.iterator();
                while (iter.hasNext()) {
                    System.out.print(iter.next() + " ");
                }
                System.out.println();
                System.out.println("Infix: " + result.getParenthesizedInfix());
                System.out.println();
                System.out.print("Enter another expression [Y/N]? ");
            } catch (MalformedPostfixExpressionException e) {
                System.out.println("Uh oh. " 
                                    + "That expression contained a problem.");
                System.out.println(e);
                System.out.println();
                System.out.println();
                System.out.print("Enter another expression [Y/N]? ");
            } //end try-catch()
            
            //Take another input
            answer = kb.nextLine();
            System.out.println();
            
        } while (!answer.equalsIgnoreCase("N"));
        
        //End program
        System.out.println("Goodbye!"); 
        kb.close();
    
    } //end main
    
    /**
     * Takes in the user input expression and evaluate it.
     * 
     * @param expression the postfix expression that the user input.
     * @return the resulting tree.
     */
    public static ExpressionTree formTree(String expression) {
        
        //read from the String expression
        Scanner eqn = new Scanner(expression); 
        
        //Create an empty Stack226Array
        Stack226Array<ExpressionTree> ray = new Stack226Array<ExpressionTree>();
        
        ExpressionTreeToken eToken = new ExpressionTreeToken("");
        
        //keep looping while the expression has next inputs to be read
        while (eqn.hasNext()) { 
            
            //Take in the next token from the String
            String sToken = eqn.next();
            
            //Convert the String token into ExpressionTreeToken type
            eToken = new ExpressionTreeToken(sToken);
            
            //First check if the token is an operator, 
            if (eToken.isOperator()) {
                
                //To do an operation, there must be 
                //at least 2 elements in the list to pop
                if (ray.size() < 2) {
                    eqn.close();
                    throw new MalformedPostfixExpressionException(
                            "Operator without sufficient operands.");
                } //end if
          
                //pop() two trees and save each in a variable
                ExpressionTree rightTree = ray.pop();
                ExpressionTree leftTree = ray.pop();
                
                //Link the trees together and form a new bigger tree
                ExpressionTree biggerTree = new ExpressionTree(eToken, 
                                                leftTree, rightTree);
                
                //push the middle result back to the list
                ray.push(biggerTree); 
                
            } else {
                ExpressionTree node = new ExpressionTree(eToken);
                ray.push(node);                
            } //end if-else

        } //end while
        
        if (ray.size() > 1) { //check if there were enough operators;
            eqn.close();
            throw new MalformedPostfixExpressionException(
                    "Too few operators.");
        } //end if
        
        if (ray.size() < 1) { //check if the expression was empty;
            eqn.close();
            throw new MalformedPostfixExpressionException(
                    "Empty expression.");     
        } //end if
        
        eqn.close();
        
        return ray.pop();
        
    } //end formTree()
  
} //end class
