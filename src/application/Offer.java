package application;
import java.sql.Connection;
import java.sql.*;

public class Offer {
	private long Id_offer;
	private String titre;
	private float prix;
	private String Date_departe;
	private String heure_depart;
	private String Ville_depart;
	private String Ville_arrive;
	private int Nbr_places;
	private int Bagage;
	private long Id_Utilisateur;
	private int Id_Admin;
	public Offer() {
		super();
	}
	
	public Offer(long id_offer, String titre, float prix, String date_departe, String heure_depart, String ville_depart,
			String ville_arrive, int nbr_places, int bagage) {
		super();
		Id_offer = id_offer;
		this.titre = titre;
		this.prix = prix;
		Date_departe = date_departe;
		this.heure_depart = heure_depart;
		Ville_depart = ville_depart;
		Ville_arrive = ville_arrive;
		Nbr_places = nbr_places;
		Bagage = bagage;
	}
	
	public Offer(long id_offer, String titre, float prix, String ville_depart, String ville_arrive, int nbr_places,
			long id_Utilisateur) {
		super();
		Id_offer = id_offer;
		this.titre = titre;
		this.prix = prix;
		Ville_depart = ville_depart;
		Ville_arrive = ville_arrive;
		Nbr_places = nbr_places;
		Id_Utilisateur = id_Utilisateur;
	}
	public long getId_offer() {
		return Id_offer;
	}
	public void setId_offer(long id_offer) {
		Id_offer = id_offer;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getDate_departe() {
		return Date_departe;
	}
	public void setDate_departe(String date_departe) {
		Date_departe = date_departe;
	}
	public String getHeure_depart() {
		return heure_depart;
	}
	public void setHeure_depart(String heure_depart) {
		this.heure_depart = heure_depart;
	}
	public String getVille_depart() {
		return Ville_depart;
	}
	public void setVille_depart(String ville_depart) {
		Ville_depart = ville_depart;
	}
	public String getVille_arrive() {
		return Ville_arrive;
	}
	public void setVille_arrive(String ville_arrive) {
		Ville_arrive = ville_arrive;
	}
	public int getNbr_places() {
		return Nbr_places;
	}
	public void setNbr_places(int nbr_places) {
		Nbr_places = nbr_places;
	}
	public int getBagage() {
		return Bagage;
	}
	public void setBagage(int bagage) {
		Bagage = bagage;
	}
	public long getId_Utilisateur() {
		return Id_Utilisateur;
	}
	public void setId_Utilisateur(int id_Utilisateur) {
		Id_Utilisateur = id_Utilisateur;
	}
	public int getId_Admin() {
		return Id_Admin;
	}
	public void setId_Admin(int id_Admin) {
		Id_Admin = id_Admin;
	}
	public int Nombre_offres() {
		ConnexionMysql cont = new ConnexionMysql();
		Connection con = cont.ConnDB();
		int nbr=0;
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql="select count(*) As totale from Offres";
			resu = st.executeQuery(sql);
			while(resu.next()) {
				nbr = resu.getInt("totale");
			}
		}catch(SQLException e) {
			nbr=0;
		}
		return nbr;
	}
}