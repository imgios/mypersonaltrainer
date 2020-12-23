package it.unisa.c03.myPersonalTrainer.Account.bean;

public class Account {

    private String name ;
    private String surname;
    private String email ;
    private String password ;
    private String telefono ;

    //costruttore

    public Account(String name, String surname, String email, String password, String telefono)
    {
        this.name = name;
        this.surname = surname ;
        this.email = email ;
        this.password = password ;
        this.telefono = telefono ;
    }

    public Account() {

    }

    //getter and setter

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // to String

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name +
                ", surname='" + surname +
                ", email='" + email +
                ", password='" + password +
                ", telefono=" + telefono +
                '}';
    }
}
