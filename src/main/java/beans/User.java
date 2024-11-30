package beans;

public class User {
    private int id;
    private String email;
    private String typeUser;
    
    // Constructeur modifié pour inclure l'ID
    public User(int id, String email, String typeUser) {
        this.id = id;
        this.email = email;
        this.typeUser = typeUser;
    }
    
    // Getters et setters pour tous les attributs
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
