package it.unisa.c03.myPersonalTrainer.model.bean;

public class Account {

    private String nome ;
    private String cognome ;
    private String email ;
    private String password ;
    private int telefono ;

    //costruttore

    public Account(String nome, String cognome, String email, String password, int telefono)
    {
        this.nome = nome;
        this.cognome = cognome ;
        this.email = email ;
        this.password = password ;
        this.telefono = telefono ;
    }

    //getter and setter

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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    // to String

    @Override
    public String toString() {
        return "Customer{" +
                "nome='" + nome +
                ", cognome='" + cognome +
                ", email='" + email +
                ", password='" + password +
                ", telefono=" + telefono +
                '}';
    }
}
