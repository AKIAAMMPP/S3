package beans;

public class Demande {
    private int id;
    private int clientId;
    private int serviceId;
    private String demandeDescription;
    private String statut;

    // Constructeur
    public Demande(int id, int clientId, int serviceId, String demandeDescription, String statut) {
        this.id = id;
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.demandeDescription = demandeDescription;
        this.statut = statut;
    }

    // Constructeur sans paramètres (par défaut)
    public Demande() {
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getDemandeDescription() {
        return demandeDescription;
    }

    public void setDemandeDescription(String demandeDescription) {
        this.demandeDescription = demandeDescription;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
