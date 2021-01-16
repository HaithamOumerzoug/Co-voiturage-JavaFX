package application;
import java.sql.*;
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
			b=false;
		} 
		return b;
	}
	public Utilisateur getMesInf() {
		Utilisateur user = new Utilisateur();
		try {
			Statement st;
			st=con.createStatement();
			ResultSet resu;
			String sql = "select * from utilisateurs where Id_Utilisateur = '"+this.IdUser+"'";
			resu = st.executeQuery(sql);
			while(resu.next()) {
			           user = new Utilisateur(resu.getString("Nom"),resu.getString("Email"),cont.dechiffrer(resu.getString("Mot_de_passe")),resu.getString("Tel"),resu.getString("Adresse"));
			}	
		}catch(SQLException e) {
			             return user;
		}
		return user;
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
	public boolean AjouterOffer(String titre , float prix , String Date_depart , String Heure_depart,String Ville_depart,String Ville_arrive,int Nbr_places,String Bagage){
		try {
			Statement st;
			st = con.createStatement();
			if(Adm.getId_Admin() != 0) {
				String sql = "insert into offres(titre,prix,Date_depart,Heure_depart,Ville_depart,Ville_arrive,Nbr_places,Bagage,Id_Utilisateur,Id_Admin) values('"+titre+"','"+prix+"','"+Date_depart+"','"+Heure_depart+"','"+Ville_depart+"','"+Ville_arrive+"','"+Nbr_places+"','"+Bagage+"','"+this.IdUser+"','"+Adm.getId_Admin()+"')";
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
				 sql="select * from offres where Id_Utilisateur != '"+this.IdUser+"' and Nbr_places > 0";
			}
			resu = st.executeQuery(sql);
			return resu;
			}catch(SQLException e) {
			return null;
		}
	}
	 public boolean SupprimerOffer(long IdOffre){
	 	try{
	 		Statement st;
	 		st = con.createStatement();
	 		String sql00 = "delete from favoris where Id_offre = '"+IdOffre+"'";
	 		st.executeUpdate(sql00);
	 		String sql = "delete from  offres where Id_Utilisateur = '"+this.IdUser+"' and  Id_offer = '"+IdOffre+"'";
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
   public ResultSet Afficher_Mes_Favorites2(){
		 try {
			 Statement st;
			 st = con.createStatement();
			 ResultSet resu;
			 String sql = "SELECT * FROM offres WHERE Id_offer IN ( SELECT Id_offre FROM favoris WHERE Id_Utilisateur = '"+this.IdUser+"');";
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
	public ResultSet AfficherReservation() {
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql = "SELECT offres.Id_offer AS idOfr , prix , Titre , Ville_depart , Ville_arrive , Date_depart , Heure_depart , offres.Nbr_places AS nbrPlacesrest , reservations.Nbr_places AS nbrPlacesreserv , Bagage FROM offres,reservations WHERE reservations.Id_Utilisateur = '"+this.IdUser+"' AND reservations.Id_offer = offres.Id_offer";
			resu = st.executeQuery(sql);
			return resu;
			}catch(SQLException e) {
			return null;
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
	public int Nombre_offer_disp_pour(long idd) {
		int nbr=-1;
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu ;
			if (idd != -1) {
				String sql = "select count(*) as totale from offres where Id_Utilisateur != '"+idd+"'";
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
	public boolean favorisexist(long IdOffre) {
		int nbr=0;
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu ;
			if (this.IdUser != -1) {
				String sql = "select count(*) as totale from favoris where Id_Utilisateur = '"+this.IdUser+"' AND Id_offre = '"+IdOffre+"';";
				resu = st.executeQuery(sql);
				while(resu.next()) {
					nbr = resu.getInt("totale");
				}
				
			}else nbr = 0;
		}catch(SQLException e) {
			nbr = 0;
		}
		return nbr>0 ? true : false;
	}
	 public boolean supprFavoris(long IdOffre) {
		 if(favorisexist(IdOffre)) {
			 try {
				 Statement st;
				 st=con.createStatement();
				 
				 String sql = "DELETE FROM favoris WHERE Id_Utilisateur = '"+this.IdUser+"' AND Id_offre = '"+IdOffre+"';";
				 st.executeUpdate(sql);
				 return true;
				 }catch(SQLException e) {
					 return false;
				 }
		 }else return false;
	 }
	public boolean SupprimerMessages(String emailUser) {
		long IdUserDest = this.getIdOfEmail(emailUser);
		try{
	 		Statement st;
	 		st = con.createStatement();
	 		String sql = "delete from envoyer_messages where id_Utilisateurs_src = '"+IdUserDest+"' and id_Utilisateurs_dst = '"+this.IdUser+"'";
	 		st.executeUpdate(sql);
	 		return true;
	 	}catch(SQLException e){
	 		return false;
	 	}
		
	}
	public boolean ChangerUtilisateur(String nom,String email,String motdepasse,String tel,String Adresse) {
		boolean b=false;
		try {
			String motPassChifre = cont.chiffrer(motdepasse);
			Statement st;
			st = con.createStatement();
			String sql = "Update utilisateurs set Nom = '"+nom+"',Email='"+email+"',Mot_De_Passe='"+motPassChifre+"',Tel='"+tel+"',Adresse='"+Adresse+"' where Id_Utilisateur = '"+this.IdUser+"'";
			st.executeUpdate(sql);
			b=true;
		 }catch(SQLException e) {
			 b=false;
		} 
		return b;
	}
	public Offer getInfoOffer(String titre){
		Offer offer = new Offer();
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql=null;
			sql = "select * from offres where Id_Utilisateur = '"+this.IdUser+"' and Titre ='"+titre+"'";
			resu = st.executeQuery(sql);
			while(resu.next()) {
				offer = new Offer(resu.getLong("Id_Offer"),resu.getString("Titre"),resu.getFloat("prix"),resu.getString("Date_depart"),resu.getString("Heure_depart"),resu.getString("Ville_depart"),resu.getString("Ville_arrive"),resu.getInt("Nbr_Places"),resu.getString("bagage"));
			}
			
			}catch(SQLException e) {
			offer = null;
		}
		return offer;
	}
	public boolean ModdifierOffer(long id_offre,String titre ,float prix ,String date_depart ,String heure_depart,String ville_depart,String ville_arrive, int nbr_places, String bagage) {
		try {
			Statement st;
			st = con.createStatement();
			String sql = "Update offres set Titre ='"+titre+"',prix='"+prix+"',Date_depart='"+date_depart+"',Heure_depart='"+heure_depart+"',Ville_depart='"+ville_depart+"',Ville_arrive='"+ville_arrive+"',Nbr_places='"+nbr_places+"',Bagage='"+bagage+"' where Id_offer ='"+id_offre+"'" ;
			st.executeUpdate(sql);
			return true;
		
		}catch(SQLException e) {
			return false;
		}
		}
	public String GetNewMoPasse() {
		String NewPass = "";
		long nbr = (long) Math.floor(Math.random()*Math.ceil(Math.random()*1000));
		for(int i=0;i<3;i++) {
			NewPass = NewPass + nbr;
		}
		return NewPass;
	}
	public String getTitreOfferById(long id_offer) {
		String titre="";
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql=null;
			sql = "select Titre from offres where  Id_offer ='"+id_offer+"'";
			resu = st.executeQuery(sql);
			while(resu.next()) {
				titre = resu.getString("Titre");
			}
			
			}catch(SQLException e) {
				titre="";
			}
		return titre;
	}
	public String getNomUtilById(long id_utilisateur) {
		String nom="";
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql=null;
			sql = "select Nom from utilisateurs where  Id_Utilisateur ='"+id_utilisateur+"'";
			resu = st.executeQuery(sql);
			while(resu.next()) {
				nom = resu.getString("Nom");
			}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return nom;
	}
	public ResultSet getDemandesRecoit() {
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql=null;
			sql = "select * from reservations where  Id_Utilisateur !='"+this.IdUser+"'";
			resu = st.executeQuery(sql);
			return resu;
			
			}catch(SQLException e) {
				return null;
			}
	}
	public boolean SuppDemRec(long id_utilisateur , long id_offre) {
		Statement st;
		try {
			st = con.createStatement();
			String sql=null;
			sql = "delete from reservations where Id_Utilisateur ='"+id_utilisateur+"' and Id_offer ='"+id_offre+"'";
			st.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}
	public boolean SetNewPassword(long IdUtilisateur) {
		boolean b=false;
		try {
			String motPassChifre = cont.chiffrer(this.GetNewMoPasse());
			Statement st;
			st = con.createStatement();
			String sql = "Update utilisateurs set Mot_De_Passe='"+motPassChifre+"' where Id_Utilisateur = '"+IdUtilisateur+"'";
			st.executeUpdate(sql);
			b=true;
		 }catch(SQLException e) {
			 b=false;
		} 
		return b;
	}
	public String GetMyPassword(long idusr) {
		String password = "";
		try {
			Statement st;
			st = con.createStatement();
			ResultSet resu;
			String sql=null;
			sql = "select * from utilisateurs where Id_Utilisateur = '"+idusr+"'";
			resu = st.executeQuery(sql);
			while(resu.next()) {
				password = cont.dechiffrer(resu.getString("Mot_de_passe"));
			}
		}catch(SQLException e) {
			password = null;
		}
		return password;
	}
}
