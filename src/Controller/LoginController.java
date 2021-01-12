package Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Administrateur;
import application.Utilisateur;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class LoginController implements Initializable{
    @FXML private PasswordField TextPassword;
    @FXML private ImageView BackgroundLogin;
    @FXML private Button Register;
    @FXML private TextField TextEmail;
    @FXML private Button Conexion;
    @FXML private Button enrgistrer;
    @FXML private TextField MotDePasse;
    @FXML private TextField ConfirmMotDePasse;
    @FXML private Button ConexionAdm;
    @FXML private AnchorPane RegisterBackground;
    @FXML private TextField adresse;
    @FXML private TextField telephone;
    @FXML private TextField nom;
    @FXML private TextField email;   
    @FXML private Button exitButt;
    @FXML public static Utilisateur user = new Utilisateur();
    @FXML public static Administrateur admin = new Administrateur();
    @FXML
    public void ExitLogin() throws IOException {
    	this.enrgistrer.getScene().getWindow().hide();
    	this.ChargerInterLogin();
    }
    @FXML
    public void ChargerInterUser() throws IOException {
    	Stage EspaceUtil = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/EspaceUtilisateur.fxml"));
    	Scene scene = new Scene(root);
    	EspaceUtil.setTitle("Espace de l'utilisateur");
		EspaceUtil.getIcons().add(new Image("file:../../Images/icon.png"));
    	EspaceUtil.setScene(scene);
    	EspaceUtil.show();
    }
    @FXML
    public void ChargerInterAdm() throws IOException {
    	
    	Stage EspaceAdm = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/EspaceAdministrateur.fxml"));
		Scene scene = new Scene(root);
		EspaceAdm.setTitle("Espace de l'Administrateur");
		EspaceAdm.getIcons().add(new Image("file:../../Images/icon.png"));
		EspaceAdm.setScene(scene);
		EspaceAdm.show();
    }
    @FXML
    public void ChargerInterLogin() throws IOException {
    	Stage primaryStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
		Scene scene = new Scene(root,750,400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Application Co_Voiturage");
		primaryStage.getIcons().add(new Image("file:../../Images/icon.png"));
		primaryStage.show();
    }
    @FXML
    public void Inscrire() throws IOException {
    	Conexion.getScene().getWindow().hide();
    	ConexionAdm.getScene().getWindow().hide();
    	Stage Register = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/Inscrire.fxml"));
    	Scene scene = new Scene(root);
    	Register.setTitle("Inscription");
		Register.getIcons().add(new Image("file:../../Images/icon.png"));
    	Register.setScene(scene);
    	Register.show();
    }
    @FXML
    public void Connection_Espace() throws IOException {
    	if(this.user.TestDeConUser(TextEmail.getText(),TextPassword.getText())) {
            Register.getScene().getWindow().hide();
            ConexionAdm.getScene().getWindow().hide();
        	this.ChargerInterUser();
    	}else {
    		Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("Erreur dans L'inscription.");
    		dialog.setContentText("Les information son invalide !!");
    		dialog.showAndWait();
    	}
    }
   @FXML
    public void Connection_Espace_Admin() throws IOException{
    	if(this.admin.TestDeConnexion(TextEmail.getText(), TextPassword.getText())) {
    		Register.getScene().getWindow().hide();
    		Conexion.getScene().getWindow().hide();
    		this.ChargerInterAdm();
    	}else {
    		Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("Erreur.");
    		dialog.setContentText("Les information son invalide !!");
    		dialog.showAndWait();
    	}
    }
    @FXML
    public void Enrgistrer() {
    	String reg="^$";
    	if(nom.getText().matches(reg) || email.getText().matches(reg) || MotDePasse.getText().matches(reg) || telephone.getText().matches(reg) || adresse.getText().matches(reg)) {
    		Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("Attention.");
    		dialog.setContentText("l'un des champs est vide !!");
    		dialog.showAndWait();
    	}else if(!email.getText().substring(email.getText().length()-10, email.getText().length()).equals("@gmail.com")) {
	    	Alert dialog = new Alert(AlertType.ERROR);
	    	dialog.setTitle("EMAIL DANS L'EMAIL.");
	    	dialog.setHeaderText("Erreur de syntaxe.");
	    	dialog.setContentText("synatxe de l'email est invalide !!");
	    	dialog.showAndWait();
	    }else if(!MotDePasse.getText().equals(ConfirmMotDePasse.getText())) {
	    	Alert dialog = new Alert(AlertType.ERROR);
	    	dialog.setTitle("Erreur dans L'inscription.");
	    	dialog.setHeaderText("Erreur dans le mot de passe.");
	    	dialog.setContentText("Les deux mot de passe ns sont pas identique!!");
	    	dialog.showAndWait();
	    }else {
	    		Utilisateur user = new Utilisateur(nom.getText(),email.getText(),MotDePasse.getText(),telephone.getText(),adresse.getText());
	    		if(user.CreeCompteUtilisateur()) {
	    			Alert dialog = new Alert(AlertType.INFORMATION);
	        		dialog.setTitle("vous étes inscrire avec succes");
	        		dialog.setHeaderText("");
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	} 
}