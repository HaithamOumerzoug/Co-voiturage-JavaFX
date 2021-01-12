package Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

//import com.mysql.jdbc.PreparedStatement;

import application.ConnexionMysql;
import application.Message;
import application.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
public class UserController implements Initializable{
	@FXML private Button OffreU;
	@FXML private Button EspU;
	@FXML private Button MessageU;
	@FXML private Button DeconnexionU;
	@FXML private TextField EmailDest;
	@FXML private TextArea MessageEnv;
	@FXML private TableView<Message> tableMessages;
	@FXML private ImageView exitUser;
	@FXML private ImageView envoyer_message;
	@FXML private ImageView resetMessage;
	@FXML private ImageView DeleteMessage;
	@FXML private TableColumn<Message, String> emailSrc;
	@FXML private TableColumn<Message,String> Contenue;
	@FXML private Button Profile;
	@FXML private Button MesOffre;
	@FXML private Button MesDem;
	@FXML private Label welcome = new Label();
	@FXML private Label NomUsr = new Label();
	@FXML private Label EmailU = new Label();
	@FXML private Label TelU = new Label();
	@FXML private Label AddrU = new Label();
	@FXML private Button retour;
	@FXML private Button modifier;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Inisialiser les infos d'utilisateur
		LoginController loginController = new LoginController();
		this.welcome.setText("Bienvenue "+loginController.user.getMesInf().get(0).getNom());
		this.NomUsr.setText(loginController.user.getMesInf().get(0).getNom());
		this.EmailU.setText(loginController.user.getMesInf().get(0).getEmail());
		this.TelU.setText(loginController.user.getMesInf().get(0).getTel());
		this.AddrU.setText(loginController.user.getMesInf().get(0).getAdresse());
		
	}
	
	@FXML
	public void ExitInterLogin() throws IOException {
		LoginController loginController = new LoginController() ;
		this.OffreU.getScene().getWindow().hide();
		this.EspU.getScene().getWindow().hide();
		this.MessageU.getScene().getWindow().hide();
		loginController.ChargerInterLogin();
	}
	@FXML
	public void toMonEsp() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/MonEspace.fxml"));
		if(this.OffreU != null || this.EspU != null || this.DeconnexionU != null) {
			this.OffreU.getScene().getWindow().hide();
			this.EspU.getScene().getWindow().hide();
			this.DeconnexionU.getScene().getWindow().hide();
		}
		if(this.retour != null)this.retour.getScene().getWindow().hide();
		Scene scene = new Scene(root);
    	Stage Espace = new Stage(); 
    	Espace.setTitle("Mon Espace");
    	Espace.getIcons().add(new Image("file:../../Images/icon.png"));
    	Espace.setScene(scene);
    	Espace.show();
	}
	@FXML
	public void toProfile() throws IOException {
		//System.out.println(NomUrs.getText());
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/Profile.fxml"));
		this.Profile.getScene().getWindow().hide();
		this.MesOffre.getScene().getWindow().hide();
		this.MesDem.getScene().getWindow().hide();
		Scene scene = new Scene(root);
    	Stage Profile = new Stage(); 
    	Profile.setTitle("Profile");
    	Profile.getIcons().add(new Image("file:../../Images/icon.png"));
    	Profile.setScene(scene);
    	Profile.show();
	}
	@FXML
	public void toGestionnaireMessage() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/GestioneMessages.fxml"));
		this.OffreU.getScene().getWindow().hide();
		this.EspU.getScene().getWindow().hide();
		this.DeconnexionU.getScene().getWindow().hide();
		Scene scene = new Scene(root);
    	Stage Message = new Stage(); 
    	Message.setTitle("Gestionnaire des Messages");
		Message.getIcons().add(new Image("file:../../Images/icon.png"));
    	Message.setScene(scene);
    	Message.show();
		
	}
	 @FXML
	    public ObservableList<Message> dataMessage = FXCollections.observableArrayList();
	    @FXML
	    public void AfficherMessages() {
	    	for(int i=0;i<dataMessage.size();i++) dataMessage.remove(0);
	    	for ( int i = 0; i<tableMessages.getItems().size(); i++) tableMessages.getItems().clear();
	    	try {
	    		ResultSet resu = LoginController.user.afficher_les_message();
				while(resu.next()) {
					dataMessage.add(new Message(resu.getString("Email"),resu.getString("contenue")));
				}
			}catch(SQLException e) {
				Alert dialog = new Alert(AlertType.INFORMATION);
	    		dialog.setTitle("");
	    		dialog.setContentText("probléme de connexion à la base de données");
	    		dialog.showAndWait();
			}
	    	emailSrc.setCellValueFactory(new PropertyValueFactory<Message,String>("EmailSrc"));
	    	Contenue.setCellValueFactory(new PropertyValueFactory<Message,String>("message"));
	    	tableMessages.setItems(dataMessage);
	    }
	 @FXML
	 public void exitToIntrUser() throws IOException {
		 LoginController loginController = new LoginController();
		 if( this.resetMessage != null || this.DeleteMessage != null ||  this.envoyer_message != null) {
			 this.resetMessage.getScene().getWindow().hide();
			 this.DeleteMessage.getScene().getWindow().hide();
			 this.envoyer_message.getScene().getWindow().hide();
		 }
		 if( this.Profile!= null || this.MesOffre!= null || this.MesDem!= null) {
			 this.Profile.getScene().getWindow().hide();
			 this.MesOffre.getScene().getWindow().hide();
			 this.MesDem.getScene().getWindow().hide();
		 }
		 loginController.ChargerInterUser();
	  }
	 @FXML
	 public void toSupprimerMessage() {
		 /*
		    Parent root = FXMLLoader.load(getClass().getResource("/FXML/ModifierMesInf.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage toSuppMessage = new 
	    	ModifierInf.setTitle("Espace de l'utilisateur");
			ModifierInf.getIcons().add(new Image("file:../../Images/icon.png"));
	    	ModifierInf.setScene(scene);
	    	ModifierInf.show();
	    	*/
		 System.out.println("bonjour");
	 }
	 @FXML
	 public void toEnvoyerMessage() throws IOException {
		    Parent root = FXMLLoader.load(getClass().getResource("/FXML/envoyer_message.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage Message = new Stage(); 
	    	Message.setTitle("Envoyer un Message");
			Message.getIcons().add(new Image("file:../../Images/icon.png"));
	    	Message.setScene(scene);
	    	Message.show();
	  }
	 @FXML
	 public void EnvoyerMessage() {
		 if(LoginController.user.envoyer_message(EmailDest.getText(),MessageEnv.getText())) {
			 Alert dialog = new Alert(AlertType.INFORMATION);
     		 dialog.setTitle("Message envoyé .");
     		 dialog.setContentText("Les message il est envoyé avec succes");
     		 dialog.showAndWait();
		 }else {
			 Alert dialog = new Alert(AlertType.INFORMATION);
     		 dialog.setTitle("le Message n'est pas envoyer");
     		 dialog.setContentText("ce utilisateur n'exist pas !!");
     		 dialog.showAndWait();
		 }
	 }
}