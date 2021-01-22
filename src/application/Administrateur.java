package application;
import java.lang.String;
import java.sql.*;
import java.util.*;
public class Administrateur {
	//private long id;
	private String nom;
	private String email;
	private String mot_de_passe;
	private String tel;
	private String adresse ;
	ConnexionMysql cont= new ConnexionMysql();
	Connection con = cont.ConnDB();
	public Administrateur() {
		super();
	}
	public Administrateur(String nom, String email, String mot_de_passe, String tel, String adresse) {
		super();
		this.nom = nom;
		this.email = email;
		this.mot_de_passe = mot_de_passe;
		this.tel = tel;
		this.adresse = adresse;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public long  getId_Admin() {
		long a=0;
		try {
			ResultSet rst;
			Statement st;
			st = con.createStatement();
			String sql = "select * from administrateurs ;";
			rst = st.executeQuery(sql);
			while(rst.next()) {
			    a = rst.getLong("Id_Admin");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return a;
	}
	public boolean TestDeConnexion(String email,String passwrd) {
		try {
			String passwrd2 = cont.chiffrer(passwrd);
			Statement st;
			ResultSet rst;
			st = con.createStatement();
			String sql = "select * from administrateurs where Email ='"+email+"' and Mot_de_passe = '"+passwrd2+"';";
			rst = st.executeQuery(sql);
			if(rst.next()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception ex) {
			    return false;
		}
	}
	public boolean ChangerAdministrateurs() {
		boolean b=false;
		try {
			String motdepasse = cont.chiffrer(this.mot_de_passe);
			Statement st;
			st = con.createStatement();
			String sql = "Update administrateurs set Nom = '"+this.nom+"',Email='"+this.email+"',Mot_De_Passe='"+motdepasse+"',Tel='"+this.tel+"',Adresse='"+this.adresse+"'";
			st.executeUpdate(sql);
			b=true;
		 }catch(SQLException e) {
			b=false;
		} 
		return b;
	} 
	public boolean Supprimer_Utilisateur(String email) {
		Utilisateur user = new Utilisateur();
		long IdUser = user.getIdOfEmail(email);
		try {
			if(IdUser != -1) {
				Statement st;
				st = con.createStatement();
				String sql0 = "delete from envoyer_messages where id_Utilisateurs_src ='"+IdUser+"' or id_Utilisateurs_dst= '"+IdUser+"'";
				st.executeUpdate(sql0);
				String sql01 = "delete from reservations where Id_Utilisateur = '"+IdUser+"'";
				st.executeUpdate(sql01);
				String sql1 = "delete from offres where Id_Utilisateur = '"+IdUser+"'";
				st.executeUpdate(sql1);
				String sql11 = " delete from favoris where Id_Utilisateur = '"+IdUser+"'";
				st.executeUpdate(sql11);
				String sql = "delete from  utilisateurs where Email = '"+email+"'";
				st.executeUpdate(sql);
			return true;
			}else return false;
		}catch(SQLException e) {
			return  false;
		}
	}
	 public boolean  Supprimer_Offer(long IdOffer){
		 	try{
		 		Statement st;
		 		st = con.createStatement();
		 		String sql00 = "delete from favoris where Id_offre = '"+IdOffer+"'";
		 		st.executeUpdate(sql00);
		 		String sql0 = "delete from reservations where Id_offer = '"+IdOffer+"'";
		 		st.executeUpdate(sql0);
		 		String sql = "delete from  offres where Id_Offer = '"+IdOffer+"'";
		 		st.executeUpdate(sql);
		 		return true;
		 	}catch(SQLException e){
		 		e.printStackTrace();
		 		return false;
		 	}
		 }
	 public ArrayList<Administrateur> getMesInf(){
		 ArrayList<Administrateur> MesInf = new ArrayList<Administrateur>();
		 try {
			 Statement st = con.createStatement();
			 ResultSet resu;
			 String sql="select * from administrateurs";
			 resu = st.executeQuery(sql);
			 while(resu.next()) {
				 MesInf.add(new Administrateur(resu.getString("Nom"),resu.getString("Email"),cont.dechiffrer(resu.getString("Mot_de_passe")),resu.getString("Tel"),resu.getString("Adresse")));
			 }
		 }catch(SQLException e) {
			 MesInf = null;
		 }
		 return MesInf;
	 }
}
