/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 3
 * Recitation: 01
 */


/**
 * This is a fully-documented class named Package which represents the mail packages that are being delivered
 * and picked up from the mailroom.
 */
public class Package {
    private String recipient;
    private int arrivalDate;
    private double weight;

    /**
     * This is a getter method that gets the recipient of the package
     * @return      :
     *              Returns the recipient of the package
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * This is a getter method that gets the arrival date of the package
     * @return          :
     *                  returns the arrival date of the package
     */
    public int getArrivalDate() {
        return arrivalDate;
    }

    /**
     * This is the getter method that gets the weight of the package
     * @return          :
     *                  returns the weight of the package
     */
    public double getWeight() {
        return weight;
    }

    /**
     * This is a setter method that sets the recipient of the package.
     * @param recipient         :
     *                          The recipient of the package
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * This is a setter method that sets the arrival date of the package
     * @param arrivalDate           :
     *                              The arrival date of the package/
     */
    public void setArrivalDate(int arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * This is a setter method that sets the weight of the package
     * @param weight        :
     *                      The weight of the package
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Default constructor of the Package class.
     * @param recipient         :
     *                          The name of the package owner
     * @param arrivalDate       :
     *                          The arrival date of the package
     * @param weight            :
     *                          The total weight of the package
     * PostConditions           :
     *                          This object has been initialized to a package object with specified recipient,
     *                          arrival date and weight.
     */
    public Package(String recipient, int arrivalDate, double weight){
        this.recipient = recipient;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return  "[" + recipient + "     " + arrivalDate + "] ";
    }
}
