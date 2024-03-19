package reservation.ut3.projetreservation;

import java.io.Serializable;

public class RestaurantModel implements Serializable {
    private String id;
    private String nom;
    private String adresse;
    private String type ;
    private String prixMoyen ;
    private String moyensPaiement ;
    private String services ;
    private String caracteristiques ;
    private String transports ;
    private String avis ;
    private String imageRef;
    private String imagesDetails;
    private AvisModel avisResto;

    public RestaurantModel() {
    }


    public RestaurantModel(String id, String nom, String adresse, String type, String prixMoyen, String moyensPaiement, String services, String caracteristiques, String transports, String avis) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.prixMoyen = prixMoyen;
        this.moyensPaiement = moyensPaiement;
        this.services = services;
        this.caracteristiques = caracteristiques;
        this.transports = transports;
        this.avis = avis;
        //this.imageRef = imageRef;
    }

    public String getId() {
        return id;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getNom() {
        return nom;
    }
    public String getType() {
        return type;
    }
    public String getPrixMoyen() {
        return prixMoyen;
    }
    public String getMoyensPaiement() {
        return moyensPaiement;
    }
    public String getServices() {
        return services;
    }
    public String getCaracteristiques() {
        return caracteristiques;
    }
    public String getTransports() {
        return transports;
    }
    public String getAvis() {
        return avis;
    }
    public String getImageRef() {
        return imageRef;
    }
    public String getImagesDetails() {
        return imagesDetails;
    }
    void setId(String id) {
        this.id = id;
    }
    void setNom(String nom) {
        this.nom = nom;
    }
    void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    void setType(String type) {
        this.type = type;
    }
    void setPrixMoyen(String prixMoyen) {
        this.prixMoyen = prixMoyen;
    }
    void setMoyensPaiement(String moyensPaiement) {
        this.moyensPaiement = moyensPaiement;
    }
    void setServices(String services) {
        this.services = services;
    }
    void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }
    void setTransports(String transports) {
        this.transports = transports;
    }
    void setAvis(String avis) {
        this.avis = avis;
    }
    void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }
    void setImagesDetails(String imagesDetails) {
        this.imagesDetails = imagesDetails;
    }

}
