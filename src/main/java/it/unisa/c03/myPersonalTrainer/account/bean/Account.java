package it.unisa.c03.myPersonalTrainer.account.bean;

public class Account {
    //va implementata serializable?
    //dichiarazione delle variabili
    String name;                       //name surname phone email password role
    String surname;
    String phone;
    String email;
    String password;
    int role;

    //getter-setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    //costruttore

    public Account() {
    }

    public Account(String name, String surname, String phone, String email, String password, int role) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //tostring

    @Override
    public String toString() {
        return "Account{" +
                "nome='" + name + '\'' +
                ", cognome='" + surname + '\'' +
                ", n_telefono='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ruolo=" + role +
                '}';
    }
}
