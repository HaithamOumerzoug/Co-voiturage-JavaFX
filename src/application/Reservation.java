package application;
public class Reservation {
	private long id_utilisateurs;
	private String nom_utilisateurs;
	private long id_offres;
	private String titre;
	private String message;
	private int nbr_places;
	public Reservation() {
		super();
	}
	public Reservation(long id_utilisateurs, String nom_utilisateurs, long id_offres, String titre, String message,
			int nbr_places) {
		super();
		this.id_utilisateurs = id_utilisateurs;
		this.nom_utilisateurs = nom_utilisateurs;
		this.id_offres = id_offres;
		this.titre = titre;
		this.message = message;
		this.nbr_places = nbr_places;
	}
	
	public long getId_utilisateurs() {
		return id_utilisateurs;
	}

	public void setId_utilisateurs(long id_utilisateurs) {
		this.id_utilisateurs = id_utilisateurs;
	}

	public long getId_offres() {
		return id_offres;
	}

	public void setId_offres(long id_offres) {
		this.id_offres = id_offres;
	}

	public String getNom_utilisateurs() {
		return nom_utilisateurs;
	}
	public void setNom_utilisateurs(String nom_utilisateurs) {
		this.nom_utilisateurs = nom_utilisateurs;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getNbr_places() {
		return nbr_places;
	}
	public void setNbr_places(int nbr_places) {
		this.nbr_places = nbr_places;
	}
	public Reservation(String nom_utilisateurs, String titre, String message, int nbr_places) {
		super();
		this.nom_utilisateurs = nom_utilisateurs;
		this.titre = titre;
		this.message = message;
		this.nbr_places = nbr_places;
	}
	
}