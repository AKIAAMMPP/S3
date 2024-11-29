package beans;

public class Technicien {
    private int id;
    private String nom;
    private String prenom;
    private String specialite;
    public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	private String experience;
    private String email;
    private String password;

    // Constructeur vide
    
    // Constructeur avec paramètres
    public Technicien(String nom, String prenom, String specialite,   String experience, String password, String email) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.experience = experience;
        this.email = email;
        this.password = password ;
    }
    public Technicien() {
    }

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

    
}