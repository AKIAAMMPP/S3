package beans;

public class Intervention {
    private int id;
    private int demandeId;
    private int technicienId;
    private String dateIntervention;
    private String statut;
    private String rapport;
    private String note;
    private String commentaire;

    // Constructeur
    public Intervention(int id, int demandeId, int technicienId, String dateIntervention, String statut, String rapport, String note, String commentaire) {
        this.id = id;
        this.demandeId = demandeId;
        this.technicienId = technicienId;
        this.dateIntervention = dateIntervention;
        this.statut = statut;
        this.rapport = rapport;
        this.note = note;
        this.commentaire = commentaire;
    }

    // Constructeur sans paramètres (par défaut)
    public Intervention() {
        this.rapport = "NONE";
        this.note = "NONE";
        this.commentaire = "NONE";
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDemandeId() {
        return demandeId;
    }

    public void setDemandeId(int demandeId) {
        this.demandeId = demandeId;
    }

    public int getTechnicienId() {
        return technicienId;
    }

    public void setTechnicienId(int technicienId) {
        this.technicienId = technicienId;
    }

    public String getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(String dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
