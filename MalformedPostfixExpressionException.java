//MalformedPostfixExpressionException.java
/*
 * Name: Joon Hyuck Choi
 * CS226 Homework 2
 * Date: Feb 26, 2015
 * Last edited: 21:40 02/26/15
 * email: jchoi100@jhu.edu
 */

/**
 * This is the MalformedPostFixExpressionException class used in main.
 * @author Joon Hyuck Choi
 */
public class MalformedPostfixExpressionException extends RuntimeException {

    /**
     * Constructor with no arguments which uses default message.
     */
    public MalformedPostfixExpressionException() {
        super("***MalformedPostfixExpressionException***");
    } //end constructor1

    /**
     * A constructor for the exception that takes in a message.
     * @param message the error message to output.
     */
    public MalformedPostfixExpressionException(String message) {
        super(message);  //calls RuntimeException's constructor
    } //end constructor2
    
} //end MalformedPostfixExpressionException
