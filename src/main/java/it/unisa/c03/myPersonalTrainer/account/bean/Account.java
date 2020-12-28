package it.unisa.c03.myPersonalTrainer.account.bean;

import java.io.Serializable;
public final class Account implements Serializable {
    /**
     * @param name represent the name of Account
     */
    private String name;
    /**
     * @param surname represent the surname of Account
     */
    private String surname;
    /**
     * @param phone represent telephone's number of Account
     */
    private String phone;
    /**
     * @param password represent the password of Account
     */
    private String password;
    /**
     * @param email represent the email of Account
     */
    private String email;
    /**
     * @param role represent the role of Account
     */
    private int role;
     /**
     * For this constructor of Account we'll use the following parameters.
     * @param newName represent the name of Account
     * @param newSurname represent the surname of Account
     * @param newPhone represent telephone's number of Account
     * @param newEmail represent the email of Account
     * @param newPassword represent the password of Account
     * @param newRole represent the role of Account
     */
    public Account(String newName, String newSurname, String newPhone,
                   String newEmail, String newPassword, int newRole) {
        this.name = newName;
        this.surname = newSurname;
        this.phone = newPhone;
        this.email = newEmail;
        this.password = newPassword;
        this.role = newRole;
    }
    /**
     * Another constructor of Account.
     */
    public Account() { }
    /**
     * Method to obtain Account's role.
     * @return role of Account.
     */
    public int getRole() {
        return role;
    }
    /**
     * Method to set Account's role.
     * @param newRole
     */
    public void setRole(final int newRole) {
        this.role = newRole;
    }
    /**
     * Method to obtain the Account's name.
     * @return name of Account.
     */
    public String getName() {
        return name;
    }
    /**
     * Method to set Account's name.
     * @param newName
     */
    public void setName(final String newName) {
        this.name = newName;
    }
    /**
     * Method to obtain the Account's surname.
     * @return surname of Account.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Method to set Account's surname.
     * @param newSurname
     */
    public void setSurname(final String newSurname) {
        this.surname = newSurname;
    }
    /**
     * Method to obtain the Account's phone.
     * @return phone of Account.
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Method to set Account's phone.
     * @param newPhone
     */
    public void setPhone(final String newPhone) {
        this.phone = newPhone;
    }
    /**
     * Method to obtain the Account's password.
     * @return password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Method to set Account's password.
     * @param newPassword
     */
    public void setPassword(final String newPassword) {
        this.password = newPassword;
    }
    /**
     * Method to obtain Account's email.
     * @return email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Method to set Account's email.
     * @param newEmail
     */
    public void setEmail(final String newEmail) {
        this.email = newEmail;
    }
    @Override
    public String toString() {
        return "Account{"
                + "  name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", phone=" + phone
                + ", password='" + password + '\''
                + ", email='" + email + '\''
                + ", role='" + role + '\''
                + '}';
    }
}
