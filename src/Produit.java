import java.io.Serializable;

public class Produit implements Serializable {

	/**
	 * 
	 */
	private String identifiant,nom;
	private int prix;
	private static final long serialVersionUID = -4493386577010758351L;
	public Produit(String identifiant,String nom,int prix) {
		this.identifiant=identifiant;
		this.nom=nom;
		this.prix=prix;
		
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public String getNom() {
		return nom;
	}
	public int getPrix() {
		return prix;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
