package it.unisa.c03.myPersonalTrainer.subscription.bean;

/**
 * Subscription bean.
 */
public class Subscription {
    /**
     * customer's email.
     */
    private String customerMail;
    /**
     * expiration date of the subscription.
     */
    private String expDate;
    /**
     subscription price.
     */
    private String price;

    /**
     * Void constructor of the class.
     */
    public Subscription() {

    }
    /**
     * Standard constructor of the class.
     * @param customerEmail the customer email
     * @param date the expiration date of the subscription
     * @param cost the subscription price
     */
    public Subscription(String customerEmail, String date, String cost) {
        this.customerMail = customerEmail;
        this.expDate = date;
        this.price = cost;
    }

    /**
     * method for get the customer email.
     * @return the email
     */
    public String getCustomerMail() {
        return customerMail;
    }

    /**
     * method for set the email.
     * @param customerEmail email
     */
    public void setCustomerMail(String customerEmail) {
        this.customerMail = customerEmail;
    }

    /**
     * method for get the expiration date of the subscription.
     * @return the date
     */
    public String getExpDate() {
        return expDate;
    }

    /**
     * method for set the expiration date of the subscription.
     * @param date to set
     */
    public void setExpDate(String date) {
        this.expDate = date;
    }

    /**
     * method for get the subscription price.
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * method for set the subscription price.
     * @param cost to set
     */
    public void setPrice(String cost) {
        this.price = cost;
    }

    /**
     * method to string, to view the information during a stamp.
     */
    @Override
    public String toString() {
        return "Subscription{"
                + " eMail='" + customerMail + '\''
                + ", price='" + price + '\''
                + ", expDate=" + expDate
                + '}';
    }
}
