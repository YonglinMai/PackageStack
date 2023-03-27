/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 3
 * Recitation: 01
 */

import java.util.ArrayList;
import java.util.Stack;

/**
 * A fully-documented class named PackageStack that stores all packages in a stack.
 */
public class PackageStack{
    private final int CAPACITY = 7;
    private int packageCounts = 0;
    private ArrayList<Package> packageArrayList = new ArrayList<>();
    private Stack<Package> stack = new Stack<Package>();

    /**
     * A default constructor for the PackageStack class.
     */
    public PackageStack(){
    }

    /**
     * A getter method that gets the arraylist of packages.
     * @return          :
     *                  returns the arraylist of packages.
     */
    public ArrayList<Package> getPackageArrayList() {
        return packageArrayList;
    }

    /**
     * Pushes a package onto the top of the backing data structure.
     * @param x         :
     *                  THe package that is to be pushed.
     * Throws           :
     *                  FullStackException if the stack is at capacity.
     */
    public void push(Package x) throws FullStackException{
        if (packageCounts == CAPACITY){
            throw new FullStackException();
        }

        stack.push(x);
        packageArrayList.add(x);
        packageCounts++;
    }

    /**
     * Removes the topmost package from the stack and returns it.
     * @return          :
     *                  The package that is being removed from the stack.
     * Throws           :
     *                  EmptyStackException if the stack was empty.
     *                  If you are extending LinkedList, you may rename this popPackage() if your IDE is complaining about throwing a EmptyStackException
     */
    public Package pop() throws EmptyStackException{
        packageCounts--;
        packageArrayList.remove(packageArrayList.size() - 1);
        return stack.pop();
    }

    /**
     * The method returns the topmost Package from the stack without removing it.
     * The stack should be unchanged as a result of this method.
     * @return      :
     *              The topmost package from the stack without removing it.
     * Throws       :
     *              EmptyStackException if the stack was empty
     */
    public Package peek() throws EmptyStackException{
        return stack.peek();
    }

    /**
     * the method returns true if the stack is empty, false otherwise.
     * @return      :
     *              Returns true if the stack is empty, false otherwise.
     */
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    /**
     * the method returns true if the stack is full, false otherwise.
     * @return          :
     *                  Returns true if the stack is full, false otherwise.
     */
    public boolean isFull(){
        return packageCounts == CAPACITY;
    }

    public String toString(){
        String packageString = "";
        for (Package aPackage : packageArrayList) {
            packageString = packageString + aPackage.toString();
        }

        return packageString;
    }
}
