/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 3
 * Recitation: 01
 */

/**
 * This is an exception class that indicates the stack is full.
 */
public class FullStackException extends Exception{
    public FullStackException() {
        super("The list is full");
    }
}
