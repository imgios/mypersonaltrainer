package it.unisa.c03.myPersonalTrainer.account.bean;

public class Account {

    private String name;
    private String surname;
    private String phone;
    private String password;
    private String email;
    private int role;

    public Account(String name, String surname, String phone,
                   String email, String password, int role) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        role = 0;
    }

    public Account() { }

    public int getRole() {
        return role;
    }

    public void setRole(final int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
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
