package sira.bean;

public class Membre {

    private int    id_membre;
    private String civility;
    private String prenom;
    private String nom;
    private String pseudo;
    private String mdp;
    private String email;
    private String statut;
    private String date_enregistrement;

    public Membre() {
    }

    public Membre( int id_membre, String civility, String prenom, String nom, String pseudo, String mdp, String email,
            String statut, String date ) {
        super();
        this.id_membre = id_membre;
        this.civility = civility;
        this.prenom = prenom;
        this.nom = nom;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.email = email;
        this.statut = statut;
        this.date_enregistrement = date;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre( int id_membre ) {
        this.id_membre = id_membre;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility( String civility ) {
        this.civility = civility;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp( String mdp ) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut( String statut ) {
        this.statut = statut;
    }

    public String getDate_enregistrement() {
        return date_enregistrement;
    }

    public void setDate_enregistrement( String date_enregistrement ) {
        this.date_enregistrement = date_enregistrement;
    }

    @Override
    public String toString() {
        return "Membre [id_membre=" + id_membre + ", civility=" + civility + ", prenom=" + prenom + ", nom=" + nom
                + ", pseudo=" + pseudo + ", mdp=" + mdp + ", email=" + email + ", statut=" + statut
                + ", date_enregistrement=" + date_enregistrement + "]";
    }

}
