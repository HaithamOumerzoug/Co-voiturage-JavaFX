package Controller;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
//import com.mysql.jdbc.PreparedStatement;
import application.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminController implements Initializable {
	//Les attributs
	@FXML private boolean ShowPasse = false;
	
	//Button
	@FXML private Button GestionnaireOffres;
	@FXML private Button GestionnaireUsers;
	@FXML private Button exitButton;
    @FXML private Button DeconnexionAdm;
    @FXML private Button ModifierMesInf;
    
    //ImageView
    @FXML private ImageView exitUser;
    @FXML private ImageView resetUser;
    @FXML private ImageView eyeIcon;
    @FXML private ImageView eyeIcon1;
    @FXML private ImageView DeleteUser;
    @FXML private ImageView ResetPassword;
    
    //TextField
    @FXML private TextField adresseAdm;
    @FXML private TextField telephoneAdm;
    @FXML private TextField nomAdm;
    @FXML private TextField emailAdm;  
    @FXML private TextField MotDePasseAdm;
    @FXML private TextField MotDePasseAdm1;
    
    //TableView
    @FXML private TableView<Utilisateur> tableUser;
    @FXML private TableView<Offer> tableOffre;
    
    //TableColumn
    @FXML private TableColumn<Utilisateur,String> AdresseUser;
    @FXML private TableColumn<Utilisateur,String> EmailUser;
    @FXML private TableColumn<Utilisateur, String> TelUser;
    @FXML private TableColumn<Utilisateur, String> NomUser;
    @FXML private TableColumn<Utilisateur,Long> IdUser;
    @FXML private TableColumn<Offer,Long> IdOffre;
    @FXML private TableColumn<Offer,String> TitreOffre;
    @FXML private TableColumn<Offer, Float> PrixOffre;
    @FXML private TableColumn<Offer, String> Ville_depart;
    @FXML private TableColumn<Offer,String> Ville_arrive;
    @FXML private TableColumn<Offer,Integer> NbrPlaceOffre;
    @FXML private TableColumn<Offer,Long> IdUser2;
    
    //Label
    @FXML private Label BonAdm;
    //AnchorPane
    @FXML private AnchorPane RegisterBackground;
    
    //Les méthodes
    @FXML
    public void toModifierMesInf() throws IOException {
    	DeconnexionAdm.getScene().getWindow().hide();
    	GestionnaireOffres.getScene().getWindow().hide();
    	GestionnaireUsers.getScene().getWindow().hide();
    	Stage ModifierInf = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/ModifierMesInf.fxml"));
    	Scene scene = new Scene(root);
    	ModifierInf.setTitle("Utilisateur");
		ModifierInf.getIcons().add(new Image("file:../../Images/icon.png"));
    	ModifierInf.setScene(scene);
    	ModifierInf.show();
    }
    @FXML
    public void ModifierMesInformation() {
    	String reg="^$";
    	String regEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
    	String regTel="^0[5-7][0-9]+$";
    	if(nomAdm.getText().matches(reg) || emailAdm.getText().matches(reg) || MotDePasseAdm.getText().matches(reg) || telephoneAdm.getText().matches(reg) || adresseAdm.getText().matches(reg)) {
    		Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("Attention !");
    		dialog.setContentText("L'un des champs est vide !!");
    		dialog.showAndWait();
    	}else if(!emailAdm.getText().matches(regEmail)) {
	    	Alert dialog = new Alert(AlertType.ERROR);
	    	dialog.setTitle("ERREUR !");
	    	dialog.setHeaderText("Erreur du syntaxe.");
	    	dialog.setContentText("L'email est invalide !!");
	    	dialog.showAndWait();
	    }else if(!telephoneAdm.getText().matches(regTel)){
	    	Alert dialog = new Alert(AlertType.ERROR);
	    	dialog.setTitle("ERREUR !");
	    	dialog.setHeaderText("Erreur du syntaxe.");
	    	dialog.setContentText("Numéro de télephone est invalide !!");
	    	dialog.showAndWait();
	    }else{
	    	    Administrateur adm = null;
	    	    if(!this.ShowPasse)  adm = new Administrateur(nomAdm.getText(),emailAdm.getText(),MotDePasseAdm.getText(),telephoneAdm.getText(),adresseAdm.getText());
	    	    else    adm = new Administrateur(nomAdm.getText(),emailAdm.getText(),MotDePasseAdm1.getText(),telephoneAdm.getText(),adresseAdm.getText()); 
	    	    if(adm.ChangerAdministrateurs()) {
	    			Alert dialog = new Alert(AlertType.INFORMATION);
	        		dialog.setTitle("");
	        		dialog.setContentText("Les informations sont bien enrgistrer :)");
	        		dialog.showAndWait();
	    		}else {
	    			Alert dialog = new Alert(AlertType.ERROR);
	        		dialog.setTitle("ERREUR !");
	        		dialog.setContentText("Les informations son invalide !!");
	        		dialog.showAndWait();
	    		}
	    	}
    }
    @FXML
    public void SetNewPassword() {
    	Utilisateur user = this.tableUser.getSelectionModel().getSelectedItem();
    	if(user != null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation de suppression ");
			alert.setContentText("Voulez-vous vraiment rénisialiser le mot de passe de l'utilisateur "+user.getNom()+" ?");
			Optional<ButtonType> result = alert.showAndWait();
		    if(result.get() == ButtonType.OK) {
		    	if(user.SetNewPassword(user.getId_Utilisateur()) && SendEmail.Send(user.getEmail()," Bonjour "+user.getNom()+" votre noveau mot de passe est : "+user.GetMyPassword(user.getId_Utilisateur())+" Svp Inistialiser le mot de passe.","le nouveau mot de passe")) {
	    			 Alert dialog = new Alert(AlertType.INFORMATION);
	        		 dialog.setTitle("Succés !");
	        		 dialog.setContentText("Le nouveau mot de passe de l'utilisateur est bien enregistrer ;)");
	        		 dialog.showAndWait();
	    		}else {
	    			 Alert dialog = new Alert(AlertType.INFORMATION);
	        		 dialog.setTitle("Pas de Connexion ");
	        		 dialog.setContentText("vous les problémes de connexion .");
	        		 dialog.showAndWait();
	    		}
		   }
    	}else {
    		 Alert dialog = new Alert(AlertType.INFORMATION);
    		 dialog.setTitle("Erreur");
    		 dialog.setContentText("Aucune utilisateur séléctionnée");
    		 dialog.showAndWait(); 
    	}
    }
    @FXML
    public void ShowPassword() {
    	this.MotDePasseAdm.setVisible(false);
    	this.eyeIcon.setVisible(false);
    	this.MotDePasseAdm1.setVisible(true);
    	this.eyeIcon1.setVisible(true);
    	this.ShowPasse = true;
    	MotDePasseAdm1.setText(MotDePasseAdm.getText());
    }
    @FXML
    public void DShowPassword() {
    	this.MotDePasseAdm.setVisible(true);
    	this.eyeIcon.setVisible(true);
    	this.MotDePasseAdm1.setVisible(false);
    	this.eyeIcon1.setVisible(false);
    	this.ShowPasse = false;
    	MotDePasseAdm.setText(MotDePasseAdm1.getText());
    }
    @FXML
    public void ExitAdm() throws IOException {
    	LoginController loginController = new LoginController();
    	ModifierMesInf.getScene().getWindow().hide();
    	loginController.ChargerInterAdm();
    }
    @FXML
    public void Deconnexion() throws IOException {
    	LoginController loginController = new LoginController();
    	ModifierMesInf.getScene().getWindow().hide();
    	GestionnaireUsers.getScene().getWindow().hide();
    	GestionnaireOffres.getScene().getWindow().hide();
    	loginController.ChargerInterLogin();
    }
    @FXML
    public void toGestionnaireUsers() throws IOException {
    	DeconnexionAdm.getScene().getWindow().hide();
    	GestionnaireOffres.getScene().getWindow().hide();
    	ModifierMesInf.getScene().getWindow().hide();
    	Stage gestionnaire = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/GestionnaireUtilisateur.fxml"));
    	Scene scene = new Scene(root);
    	gestionnaire.setTitle("Gestionnaire");
		gestionnaire.getIcons().add(new Image("file:../../Images/icon.png"));
    	gestionnaire.setScene(scene);
    	gestionnaire.show();
    }
    @FXML
    public void toGestionnaireOffres() throws IOException{
    	DeconnexionAdm.getScene().getWindow().hide();
    	GestionnaireUsers.getScene().getWindow().hide();
    	ModifierMesInf.getScene().getWindow().hide();
    	Stage gestionnaire = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/GestionnaireOffres.fxml"));
    	Scene scene = new Scene(root);
    	gestionnaire.setTitle("Gestionnaire");
		gestionnaire.getIcons().add(new Image("file:../../Images/icon.png"));
    	gestionnaire.setScene(scene);
    	gestionnaire.show();
    }
    @FXML
    public void SupprimerUtilisateurs() {
    	Utilisateur user = this.tableUser.getSelectionModel().getSelectedItem();
    	if(user != null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation de suppression ");
			alert.setContentText("Voulez-vous vraiment supprimer l'utilisateur  "+user.getNom()+" ?");
			Optional<ButtonType> result = alert.showAndWait();
		    if(result.get() == ButtonType.OK) {
			   if(LoginController.admin.Supprimer_Utilisateur(user.getEmail())) {
				 this.afficherUtilisateurs();
			   }
		   }
    	}else {
    		Alert dialog = new Alert(AlertType.INFORMATION);
   		    dialog.setTitle("Erreur");
   		    dialog.setContentText("Aucune utilisateur séléctionnée");
   		    dialog.showAndWait(); 
    	}
    }
    @FXML
    public void SupprimerOffre() {
    	Offer offer = this.tableOffre.getSelectionModel().getSelectedItem();
    	if(offer != null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation de suppression ");
			alert.setContentText("Voulez-vous vraiment supprimer l'offre  "+offer.getTitre()+" ?");
			Optional<ButtonType> result = alert.showAndWait();
		   if(result.get() == ButtonType.OK) {
			  if(LoginController.admin.Supprimer_Offer(offer.getId_offer())) {
				 this.afficherOffres();
			  }
		   }
    	}else {
    		Alert dialog = new Alert(AlertType.INFORMATION);
   		    dialog.setTitle("Erreur");
   		    dialog.setContentText("Aucune offre séléctionnée");
   		    dialog.showAndWait();
    	}
    }
    @FXML
    public ObservableList<Utilisateur> dataUser = FXCollections.observableArrayList();
    @FXML
    public void afficherUtilisateurs() {
    	for(int i=0;i<dataUser.size();i++) dataUser.remove(0);
    	for ( int i = 0; i<tableUser.getItems().size(); i++) tableUser.getItems().clear();
    	try {
    		ConnexionMysql cont = new ConnexionMysql();
        	Connection con = cont.ConnDB();
			String sql = "SELECT  *  FROM utilisateurs ";
			PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resu = stat.executeQuery();
			while(resu.next()) {
			   dataUser.add( new Utilisateur(resu.getLong("Id_Utilisateur"),resu.getString("Nom"),resu.getString("Email"),resu.getString("Tel"),resu.getString("Adresse")));
			}
		}catch(SQLException e) {
			Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("");
    		dialog.setContentText("Problème detecté !");
    		dialog.showAndWait();
		}
    	IdUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,Long>("Id_Utilisateur"));
    	NomUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
    	EmailUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
    	TelUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Tel"));
    	AdresseUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Adresse"));
    	Tooltip.install(this.resetUser, new Tooltip("Actualiser"));
    	Tooltip.install(this.DeleteUser, new Tooltip("Supprimer"));
    	Tooltip.install(this.exitUser, new Tooltip("Sortir"));
    	Tooltip.install(this.ResetPassword, new Tooltip("Réinitialiser mot de passe"));
    	tableUser.setItems(dataUser);
    }
    @FXML
    public ObservableList<Offer> dataOffre = FXCollections.observableArrayList();
    @FXML
    public void afficherOffres() {
    	for(int i=0;i<dataOffre.size();i++) dataOffre.remove(0);
    	for ( int i = 0; i<tableOffre.getItems().size(); i++) tableOffre.getItems().clear();
    	try {
    		ConnexionMysql cont = new ConnexionMysql();
        	Connection con = cont.ConnDB();
			String sql = "SELECT  *  FROM offres ";
			PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resu = stat.executeQuery();
			while(resu.next()) {
			   dataOffre.add( new Offer(resu.getLong("Id_Offer"),resu.getString("Titre"),resu.getFloat("prix"),resu.getString("Ville_depart"),resu.getString("Ville_arrive"),resu.getInt("Nbr_Places"),resu.getLong("Id_Utilisateur")));
			}
		}catch(SQLException e) {
			Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("");
    		dialog.setContentText("Problème detecté !");
    		dialog.showAndWait();
		}
    	this.IdOffre.setCellValueFactory(new PropertyValueFactory<Offer,Long>("Id_offer"));
    	this.TitreOffre.setCellValueFactory(new PropertyValueFactory<Offer,String>("titre"));
    	this.Ville_depart.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_depart"));
    	this.Ville_arrive.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_arrive"));
    	this.PrixOffre.setCellValueFactory(new PropertyValueFactory<Offer,Float>("prix"));
    	this.NbrPlaceOffre.setCellValueFactory(new PropertyValueFactory<Offer,Integer>("Nbr_places"));
    	this.IdUser2.setCellValueFactory(new PropertyValueFactory<Offer,Long>("Id_Utilisateur"));
    	Tooltip.install(this.resetUser, new Tooltip("Actualiser"));
    	Tooltip.install(this.DeleteUser, new Tooltip("Supprimer"));
    	Tooltip.install(this.exitUser, new Tooltip("Sortir"));
    	
    	
    	tableOffre.setItems(dataOffre);
    }
    @FXML
    public void exitToIntrAdm() throws IOException {
    	LoginController loginController = new LoginController();
    	DeleteUser.getScene().getWindow().hide();
    	loginController.ChargerInterAdm();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(BonAdm != null) {
			BonAdm.setText("Bienvenue "+LoginController.admin.getMesInf().get(0).getNom());
		}
		if(this.tableUser != null )  this.afficherUtilisateurs();
		if(this.tableOffre != null ) this.afficherOffres();
		if(this.RegisterBackground != null) {
			this.nomAdm.setText(LoginController.admin.getMesInf().get(0).getNom());
			this.emailAdm.setText(LoginController.admin.getMesInf().get(0).getEmail());
			this.MotDePasseAdm.setText(LoginController.admin.getMesInf().get(0).getMot_de_passe());
			this.telephoneAdm.setText(LoginController.admin.getMesInf().get(0).getTel());
			this.adresseAdm.setText(LoginController.admin.getMesInf().get(0).getAdresse());
			this.MotDePasseAdm1.setVisible(false);
			this.eyeIcon1.setVisible(false);
		}
	}
}
