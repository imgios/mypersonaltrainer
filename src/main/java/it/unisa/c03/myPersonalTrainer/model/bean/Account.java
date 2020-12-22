package it.unisa.c03.myPersonalTrainer.model.bean;

public class Account {
    //va implementata serializable?
    //dichiarazione delle variabili
    String nome;
    String cognome;
    String n_telefono;
    String email;
    String password;
    int ruolo;

    //getter-setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getN_telefono() {
        return n_telefono;
    }

    public void setN_telefono(String n_telefono) {
        this.n_telefono = n_telefono;
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

    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    //costruttore

    public Account() {
    }

    public Account(String nome, String cognome, String n_telefono, String email, String password, int ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.n_telefono = n_telefono;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }

    //tostring

    @Override
    public String toString() {
        return "Account{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", n_telefono='" + n_telefono + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ruolo=" + ruolo +
                '}';
    }
}
