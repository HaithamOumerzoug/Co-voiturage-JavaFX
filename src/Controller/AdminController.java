package Controller;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;
//import com.mysql.jdbc.PreparedStatement;
import application.Administrateur;
import application.ConnexionMysql;
import application.Offer;
import application.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
public class AdminController implements Initializable {
    @FXML private Button GestionnaireOffres;
    @FXML private Button GestionnaireUsers;
    @FXML private Button exitButton;
    @FXML private Button DeconnexionAdm;
    @FXML private Button ModifierMesInf;
    @FXML private ImageView exitUser;
    @FXML private ImageView resetUser;
    @FXML private ImageView DeleteUser;
    @FXML private TextField adresseAdm;
    @FXML private TextField telephoneAdm;
    @FXML private TextField nomAdm;
    @FXML private TextField emailAdm;  
    @FXML private TextField MotDePasseAdm;
    @FXML private TableView<Utilisateur> tableUser;
    @FXML private TableView<Offer> tableOffre;
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
    @FXML private TextField EmailUserDel;
    @FXML private TextField IdOffreValue;
    @FXML private Label BonAdm;
    @FXML private AnchorPane RegisterBackground;
    @FXML
    public void toModifierMesInf() throws IOException {
    	DeconnexionAdm.getScene().getWindow().hide();
    	GestionnaireOffres.getScene().getWindow().hide();
    	GestionnaireUsers.getScene().getWindow().hide();
    	Stage ModifierInf = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/ModifierMesInf.fxml"));
    	Scene scene = new Scene(root);
    	ModifierInf.setTitle("Espace de l'utilisateur");
		ModifierInf.getIcons().add(new Image("file:../../Images/icon.png"));
    	ModifierInf.setScene(scene);
    	ModifierInf.show();
    }
    @FXML
    public void ModifierMesInformation() {
    	String reg="^$";
    	if(nomAdm.getText().matches(reg) || emailAdm.getText().matches(reg) || MotDePasseAdm.getText().matches(reg) || telephoneAdm.getText().matches(reg) || adresseAdm.getText().matches(reg)) {
    		Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("Attention.");
    		dialog.setContentText("l'un des champs est vide !!");
    		dialog.showAndWait();
    	}else if(!emailAdm.getText().substring(emailAdm.getText().length()-10, emailAdm.getText().length()).equals("@gmail.com")) {
	    	Alert dialog = new Alert(AlertType.INFORMATION);
	    	dialog.setTitle("EMAIL DANS L'EMAIL.");
	    	dialog.setHeaderText("Erreur de syntaxe.");
	    	dialog.setContentText("synatxe de l'email est invalide !!");
	    	dialog.showAndWait();
	    }else {
	    		Administrateur adm = new Administrateur(nomAdm.getText(),emailAdm.getText(),MotDePasseAdm.getText(),telephoneAdm.getText(),adresseAdm.getText());
	    		if(adm.ChangerAdministrateurs()) {
	    			Alert dialog = new Alert(AlertType.INFORMATION);
	        		dialog.setTitle("vous étes Modifier vous informations avec succes");
	        		dialog.setContentText("Les information sont bien enrgistrer:)");
	        		dialog.showAndWait();
	    		}else {
	    			Alert dialog = new Alert(AlertType.ERROR);
	        		dialog.setTitle("Erreur dans L'inscription.");
	        		dialog.setContentText("Les information son invalide !!");
	        		dialog.showAndWait();
	    		}
	    	}
    }
    public void ExitAdm() throws IOException {
    	LoginController loginController = new LoginController();
    	ModifierMesInf.getScene().getWindow().hide();
    	loginController.ChargerInterAdm();
    }
    public void Deconnexion() throws IOException {
    	LoginController loginController = new LoginController();
    	ModifierMesInf.getScene().getWindow().hide();
    	GestionnaireUsers.getScene().getWindow().hide();
    	GestionnaireOffres.getScene().getWindow().hide();
    	loginController.ChargerInterLogin();
    }
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
    	Administrateur admin = new Administrateur();
    	if(admin.Supprimer_Utilisateur(this.EmailUserDel.getText())) {
    		Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("");
    		dialog.setContentText("L'utilisateur est supprimé avec succes)");
    		dialog.showAndWait();
    	}else {
    		Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("");
    		dialog.setContentText("ce utilisateur n'exit pas");
    		dialog.showAndWait();
    	}
    }
    @FXML
    public void SupprimerOffre() {
    	LoginController loginController = new LoginController();
    	if(loginController.admin.Supprimer_Offer(Long.parseLong(this.IdOffreValue.getText()))) {
    		Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("");
    		dialog.setContentText("L'offre il  est supprimé avec succes)");
    		dialog.showAndWait();
    	}else {
    		Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("");
    		dialog.setContentText("cette offre n'exit pas");
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
			String sql = "SELECT  *  FROM Utilisateurs ";
			PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resu = stat.executeQuery();
			while(resu.next()) {
			   dataUser.add( new Utilisateur(resu.getLong("Id_Utilisateur"),resu.getString("Nom"),resu.getString("Email"),resu.getString("Tel"),resu.getString("Adresse")));
			}
		}catch(SQLException e) {
			Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("");
    		dialog.setContentText("probléme de connexion à la base de données");
    		dialog.showAndWait();
		}
    	IdUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,Long>("Id_Utilisateur"));
    	NomUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
    	EmailUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
    	TelUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Tel"));
    	AdresseUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("Adresse"));
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
			String sql = "SELECT  *  FROM Offres ";
			PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resu = stat.executeQuery();
			while(resu.next()) {
			   dataOffre.add( new Offer(resu.getLong("Id_Offer"),resu.getString("Titre"),resu.getFloat("prix"),resu.getString("Ville_depart"),resu.getString("Ville_arrive"),resu.getInt("Nbr_Places"),resu.getLong("Id_Utilisateur")));
			}
		}catch(SQLException e) {
			Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("");
    		dialog.setContentText("probléme de connexion à la base de données");
    		dialog.showAndWait();
		}
    	this.IdOffre.setCellValueFactory(new PropertyValueFactory<Offer,Long>("Id_offer"));
    	this.TitreOffre.setCellValueFactory(new PropertyValueFactory<Offer,String>("titre"));
    	this.Ville_depart.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_depart"));
    	this.Ville_arrive.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_arrive"));
    	this.PrixOffre.setCellValueFactory(new PropertyValueFactory<Offer,Float>("prix"));
    	this.NbrPlaceOffre.setCellValueFactory(new PropertyValueFactory<Offer,Integer>("Nbr_places"));
    	this.IdUser2.setCellValueFactory(new PropertyValueFactory<Offer,Long>("Id_Utilisateur"));
    	tableOffre.setItems(dataOffre);
    }
    @FXML
    public void exitToIntrAdm() throws IOException {
    	LoginController loginController = new LoginController();
    	DeleteUser.getScene().getWindow().hide();
    	loginController.ChargerInterAdm();
    }
    @FXML
    public void toSupprimerUser() throws IOException {
    	Stage gestionnaire = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/SupprimerUsers.fxml"));
    	Scene scene = new Scene(root);
    	gestionnaire.setTitle("Supprimer les Utilisateurs");
		gestionnaire.getIcons().add(new Image("file:../../Images/icon.png"));
    	gestionnaire.setScene(scene);
    	gestionnaire.show();
    }
    @FXML
    public void toSupprimerOffres() throws IOException {
    	Stage gestionnaire = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/SupprimerOffres.fxml"));
    	Scene scene = new Scene(root);
    	gestionnaire.setTitle("supprimer les offres");
		gestionnaire.getIcons().add(new Image("file:../../Images/icon.png"));
    	gestionnaire.setScene(scene);
    	gestionnaire.show();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(BonAdm != null) {
			LoginController loginController = new LoginController();
			BonAdm.setText("Bienvenue "+loginController.admin.getMesInf().get(0).getNom());
		}
		if(this.tableUser != null )  this.afficherUtilisateurs();
		if(this.tableOffre != null ) this.afficherOffres();
		if(this.RegisterBackground != null) {
			LoginController loginController = new LoginController();
			this.nomAdm.setText(loginController.admin.getMesInf().get(0).getNom());
			this.emailAdm.setText(loginController.admin.getMesInf().get(0).getEmail());
			this.MotDePasseAdm.setText(loginController.admin.getMesInf().get(0).getMot_de_passe());
			this.telephoneAdm.setText(loginController.admin.getMesInf().get(0).getTel());
			this.adresseAdm.setText(loginController.admin.getMesInf().get(0).getAdresse());
		}
	}
}
