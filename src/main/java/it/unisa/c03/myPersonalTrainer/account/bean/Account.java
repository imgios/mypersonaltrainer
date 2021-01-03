package it.unisa.c03.myPersonalTrainer.account.bean;

import java.io.Serializable;

/**
 * account class.
 */
public class Account implements Serializable {

    /**
     * user's name.
     */
    private String name;

    /**
     * user's surname.
     */
    private String surname;

    /**
     * user's phone.
     */
    private String phone;

    /**
     * user's email.
     */
    private String email;

    /**
     * user's password.
     */
    private String password;

    /**
     * attribute that construct the user into the db
     * role when is 1 the user is personal trainer when
     * for 0 is the user.
     */
    private int role;


    /**
     * method for get the name.
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * method for set the name.
     * @param newname name
     */
    public void setName(String newname) {
        this.name = newname;
    }

    /**
     * method for get the surname.
     * @return surname of account
     */
    public String getSurname() {
        return surname;
    }

    /**
     * method for set the surname.
     * @param newsurname new surname
     */
    public void setSurname(String newsurname) {
        this.surname = newsurname;
    }

    /**
     * method for get the phone.
     * @return phone phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * method for set the phone.
     * @param newphone phone
     */
    public void setPhone(String newphone) {
        this.phone = newphone;
    }

    /**
     * method for get the email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * method for set the email.
     * @param newemail email
     */
    public void setEmail(String newemail) {
        this.email = newemail;
    }

    /**
     * method for get the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * method for set the password.
     * @param newpassword password
     */
    public void setPassword(String newpassword) {
        this.password = newpassword;
    }

    /**
     * method for get the role.
     * @return role
     */
    public int getRole() {
        return role;
    }

    /**
     * method for set the role.
     * @param newrole role
     */
    public void setRole(int newrole) {
        this.role = newrole;
    }


    /**
     * constructor of the class.
     */
    public Account() {
    }

    /**
     * param costructor.
     * @param newname new name
     * @param newsurname new surname
     * @param newphone new phone
     * @param newemail new email
     * @param newpassword new passowrd
     * @param newrole new role
     */
    public Account(String newname, String newsurname,
                   String newphone, String newemail,
                   String newpassword, int newrole) {
        this.name = newname;
        this.surname = newsurname;
        this.phone = newphone;
        this.email = newemail;
        this.password = newpassword;
        this.role = newrole;
    }


    /**
     * method to string, to view the information during a stamp.
     */
    @Override
    public String toString() {
        return "Account{"
                + "Nome='" + name + '\''
                + ", Cognome='" + surname + '\''
                + ", Numero di Telefono='" + phone + '\''
                + ", eMail='" + email + '\''
                + ", password='" + password + '\''
                + ", ruolo=" + role
                + '}';
    }
}