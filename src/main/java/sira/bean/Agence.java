	package sira.bean;
	
	public class Agence {
	
	    private int    id_agence;
	    private String titre;
	    private String adresse;
	    private int    cp;
	    private String ville;
	    private String description;
	    private String photo;
	
	    public Agence() {
	    }
	
	    public Agence( int id_agence, String titre, String adresse, int cp, String ville, String description,
	            String photo ) {
	        super();
	        this.id_agence = id_agence;
	        this.titre = titre;
	        this.adresse = adresse;
	        this.cp = cp;
	        this.ville = ville;
	        this.description = description;
	        this.photo = photo;
	    }
	
	    public int getId_agence() {
	        return id_agence;
	    }
	
	    public void setId_agence( int id_agence ) {
	        this.id_agence = id_agence;
	    }
	
	    public String getTitre() {
	        return titre;
	    }
	
	    public void setTitre( String titre ) {
	        this.titre = titre;
	    }
	
	    public String getAdresse() {
	        return adresse;
	    }
	
	    public void setAdresse( String adresse ) {
	        this.adresse = adresse;
	    }
	
	    public int getCp() {
	        return cp;
	    }
	
	    public void setCp( int cp ) {
	        this.cp = cp;
	    }
	
	    public String getVille() {
	        return ville;
	    }
	
	    public void setVille( String ville ) {
	        this.ville = ville;
	    }
	
	    public String getDescription() {
	        return description;
	    }
	
	    public void setDescription( String description ) {
	        this.description = description;
	    }
	
	    public String getPhoto() {
	        return photo;
	    }
	
	    public void setPhoto( String photo ) {
	        this.photo = photo;
	    }
	
	    @Override
	    public String toString() {
	        return "Agence [id_agence=" + id_agence + ", titre=" + titre + ", adresse=" + adresse + ", cp=" + cp
	                + ", ville=" + ville + ", description=" + description + ", photo=" + photo + "]";
	    }
	
	}
