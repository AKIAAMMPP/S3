package beans;

public class Technicien {
    private int id;
    private String nom;
    private String prenom;
    private String specialite;
    private boolean disponibilite;
    private String experience;
    private String email;
    private String password;
    private String service;
    
    public Technicien(int id, String nom, String prenom, String email, String specialite, String adresse, String experience, boolean disponibilite) {
        // Initialisation des champs
    }


    // Constructeur avec paramètres
    public Technicien(int id, String nom, String prenom, String specialite, String experience, String password, String email, boolean disponibilite, String service) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.experience = experience;
        this.email = email;
        this.password = password;
        this.disponibilite = disponibilite;
        this.service = service;
    }

    // Constructeur vide
    public Technicien() {
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
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

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
    
    
    
}
