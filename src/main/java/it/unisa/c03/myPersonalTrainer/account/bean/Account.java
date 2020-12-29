package it.unisa.c03.myPersonalTrainer.account.bean;

import java.io.Serializable;

public class Account implements Serializable {
    //va implementata serializable?
    //dichiarazione delle variabili
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private int role;


    //getter-setter
    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String userSurname) {
        this.surname = userSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String userPhone) {
        this.phone = userPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String userEmail) {
        this.email = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int userRole) {
        this.role = userRole;
    }

    public Account() {
    }

    public Account(String name, String surname, String phone,
                   String email, String password, int role) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{"
                +
                "Name='"
                + name
                + '\''
                +
                ", Surname='"
                + surname
                + '\''
                +
                ", Phone='"
                + phone
                + '\''
                +
                ", Email='"
                + email
                + '\''
                +
                ", Password='"
                + password
                + '\''
                +
                ", Role="
                + role
                +
                '}';
    }
}
