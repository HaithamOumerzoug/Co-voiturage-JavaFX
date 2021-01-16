package Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import application.Administrateur;
import application.SendEmail;
import application.Utilisateur;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    @FXML private Button IdObliePassword;
    @FXML private TextField MotDePasse1;
    @FXML private TextField MotDePasse;
    @FXML private TextField ConfirmMotDePasse;
    @FXML private TextField ConfirmMotDePasse1;
    @FXML private Button ConexionAdm;
    @FXML private AnchorPane RegisterBackground;
    @FXML private TextField adresse;
    @FXML private TextField telephone;
    @FXML private TextField nom;
    @FXML private TextField email;   
    @FXML private Button exitButt;
    @FXML private ImageView eyeIcon;
    @FXML private ImageView eyeIcon1;
    @FXML private ImageView eyeIconInsUser;
    @FXML private ImageView DeyeIconInsUser;
    @FXML private TextField TextPassword1;
    @FXML private TextField EmailUserForget;
    @FXML private Button SendButton;
    @FXML private AnchorPane LoginBack;
    @FXML private boolean ShowPasse = false;
    @FXML private boolean ShowPasseInsUser = false;
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
    	Scene scene = new Scene(root,750,500);
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
    	boolean b;
    	if(!this.ShowPasse) b = LoginController.user.TestDeConUser(TextEmail.getText(),TextPassword.getText());
    	else b = LoginController.user.TestDeConUser(TextEmail.getText(),TextPassword1.getText());
    	if(b) {
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
	    boolean b;
	    if(!this.ShowPasse) b=LoginController.admin.TestDeConnexion(TextEmail.getText(), TextPassword.getText());
	    else  b=LoginController.admin.TestDeConnexion(TextEmail.getText(), TextPassword1.getText());
    	if(b) {
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
    public void toSendOldPassword() throws IOException {
    	this.IdObliePassword.getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/ForgetPassword.fxml"));
    	Scene scene = new Scene(root,750,500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Application Co_Voiturage");
		primaryStage.getIcons().add(new Image("file:../../Images/icon.png"));
		primaryStage.show();	
    }
    @FXML
    public void Enrgistrer() throws IOException {
    	String reg="^$";
    	String regEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
    	String regTel="^0[67][0-9]+$";
    	if(nom.getText().matches(reg) || email.getText().matches(reg) || (this.ShowPasseInsUser ==true ? MotDePasse1.getText().matches(reg):MotDePasse.getText().matches(reg)) || telephone.getText().matches(reg) || adresse.getText().matches(reg)) {
    		Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("Attention.");
    		dialog.setContentText("l'un des champs est vide !!");
    		dialog.showAndWait();
    	}else if(!email.getText().matches(regEmail)) {
	    	Alert dialog = new Alert(AlertType.ERROR);
	    	dialog.setTitle("ERREUR DANS L'EMAIL.");
	    	dialog.setHeaderText("Erreur de syntaxe.");
	    	dialog.setContentText("synatxe de l'email est invalide !!");
	    	dialog.showAndWait();
	    }else if(!telephone.getText().matches(regTel)) {
	    	Alert dialog = new Alert(AlertType.ERROR);
	    	dialog.setTitle("ERREUR DANS TELEPHONE.");
	    	dialog.setHeaderText("Erreur de syntaxe.");
	    	dialog.setContentText("synatxe de numéro de télephone est invalide !!");
	    	dialog.showAndWait();
	    }else {
	    	if((!MotDePasse.getText().equals(ConfirmMotDePasse.getText()) && !this.ShowPasseInsUser ) || (!MotDePasse1.getText().equals(ConfirmMotDePasse1.getText()) && this.ShowPasseInsUser)){
		    	Alert dialog = new Alert(AlertType.ERROR);
		    	dialog.setTitle("Erreur dans L'inscription.");
		    	dialog.setHeaderText("Erreur dans le mot de passe.");
		    	dialog.setContentText("Les deux mot de passe ns sont pas identique!!");
		    	dialog.showAndWait();
		    }else {
		    	    String PasswordUser = "";
		    	    if(!this.ShowPasseInsUser) PasswordUser = MotDePasse.getText();
		    	    else PasswordUser = MotDePasse1.getText();
		    		Utilisateur user = new Utilisateur(nom.getText(),email.getText(),PasswordUser,telephone.getText(),adresse.getText());
		    		if(user.CreeCompteUtilisateur()) {
		    			if(LoginController.user.TestDeConUser(email.getText(),PasswordUser)) {
		    				this.enrgistrer.getScene().getWindow().hide();
		    				this.ChargerInterUser();
		    			}
		    		}else {
		    			Alert dialog = new Alert(AlertType.ERROR);
		        		dialog.setTitle("Erreur dans L'inscription.");
		        		dialog.setContentText("Les information son invalide !!");
		        		dialog.showAndWait();
		    		}
		    	}
	    }
    }
    @FXML
    public void ShowPasswordInsUser() {
    	this.DeyeIconInsUser.setVisible(true);
		this.MotDePasse1.setVisible(true);
		this.ConfirmMotDePasse1.setVisible(true);
		this.eyeIconInsUser.setVisible(false);
		this.MotDePasse.setVisible(false);
		this.ConfirmMotDePasse.setVisible(false);
		this.MotDePasse1.setText(this.MotDePasse.getText());
		this.ConfirmMotDePasse1.setText(this.ConfirmMotDePasse.getText());
		this.ShowPasseInsUser = true;	
    }
    @FXML
    public void DShowPasswordInsUser() {
    	this.DeyeIconInsUser.setVisible(false);
		this.MotDePasse1.setVisible(false);
		this.ConfirmMotDePasse1.setVisible(false);
		this.eyeIconInsUser.setVisible(true);
		this.MotDePasse.setVisible(true);
		this.ConfirmMotDePasse.setVisible(true);
		this.MotDePasse.setText(this.MotDePasse1.getText());
		this.ConfirmMotDePasse.setText(this.ConfirmMotDePasse1.getText());
		this.ShowPasseInsUser = false;
    }
    @FXML
    public void ShowPassword() {
    	this.TextPassword.setVisible(false);
    	this.eyeIcon.setVisible(false);
    	this.TextPassword1.setVisible(true);
    	this.eyeIcon1.setVisible(true);
    	this.ShowPasse = true;
    	this.TextPassword1.setText(TextPassword.getText());
    }
    @FXML
    public void DShowPassword() {
    	this.TextPassword.setVisible(true);
    	this.eyeIcon.setVisible(true);
    	this.TextPassword1.setVisible(false);
    	this.eyeIcon1.setVisible(false);
    	this.ShowPasse = false;
    	TextPassword.setText(TextPassword1.getText());
    }
    @FXML 
    public void SendOldPassword() throws IOException {
    	String reg="^$";
    	String regEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
    	if(this.EmailUserForget.getText().matches(reg)) {
    		Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("Erreur.");
    		dialog.setContentText("Le champe de l'email est vide !!");
    		dialog.showAndWait();
    	}else if(!this.EmailUserForget.getText().matches(regEmail)) {
    		Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("Erreur.");
    		dialog.setContentText("Le Synatxe de l'email est faux !!");
    		dialog.showAndWait();
    	}else {
    		Utilisateur user = new Utilisateur();
    		long IdUser = user.getIdOfEmail(this.EmailUserForget.getText());
    		if(IdUser != -1 ) {
    		         if(SendEmail.Send(this.EmailUserForget.getText(),"Votre ancien mot de passe est : <b>" +user.GetMyPassword(IdUser)+"</b>","Initialisation du mot de passe",user.getNomUtilById(IdUser))) {
    		        	 Alert dialog = new Alert(AlertType.CONFIRMATION);
    		     		 dialog.setTitle("Succe.");
    		     		 dialog.setContentText("vous recevoir le mot de passe dans votre email!!");
    		     		 Optional<ButtonType> result = dialog.showAndWait();
    		     		 if(result.get() == ButtonType.OK) {
    		     			this.SendButton.getScene().getWindow().hide();
    		     			this.ChargerInterLogin();
    		     		}
    		         }else {
    		        	Alert dialog = new Alert(AlertType.ERROR);
    		     		dialog.setTitle("Erreur.");
    		     		dialog.setContentText("probléme de connexion !!");
    		     		dialog.showAndWait();
    		         }
    	}else {
    			Alert dialog = new Alert(AlertType.ERROR);
        		dialog.setTitle("Erreur.");
        		dialog.setContentText(" ce email n'exist pas !!");
        		dialog.showAndWait();
    		}
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(this.LoginBack != null ) {
			this.TextPassword1.setVisible(false);
	    	this.eyeIcon1.setVisible(false);
		}
		if(this.RegisterBackground != null) {
			this.DeyeIconInsUser.setVisible(false);
			this.MotDePasse1.setVisible(false);
			this.ConfirmMotDePasse1.setVisible(false);
		}
	} 
}
