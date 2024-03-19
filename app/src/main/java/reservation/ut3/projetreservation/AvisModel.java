package reservation.ut3.projetreservation;

public class AvisModel {
    private String nom;
    private String description;
    private String photo;

    public AvisModel() {
        // Constructeur par défaut requis pour Firebase Realtime Database
    }

    public AvisModel(String nom, String description, String photo) {
        this.nom = nom;
        this.description = description;
        this.photo = photo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
