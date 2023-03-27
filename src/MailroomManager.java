/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 3
 * Recitation: 01
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * A fully-documented driver class named MailroomManager which contains 6 package stacks for storing mail.
 */
public class MailroomManager {

    private static final String[] menu = new String[]{
            "D - Deliver Package\n",
            "G - Get Packages for a user - gets the topmost package for a user\n",
            "T - Make it tomorrow\n",
            "P - print the stacks (format for a package is [Name arrivalDate]\n",
            "M - move a package from one stack to another\n",
            "F - Find packages in the wrong stack and move to Floor (note: just like in a teenager's room, the Floor never becomes full)\n",
            "L - List all the packages awaiting a user\n",
            "E- Empty the floor, moving all packages to the trash.\n",
            "Q - Quit"
    };


    static PackageStack stack1 = new PackageStack();
    static PackageStack stack2 = new PackageStack();
    static PackageStack stack3 = new PackageStack();
    static PackageStack stack4 = new PackageStack();
    static PackageStack stack5 = new PackageStack();
    static Stack<Package> floor = new Stack<>();

    /**
     * This is a helper method that helps to print the menu.
     * @param menu          :
     *                      The menu to be displayed.
     */
    public static void displayMenu(String[] menu) {
        for (String s : menu) {
            System.out.print(s);
        }
        System.out.println();
    }

    /**
     *  This method list out all the packages that belong to a recipient in a stack.
     * @param stack         :
     *                      The stack that is currently being search through.
     * @param stackName     :
     *                      The name of the stack.
     * @param name          :
     *                      The name of the recipient.
     * @param count         :
     *                      The number of packages that belong to the recipient.
     * @return              :
     *                      A list of packages that belong to the recipient.
     */
    public static ArrayList<String> listAllPackages(PackageStack stack, String stackName, String name, int count){
        ArrayList<String> packageList = new ArrayList<>();
        for (int i = 0; i < stack.getPackageArrayList().size(); i++){
            if (stack.getPackageArrayList().get(i).getRecipient().equalsIgnoreCase(name)){
                String string  =
                        "Package " +( count + 1 )+ " is in " + stackName + ", " +
                        "it was delivered on day " + stack.getPackageArrayList().get(i).getArrivalDate() + ", " +
                        "and weighs " + stack.getPackageArrayList().get(i).getWeight() + " lbs. \n";
                packageList.add(string);
                count++;
            }
        }
        return packageList;
    }

    /**
     * This is a helper method that helps to organize the packages into different stacks.
     * @param name          :
     *                      The name of the recipient.
     * @param arrivalDate   :
     *                      The arrival date of the package.
     * @param weight        :
     *                      The weight of the package.
     * @throws FullStackException   :
     *                              Move to different stack once the designated one is full.
     */
    public static void deliverPackage(String name, int arrivalDate, double weight) throws FullStackException {

        Package newPackage = new Package(name,arrivalDate,weight);

        if (name.compareToIgnoreCase("G") <= 0){
            if(!stack1.isFull()){
                stack1.push(newPackage);
            }else{
                stack2.push(newPackage);
            }
        }else if(name.compareToIgnoreCase("J") <= 0){
            if(!stack2.isFull()){
                stack2.push(newPackage);
            } else if(!stack1.isFull()){
                stack1.push(newPackage);
            }
        }else if(name.compareToIgnoreCase("M") <= 0){
            if(!stack3.isFull()){
                stack3.push(newPackage);
            } else if(!stack2.isFull()){
                stack2.push(newPackage);
            }
        }else if(name.compareToIgnoreCase("R") <= 0){
            if(!stack4.isFull()){
                stack4.push(newPackage);
            } else if(!stack3.isFull()){
                stack3.push(newPackage);
            }
        }else{
            if(!stack5.isFull()){
                stack5.push(newPackage);
            } else if(!stack4.isFull()){
                stack4.push(newPackage);
            }else if(!stack1.isFull()){
                stack1.push(newPackage);
            }else{
                floor.push(newPackage);
            }
        }
    }

    /**
     * This is a helper method that helps to print out the current packages in the stacks.
     */
    public static void printStack(){
        System.out.println("Stack1 （A-G) :| " + stack1);
        System.out.println("Stack2 （H-J) :| " + stack2);
        System.out.println("Stack3 （K-M) :| " + stack3);
        System.out.println("Stack4 （N-R) :| " + stack4);
        System.out.println("Stack5 （S-Z) :| " + stack5);
        System.out.println("Floor:| " + floor);
    }

    private static ArrayList<Package> tempList = new ArrayList<>();

    /**
     * This is a helper method that gets the package for a recipient.
     * @param stack         :
     *                      The stack that is being searched through.
     * @param name          :
     *                      The name of the recipient.
     * @throws EmptyStackException  :
     *                              EmptyStackException if stack is empty.
     * @throws FullStackException   :
     *                              FullStackException if stack is full.
     */
    public static void gettingPackage(PackageStack stack, String name) throws EmptyStackException, FullStackException {
        while(!stack.peek().getRecipient().equalsIgnoreCase(name)){
            Package p = stack.pop();
            tempList.add(p);
            floor.push(p);

        }
        System.out.println("Move " + tempList.size() + "from current stack to floor.");
        printStack();

       Package removingPackage = stack.pop();

        for (int i = tempList.size()-1; i > -1; i--){
            stack.push(tempList.remove(i));
            floor.pop();
        }
        System.out.println("Give " + name + " " + removingPackage.getWeight() +
                " lb package delivered on day " + removingPackage.getArrivalDate());

        printStack();
    }

    /**
     * This is a helper method that helps to move one package from one stack to another.
     * @param source        :
     *                      The source stack of the transferring process.
     * @return              :
     *                      The package to be moved from one stack to another.
     * @throws EmptyStackException  :
     *                      EmptyStackException if stack is empty.
     *
     */
    public static Package popPackage(int source) throws EmptyStackException {
        if (source == 1){
            return stack1.pop();
        }else if (source == 2){
            return stack2.pop();
        }else if (source == 3) {
            return stack3.pop();
        }else if (source == 4) {
            return stack4.pop();
        }else if (source == 5) {
            return stack5.pop();
        }else if (source == 0){
            return floor.pop();
        }else{
            return null;
        }
    }

    /**
     * This is another helper method for moving one package from one stack to another
     * @param p         :
     *                  The package that is taken out of the soruce stack.
     * @param destination   :
     *                      The destination stack of the package.
     * @throws FullStackException   :
     *                      FullStackException if the destination stack is full.
     */
    public static void pushPackage(Package p, int destination) throws FullStackException {
        if (destination == 1 ){
            stack1.push(p);
        }else if (destination == 2){
            stack2.push(p);
        }else if (destination == 3){
            stack3.push(p);
        }else if (destination == 4){
            stack4.push(p);
        }else if (destination == 5){
            stack5.push(p);
        }else if (destination == 0){
            floor.push(p);
        }
    }

    /**
     * This is a helper method that checks the displacement of packages.
     * @param stack         :
     *                      The stack that is being search through
     * @param start         :
     *                      The start of the alphabet range for the stack
     * @param end           :
     *                      The end of hte alphabet range for the stack
     * @throws EmptyStackException      :
     *                                  EmptyStackException when stack is empty.
     * @throws FullStackException       :
     *                                  FullStackException when the stack is full.
     */
    public static void checkMisplacement(PackageStack stack, String start, String end) throws EmptyStackException, FullStackException {
        ArrayList<Package> temp = new ArrayList<>();
        while (!stack.isEmpty()){
            if (start.compareToIgnoreCase(stack.peek().getRecipient()) <= 0 &&
                    stack.peek().getRecipient().compareToIgnoreCase(end) <=0 ){
                temp.add(stack.pop());
            }else{
                floor.push(stack.pop());
            }
        }
        for (int i = temp.size() - 1; i > -1; i--){
            stack.push(temp.get(i));
        }
    }

    /**
     * This method removes the packages that are over 5 days old in the stack, and returns the number of packages that is being removed.
     * @param days          :
     *                      The current date/day.
     * @param stack         :
     *                      The stack being search through.
     * @return              :
     *                      Returns the number of packages that are being removed.
     * @throws FullStackException           :
     *                                      FullStackException if the stack is full.
     * @throws EmptyStackException          :
     *                                      EmptyStackException if hte stack is empty.
     */
    public static int checkArrival(int days, PackageStack stack) throws FullStackException, EmptyStackException {
       int count = 0;
       ArrayList<Package> temp = new ArrayList<>();
       while (!stack.isEmpty()){
           if ((days - stack.peek().getArrivalDate()) < 5){
               temp.add(stack.pop());
           }else{
               stack.pop();
               count++;
           }
       }

        for (int i = temp.size() - 1; i > -1; i--){
            stack.push(temp.get(i));
        }

       return count;
    }

    /**
     * This method removes the packages that are over 5 days old in the floor stack, and returns the number of packages that is being removed.
     * @param day          :
     *                      The current date/day.
     * @param stack         :
     *                      The stack being search through.
     * @return              :
     *                      Returns the number of packages that are being removed.
     */
    public static int checkFloor(int day, Stack<Package> stack){
        int count = 0;
        ArrayList<Package> temp = new ArrayList<>();
        while (!stack.isEmpty()){
            if ((day - stack.peek().getArrivalDate()) < 5){
                temp.add(stack.pop());
            }else{
                stack.pop();
                count++;
            }
        }

        for (int i = temp.size() - 1; i > -1; i--){
            stack.push(temp.get(i));
        }
        return count;
    }

    static int arrivalDate = 0;
        /**
         * The main method runs a menu driven application which allows the user to create six instances of the PackageStack
         * class and then prompts the user for a menu command selecting the operation. The required information is then
         * requested from the user based on the selected operation.
         */
    public static void main(String[] args) {
        String option;
        do {
            displayMenu(menu);
            Scanner input = new Scanner(System.in);
            System.out.println("Please select an option: ");
            option = input.nextLine();
            switch (option.toUpperCase()) {

                case "D":
                    try{
                        System.out.println("Please enter the recipient name: ");
                        String name = input.nextLine();
                        System.out.println("Please enter the weight (lbs): ");
                        double weight = input.nextDouble();

                        deliverPackage(name,arrivalDate,weight);

                        System.out.println("A " + weight + " lb package is awaiting pickup by " + name);

                    }catch(FullStackException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "G":
                    try{
                        System.out.println("Please enter the recipient name: ");
                        String name = input.nextLine();

                        if (!listAllPackages(stack1, "stack 1", name, 0).isEmpty()){
                            gettingPackage(stack1, name);
                        }else if(!listAllPackages(stack2, "stack 2", name, 0).isEmpty()){
                            gettingPackage(stack2, name);
                        }else if(!listAllPackages(stack3, "stack 3", name, 0).isEmpty()){
                            gettingPackage(stack3, name);
                        }else if(!listAllPackages(stack4, "stack 4", name, 0).isEmpty()){
                            gettingPackage(stack4, name);
                        }else if(!listAllPackages(stack5, "stack 5", name, 0).isEmpty()){
                            gettingPackage(stack5, name);
                        }

                    }catch(FullStackException | EmptyStackException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "T":
                    try{
                        arrivalDate++;

                        int pkg1 = checkArrival(arrivalDate, stack1);
                        int pkg2 = checkArrival(arrivalDate, stack2);
                        int pkg3 = checkArrival(arrivalDate, stack3);
                        int pkg4 = checkArrival(arrivalDate, stack4);
                        int pkg5 = checkArrival(arrivalDate, stack5);
                        int flr = checkFloor(arrivalDate, floor);

                        int total = pkg1 + pkg2 + pkg3 + pkg4 + pkg5 + flr;
                        System.out.println("It is now day " + arrivalDate + ", " + total + " packages have been returned to sender.");

                    }catch(FullStackException | EmptyStackException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case "P":
                    printStack();
                    break;
                case "M":
                    try{
                        System.out.println("Please enter the source stack (enter 0 for floor): ");
                        int source = input.nextInt();
                        System.out.println("Please enter the destination stack: ");
                        int destination = input.nextInt();
                        pushPackage(popPackage(source), destination);

                    }catch(FullStackException | EmptyStackException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "F":
                    try{
                        checkMisplacement(stack1, "A", "G");
                        checkMisplacement(stack2, "H", "J");
                        checkMisplacement(stack3, "K", "M");
                        checkMisplacement(stack4, "N", "R");
                        checkMisplacement(stack5, "S", "Z");

                        System.out.println("Misplaced packages moved to floor.");

                    }catch(FullStackException | EmptyStackException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "L":
                    System.out.println("Please enter the recipient name: ");
                     String name = input.nextLine();

                    ArrayList <String> totalPackage = new ArrayList<>();

                    totalPackage.addAll(listAllPackages(stack1, "stack 1", name, 0)) ;
                    totalPackage.addAll(listAllPackages(stack2, "stack 2", name, totalPackage.size()));
                    totalPackage.addAll(listAllPackages(stack3, "stack 3", name, totalPackage.size()));
                    totalPackage.addAll(listAllPackages(stack4, "stack 4", name, totalPackage.size()));
                    totalPackage.addAll(listAllPackages(stack5, "stack 5", name, totalPackage.size()));
                    //totalPackage.addAll(listAllPackages(floor, "floor", name, totalPackage.size()));


                    System.out.println(name + " has " + totalPackage.size() + " in total.");
                    for (String s: totalPackage){
                        System.out.println(s);
                    }

                case "E":
                    while(!floor.isEmpty()){
                        floor.pop();
                    }

                    System.out.println("The floor has been emptied.");
                    break;

                case "Q":
                    System.out.println("Use Amazon Locker next time.");
            }
        } while (!option.equalsIgnoreCase("Q"));
    }
}
