package application;
import java.sql.*;
import java.util.ArrayList;
public class Utilisateur {
	private long Id_Utilisateur;
	private String nom;
	private String email;
	private String Mot_de_passe;
	private String Tel;
	private String Adresse;
	private long Id_Admin;
	public long IdUser = 0;
	Administrateur Adm = new Administrateur();
	ConnexionMysql cont = new ConnexionMysql();
	Connection con = cont.ConnDB();
	public Utilisateur() {
		super();
	}
	public Utilisateur(long Id,String nom,String email,String Mot_de_passe,String Tel,String Adresse) {
		super();
		this.Id_Utilisateur = Id;
		this.nom = nom;
		this.email = email;
		this.Mot_de_passe = Mot_de_passe;
		this.Tel = Tel;
		this.Adresse = Adresse;
	}
	public Utilisateur(String nom, String email, String mot_de_passe, String tel, String adresse) {
		super();
		this.nom = nom;
		this.email = email;
		Mot_de_passe = mot_de_passe;
		Tel = tel;
		Adresse = adresse;
	}
	public Utilisateur(long ID,String nom, String email, String tel, String adresse) {
		super();
		this.Id_Utilisateur = ID;
		this.nom = nom;
		this.email = email;
		this.Tel = tel;
		this.Adresse = adresse;
	}
	
	public long getId_Utilisateur() {
		return Id_Utilisateur;
	}
	public void setId_Utilisateur(long id_Utilisateur) {
		Id_Utilisateur = id_Utilisateur;
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
		return Mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		Mot_de_passe = mot_de_passe;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	public long getId_Admin() {
		return Id_Admin;
	}
	public void setId_Admin(long id_Admin) {
		Id_Admin = id_Admin;
	}
	public void setIdUser(long idUser) {
		IdUser = idUser;
	}
	public long MyId() {
		long MyId = 0;
		try {
			Statement st;
			ResultSet resu ;
			st = con.createStatement();
			String sql = "select * from utilisateurs";
			resu = st.executeQuery(sql);
			while(resu.next()) {
				MyId = resu.getLong("Id_Utilisateur");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return MyId + 1;
	}
	public boolean TestDeConUser(String email,String motpasse) {
			try {
				String mot_de_passe2 = cont.chiffrer(motpasse);
				Statement st;
				ResultSet rst;
				st = con.createStatement();
				String sql = "select * from utilisateurs where Email ='"+email+"' and Mot_de_passe = '"+mot_de_passe2+"';";
				rst = st.executeQuery(sql);
				if(rst.next()) {
					this.IdUser = rst.getLong("Id_Utilisateur");
					return true;
				}else {
					return false;
				}
			}catch(Exception ex) {
				return false;
			}
	}
	public long getIdUser() {
		return this.IdUser;
	}
	public boolean CreeCompteUtilisateur() {
		boolean b;
		try {
			String motdepasse = cont.chiffrer(this.Mot_de_passe);
			Statement st;
			st = con.createStatement();
			if(Adm.getId_Admin() != 0) {
				String sql = "insert into utilisateurs (Nom,Email,Mot_de_passe,Tel,Adresse,Id_Admin) values ('"+this.nom+"','"+this.email+"','"+motdepasse+"','"+this.Tel+"','"+this.Adresse+"','"+Adm.getId_Admin()+"');";
				st.executeUpdate(sql);
				b=true;
			}else {
				b=false;
			}
		 }catch(SQLException e) {
			//b=false;
			 e.printStackTrace();
			 b=false;
		} 
		return b;
	}
	public boolean Changer_Mot_De_Passe(String mot_de_passe) {
		boolean b=false;
		try {
			String motdepasse = cont.chiffrer(mot_de_passe);
			Statement st;
			st = con.createStatement();
			String sql = "Update utilisateurs set Mot_De_Passe='"+motdepasse+"'";
			st.executeUpdate(sql);
			b=true;
		 }catch(SQLException e) {
			b=false;
		} 
		return b;
	}
	public boolean AjouterOffer(String titre , float prix , String Date_depart , String Heure_depart,String Ville_depart,String Ville_arrive,int Nbr_places,int Bagage){
		try {
			Statement st;
			st = con.createStatement();
			if(Adm.getId_Admin() != 0) {
				String sql = "insert into Offres(titre,prix,Date_depart,Heure_depart,Ville_depart,Ville_arrive,Nbr_places,Bagage,Id_Utilisateur,Id_Admin) values('"+titre+"','"+prix+"','"+Date_depart+"','"+Heure_depart+"','"+Ville_depart+"','"+Ville_arrive+"','"+Nbr_places+"','"+Bagage+"','"+this.IdUser+"','"+Adm.getId_Admin()+"')";
				st.executeUpdate(sql);
				return true;
			}else {
				return true;
			}
		}catch(SQLException e) {
			return false;
		}
	}
	public ResultSet AfficherOffers(String c) {
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql=null;
			if(c=="my") {
				 sql = "select * from offres where Id_Utilisateur = '"+this.IdUser+"'";
			}else if(c=="all") {
				 sql="select * from offres where Id_Utilisateur != '"+this.IdUser+"'";
			}
			resu = st.executeQuery(sql);
			return resu;
			}catch(SQLException e) {
			return null;
		}
	}
	 public boolean SupprimerOffer(long IdOffer){
	 	try{
	 		Statement st;
	 		st = con.createStatement();
	 		String sql00 = "delete from favoris where Id_offre = '"+IdOffer+"'";
	 		st.executeUpdate(sql00);
	 		String sql = "delete from  offers where Id_Utilisateur = '"+this.IdUser+"' and  Id_offer = '"+IdOffer+"'";
	 		st.executeUpdate(sql);
	 		return true;
	 	}catch(SQLException e){
	 		return false;
	 	}
	 }
	 public boolean AjouterAuFavorites(long IdOffer) {
		 try {
			 Statement st;
			 st=con.createStatement();
			 String sql = "insert into favoris (Id_Utilisateur,Id_offre) values ('"+this.IdUser+"','"+IdOffer+"')";
			 st.executeUpdate(sql);
			 return true;
		 }catch(SQLException e) {
			 return false;
		 }
	 }
	 public ResultSet Afficher_Mes_Favorites(){
		 try {
			 Statement st;
			 st = con.createStatement();
			 ResultSet resu;
			 String sql = "select * from offres,favoris where favoris.Id_Utilisateur = offers.Id_Utilisateur and favoris.Id_Utilisateur='"+this.IdUser+"';";
			 resu = st.executeQuery(sql);
			 return resu;
		 }catch(SQLException e) {
			   return null;
		 }
	 }
	public int DemenderOffers(long IdOffres,int NbrDePlace,String message) {
		int nbr = -1;
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			int Le_Reste = 0 ;
			String sql = " select * from offres where Id_offer = '"+IdOffres+"';";
			resu = st.executeQuery(sql);
			while(resu.next()) {
				Le_Reste = resu.getInt("Nbr_places");
			}
			if(Le_Reste != 0 && NbrDePlace <= Le_Reste) {
				String sql1 = "Update offres set Nbr_places = '"+(Le_Reste-NbrDePlace)+"'where Id_offer ='"+IdOffres+"';";
				String sql2 = "insert into reservations (Id_Utilisateur,Id_offer,message,Nbr_places) values ('"+this.IdUser+"','"+IdOffres+"','"+message+"','"+NbrDePlace+"');";
				st.executeUpdate(sql1);
				st.executeUpdate(sql2);
				if(NbrDePlace == Le_Reste)  this.SupprimerOffer(IdOffres);
				nbr = 1;
			}else nbr = 0;
		}catch(SQLException e) {
			nbr = -1;
		}
		return nbr;
	}
	public boolean envoyer_message(String emailDest,String message) {
		try {
			Statement st;
			long IdDestination=this.getIdOfEmail(emailDest);
			st = con.createStatement();
			if(IdDestination != -1) {
				String sql = "insert into envoyer_messages (id_Utilisateurs_src,id_Utilisateurs_dst,contenue) values ('"+this.IdUser+"','"+IdDestination+"','"+message+"');";
				st.executeUpdate(sql);
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			    return false;
		}
	}
	public ResultSet afficher_les_message() {
		try{
			ResultSet resu;
			Statement st;
			st = con.createStatement();
			String sql = "select Email,contenue from utilisateurs,envoyer_messages where utilisateurs.Id_Utilisateur = envoyer_messages.id_Utilisateurs_src and Id_Utilisateurs_dst = '"+this.IdUser+"'";
			resu=st.executeQuery(sql);
			return resu;
		}catch(SQLException e) {
			return null;
		}
	}
	public ResultSet AficherUtilisateur() {
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql = "select * from utilisateurs where Id_Utilisateur != '"+this.IdUser+"'";
			resu = st.executeQuery(sql);
			return resu;
		}catch(SQLException e) {
			return null;
		}
		
	}
	public int Nombre_Utilisateur() {
		int nbr=0;
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql="select count(*) As totale from utilisateurs";
			resu = st.executeQuery(sql);
			while(resu.next()) {
				nbr = resu.getInt("totale");
			}
		}catch(SQLException e) {
			nbr=0;
		}
		return nbr;
	}
	public int Nombre_offer_pour(String email) {
		int nbr=-1;
		try {
			Statement st;
			st = con.createStatement();
			long IdUser = this.getIdOfEmail(email);
			ResultSet resu ;
			if (IdUser != -1) {
				String sql = "select count(*) as totale from offres where Id_Utilisateur = '"+IdUser+"'";
				resu = st.executeQuery(sql);
				while(resu.next()) {
					nbr = resu.getInt("totale");
				}
				
			}else nbr = -1;
		}catch(SQLException e) {
			nbr = -1;
		}
		return nbr;
	}
	public  long getIdOfEmail(String email) {
		long IdUser = -1;
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql = "select Id_Utilisateur from utilisateurs where Email = '"+email+"'";
			resu = st.executeQuery(sql);
			while(resu.next()) {
				IdUser = resu.getLong("Id_Utilisateur");
			}
		}catch(SQLException e) {
			IdUser = -1;
		}
		return IdUser;
	}
	public ArrayList<Utilisateur> getMesInf(){
		 ArrayList<Utilisateur> MesInf = new ArrayList<Utilisateur>();
		 try {
			 Statement st = con.createStatement();
			 ResultSet resu;
			 String sql="select * from utilisateurs where Id_Utilisateur ='"+this.IdUser+"'";
			 resu = st.executeQuery(sql);
			 while(resu.next()) {
				 MesInf.add(new Utilisateur(resu.getString("Nom"),resu.getString("Email"),resu.getString("Mot_de_passe"),resu.getString("Tel"),resu.getString("Adresse")));
			 }
		 }catch(SQLException e) {
			 MesInf = null;
		 }
		 return MesInf;
	 }
}