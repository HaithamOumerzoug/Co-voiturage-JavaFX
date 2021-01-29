package Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import application.Message;
import application.Offer;
import application.Reservation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
	@FXML private TextField EmailUserMessage;
	@FXML private ImageView ajouter;
	@FXML private ImageView reservationBtt;
	@FXML private TableColumn<Message, String> emailSrc;
	@FXML private TableColumn<Message,String> Contenue;
	@FXML private Button Profile;
	@FXML private Button MesOffre;
	@FXML private Label BonUser = new Label();
	@FXML private Label NomUsr = new Label();
	@FXML private Label EmailU = new Label();
	@FXML private Label TelU = new Label();
	@FXML private Label AdrU = new Label();
	@FXML private Button ApplyButton;
	@FXML private Button retour;
	@FXML private Button modifier;
	@FXML private Button exitButton;
	@FXML private Button ModifierMesInf;
	@FXML private Button ajouteroffre;
	@FXML private Button exitButt;
	@FXML private Button exitButtChoix;
	@FXML private TextField nomUsr;
	@FXML private TextField emailUsr;
	@FXML private TextField MotDePasseUsr;
	@FXML private TextField telephoneUsr;
	@FXML private TextField adresseUsr;
	@FXML private AnchorPane ProfileBackground;  
	@FXML private AnchorPane RegisterBackground;
	@FXML private AnchorPane BackgroundMod;
	@FXML private TableView<Offer> tableOffer;
    @FXML private TableColumn<Offer,Long> IdOffer;
    @FXML private TableColumn<Offer,String> TitreOffer;
    @FXML private TableColumn<Offer,Float> PrixOffer;
    @FXML private TableColumn<Offer,String> Ville_depart;
    @FXML private TableColumn<Offer,String> Ville_arrive;
    @FXML private TableColumn<Offer,Integer> NbrPlaceOffer;
	@FXML private ImageView retourimg;
	@FXML private ImageView toDeleteOffer;
	@FXML private ChoiceBox<String> bagage;
	@FXML private ChoiceBox<String> Modbagage;
	@FXML private ComboBox<String> ChoiceOffer;
	@FXML private ComboBox<String> ChoiceOfferSupp;
	@FXML private TextField titre;
	@FXML private TextField prix;
	@FXML private ComboBox<String> villeDep;
	@FXML private ComboBox<String> villeDar;
	@FXML private TextField nbrPlac;
	@FXML private DatePicker dateDep;
	@FXML private TextField heurDep;
	@FXML private TextField Modtitre;
	@FXML private TextField Modprix;
	@FXML private ComboBox<String> ModvilleDep;
	@FXML private ComboBox<String> ModvilleDar;
	@FXML private TextField ModnbrPlac;
	@FXML private DatePicker ModdateDep;
	@FXML private TextField ModheurDep;
	@FXML private Button applymodifier;
	@FXML private Button EnrOffre;
	@FXML private Button exitButtModification;
	@FXML private Button AnnulerSuppButt;
	@FXML private Button SuppButt;
	@FXML private Button EnvoyerMessage;
	@FXML private boolean showpassUserModify = false;
	@FXML private ImageView Showeye;
	@FXML private ImageView HideEye;
	@FXML private TextField MotDePasseUser1;
	@FXML private TableView<Offer> tableOfferAll;
	@FXML private TableColumn<Offer,Long> IdOffre;
    @FXML private TableColumn<Offer,String> TitreOffre;
    @FXML private TableColumn<Offer,String> Date_depart;
    @FXML private TableColumn<Offer,String> Heure_depart;
    @FXML private TableColumn<Offer, Float> PrixOffre;
    @FXML private TableColumn<Offer, String> Ville_departAll;
    @FXML private TableColumn<Offer,String> Ville_arriveAll;
    @FXML private TableColumn<Offer,Integer> NbrPlaceOffre;
    @FXML private TableColumn<Offer,String> bagageAll;
    @FXML private TableColumn<Offer,Integer> nbrPlacesreserv;
    @FXML private TextField NbrPlaces;
    @FXML private TextField Message;
    @FXML private TextField idOffreFavoris;
	@FXML private TextField IdOffreValue;
	@FXML private ImageView Favoris;
	@FXML private ImageView Apply;
	@FXML private ImageView ExitBtt;
	@FXML private ImageView modifierOffer;
	@FXML private Label nbrOffres = new Label();
    @FXML private ImageView actualiserAll;
    @FXML private ImageView favorisOnly;
    @FXML private ImageView favorisDelete;
    @FXML private Label grandLabel;
    @FXML public static String titreOffre;
    @FXML private Button DemRec;
    @FXML private ImageView ExitDem;
    @FXML private TableView<Reservation> tableDemandeRec;
    @FXML private TableColumn<Reservation,String> nomUser;
    @FXML private TableColumn<Reservation,String> TitreOffreDem; 
    @FXML private TableColumn<Reservation,String> messageDem ;
    @FXML private TableColumn<Reservation,Integer> NbrPlaceDem ;
    public static long idOffreSelect;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(BonUser != null) BonUser.setText("Bienvenu "+LoginController.user.getMesInf().getNom());
		if(tableMessages != null) this.AfficherMessages();
		if(tableOffer != null ) this.afficherMesOffres();
		if(tableDemandeRec != null) {
			try {
				this.afficherDemandRec();
			} catch (IOException e1) {
				Alert dialog = new Alert(AlertType.ERROR);
        		dialog.setTitle("Erreur");
        		dialog.setContentText("Erreur dans l'application !!");
        		dialog.showAndWait();
			}
		}
		if(this.ProfileBackground != null) {
			this.NomUsr.setText(LoginController.user.getMesInf().getNom());
			this.EmailU.setText(LoginController.user.getMesInf().getEmail());
			this.TelU.setText(LoginController.user.getMesInf().getTel());
			this.AdrU.setText(LoginController.user.getMesInf().getAdresse());
		}
		if(this.RegisterBackground != null) {
			this.nomUsr.setText(LoginController.user.getMesInf().getNom());
			this.emailUsr.setText(LoginController.user.getMesInf().getEmail());
			this.MotDePasseUsr.setText(LoginController.user.getMesInf().getMot_de_passe());
			this.telephoneUsr.setText(LoginController.user.getMesInf().getTel());
			this.adresseUsr.setText(LoginController.user.getMesInf().getAdresse());
			this.MotDePasseUser1.setVisible(false);
			this.HideEye.setVisible(false);
		}
		if(this.Modbagage != null) {
			try {
				ModloadeVille();
				ModloadBagage();
			
			} catch (IOException e) {
				Alert dialog = new Alert(AlertType.ERROR);
        		dialog.setTitle("Erreur");
        		dialog.setContentText("Erreur dans la connexion !!");
        		dialog.showAndWait();
			}
	    }
		if(this.bagage != null) {
			try {
				loadeVille();
				loadBagage();
			
			} catch (IOException e) {
				Alert dialog = new Alert(AlertType.ERROR);
        		dialog.setTitle("Erreur");
        		dialog.setContentText("Erreur dans la connexion !!");
        		dialog.showAndWait();
			}
	    }
		if(this.tableOfferAll != null) this.afficherOffres();
		if(this.ChoiceOffer != null) {
			try {
				loadOffers("mod");
			
			} catch (IOException e) {
				Alert dialog = new Alert(AlertType.ERROR);
        		dialog.setTitle("Erreur");
        		dialog.setContentText("Erreur dans l'application !!");
        		dialog.showAndWait();
			}
		}
		if(this.ChoiceOfferSupp != null) {
			try {
				loadOffers("supp");
			
			} catch (IOException e) {
				Alert dialog = new Alert(AlertType.ERROR);
        		dialog.setTitle("Erreur");
        		dialog.setContentText("Erreur dans l'application !!");
        		dialog.showAndWait();
			}
		}
		if(this.dateDep != null ) this.dateDep.setValue(LocalDate.now());
		if(this.tableOfferAll != null) this.afficherOffres();
		if(this.BackgroundMod != null) {
			Offer offre = LoginController.user.getInfoOffer(UserController.titreOffre);
			this.Modprix.setText(""+offre.getPrix());
			this.Modtitre.setText(offre.getTitre());
			this.ModdateDep.setValue(LocalDate.now());
			this.ModheurDep.setText(offre.getHeure_depart());
			this.ModvilleDep.setValue(offre.getVille_depart());
			this.ModvilleDar.setValue(offre.getVille_arrive());
			this.ModnbrPlac.setText(""+offre.getNbr_places());
			this.Modbagage.setValue(offre.getBagage());	
		}
	}
	@FXML
	public void ExitToUser() throws IOException {
		LoginController loginController = new LoginController();
		this.ExitBtt.getScene().getWindow().hide();
		loginController.ChargerInterUser();
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
		if(this.retourimg != null)this.retourimg.getScene().getWindow().hide();
		if(this.DemRec != null)this.DemRec.getScene().getWindow().hide();
		if(this.ExitDem != null)this.ExitDem.getScene().getWindow().hide();
		Scene scene = new Scene(root);
    	Stage Espace = new Stage(); 
    	Espace.setTitle("Mon Espace");
    	Espace.getIcons().add(new Image("file:../../Images/icon.png"));
    	Espace.setScene(scene);
    	Espace.show();
	}
	@FXML
	public void toProfile() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/Profile.fxml"));
		if(this.Profile != null ||this.Profile!= null || this.DemRec != null) {
			this.Profile.getScene().getWindow().hide();
			this.MesOffre.getScene().getWindow().hide();
			this.DemRec.getScene().getWindow().hide();
		}
		if(this.exitButton != null ||this.ModifierMesInf != null) {
			this.exitButton.getScene().getWindow().hide();
			this.ModifierMesInf.getScene().getWindow().hide();
		}
		Scene scene = new Scene(root);
    	Stage Profile = new Stage(); 
    	Profile.setTitle("Profile");
    	Profile.getIcons().add(new Image("file:../../Images/icon.png"));
    	Profile.setScene(scene);
    	Profile.show();
	}
	 @FXML
	    public void toModifierMesInfUsr() throws IOException {
	    	modifier.getScene().getWindow().hide();
	    	retour.getScene().getWindow().hide();
	    	Stage ModifierInf = new Stage();
	    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/ModifierMesInfUsr.fxml"));
	    	Scene scene = new Scene(root);
	    	ModifierInf.setTitle("Modification des informations d'utilisateur");
			ModifierInf.getIcons().add(new Image("file:../../Images/icon.png"));
	    	ModifierInf.setScene(scene);
	    	ModifierInf.show();
	    }
	    @FXML
	    public void ModifierMesInformationUsr() {
	    	String reg="^$";
	    	String regEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
	    	String regTel="^0[5-7][0-9]+$";
	    	if(nomUsr.getText().matches(reg) || emailUsr.getText().matches(reg) || MotDePasseUsr.getText().matches(reg) || telephoneUsr.getText().matches(reg) || adresseUsr.getText().matches(reg)) {
	    		Alert dialog = new Alert(AlertType.INFORMATION);
	    		dialog.setTitle("Attention.");
	    		dialog.setContentText("l'un des champs est vide !!");
	    		dialog.showAndWait();
	    	}else if(!emailUsr.getText().matches(regEmail)) {
		    	Alert dialog = new Alert(AlertType.INFORMATION);
		    	dialog.setTitle("EMAIL DANS L'EMAIL.");
		    	dialog.setHeaderText("Erreur de syntaxe.");
		    	dialog.setContentText("Synatxe de l'email est invalide !!");
		    	dialog.showAndWait();
		    }else if(!telephoneUsr.getText().matches(regTel)) {
		    	Alert dialog = new Alert(AlertType.ERROR);
		    	dialog.setTitle("ERREUR DANS TELEPHONE.");
		    	dialog.setHeaderText("Erreur de syntaxe.");
		    	dialog.setContentText("synatxe de numéro de télephone est invalide !!");
		    	dialog.showAndWait();
		    }else{
		    	   String motdepasse = "";
		    	   if(!this.showpassUserModify) motdepasse = MotDePasseUsr.getText();
		    	   else  motdepasse = this.MotDePasseUser1.getText();
		    		if(LoginController.user.ChangerUtilisateur(nomUsr.getText(),emailUsr.getText(),motdepasse,telephoneUsr.getText(),adresseUsr.getText())) {
		    			Alert dialog = new Alert(AlertType.INFORMATION);
		        		dialog.setTitle("Vous étes modifier vous informations avec succes");
		        		dialog.setContentText("Les information sont bien enrgistrer :)");
		        		dialog.showAndWait();
		    		}else {
		    			Alert dialog = new Alert(AlertType.ERROR);
		        		dialog.setTitle("Erreur dans L'inscription.");
		        		dialog.setContentText("Les informations son invalide !!");
		        		dialog.showAndWait();
		    		}
		    	}
	    }
	    @FXML
	     public void ShowPasswordUserModify() {
			this.MotDePasseUser1.setVisible(true);
			this.HideEye.setVisible(true);
			this.MotDePasseUsr.setVisible(false);
			this.Showeye.setVisible(false);
			this.MotDePasseUser1.setText(this.MotDePasseUsr.getText());
			this.showpassUserModify = true;
		}
		@FXML
		public void HidePasswordModifyUser() {
			this.MotDePasseUser1.setVisible(false);
			this.HideEye.setVisible(false);
			this.MotDePasseUsr.setVisible(true);
			this.Showeye.setVisible(true);
			this.MotDePasseUsr.setText(this.MotDePasseUser1.getText());
			this.showpassUserModify = false;
		}
	    @FXML
		public void toMesOffres() throws IOException {
	    	if(this.MesOffre != null)this.MesOffre.getScene().getWindow().hide();
	    	if(this.exitButt != null || this.ajouteroffre != null)this.exitButt.getScene().getWindow().hide();
	    	if(this.exitButtChoix != null) this.exitButtChoix.getScene().getWindow().hide();
	    	if(this.SuppButt != null) this.SuppButt.getScene().getWindow().hide();
		    Stage ModifierInf = new Stage();
		    Parent root = FXMLLoader.load(getClass().getResource("/FXML/MesOffres.fxml"));
		    Scene scene = new Scene(root);
		    ModifierInf.setTitle("Mes offres");
			ModifierInf.getIcons().add(new Image("file:../../Images/icon.png"));
		    ModifierInf.setScene(scene);
		    ModifierInf.show();
	    }
	    public ObservableList<Offer> dataOffre = FXCollections.observableArrayList();
	    @FXML
	    public void afficherMesOffres() {
	    	for(int i=0;i<dataOffre.size();i++) dataOffre.remove(0);
	    	for ( int i = 0; i<tableOffer.getItems().size(); i++) tableOffer.getItems().clear();
	    	try {
	    		ResultSet resu = LoginController.user.AfficherOffers("my");
				while(resu.next()) {
				   dataOffre.add( new Offer(resu.getLong("Id_Offer"),resu.getString("Titre"),resu.getFloat("prix"),resu.getString("Ville_depart"),resu.getString("Ville_arrive"),resu.getInt("Nbr_Places"),resu.getLong("Id_Utilisateur")));
				}
			}catch(SQLException e) {
				Alert dialog = new Alert(AlertType.INFORMATION);
	    		dialog.setTitle("");
	    		dialog.setContentText("probléme de connexion à la base de données");
	    		dialog.showAndWait();
			}
	    	this.IdOffer.setCellValueFactory(new PropertyValueFactory<Offer,Long>("Id_offer"));
	    	this.TitreOffer.setCellValueFactory(new PropertyValueFactory<Offer,String>("titre"));
	    	this.Ville_depart.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_depart"));
	    	this.Ville_arrive.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_arrive"));
	    	this.PrixOffer.setCellValueFactory(new PropertyValueFactory<Offer,Float>("prix"));
	    	this.NbrPlaceOffer.setCellValueFactory(new PropertyValueFactory<Offer,Integer>("Nbr_places"));
	    	
	    	tableOffer.setItems(dataOffre);
	    }
	    @FXML
		public void toOffresAll() throws IOException {
			if(this.OffreU != null) this.OffreU.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/Offres.fxml"));
			Scene scene = new Scene(root);
	    	Stage Message = new Stage(); 
	    	Message.setTitle("Gestionnaire des Offres");
			Message.getIcons().add(new Image("file:../../Images/icon.png"));
	    	Message.setScene(scene);
	    	Message.show();
		}
	    @FXML
	    public void toAddOffer() throws IOException {
	    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/AjouterOffer.fxml"));
			this.ajouter.getScene().getWindow().hide();
			Scene scene = new Scene(root);
	    	Stage Message = new Stage(); 
	    	Message.setTitle("Création d'offre");
			Message.getIcons().add(new Image("file:../../Images/icon.png"));
	    	Message.setScene(scene);
	    	Message.show();
	    }
	public ObservableList<String> list = FXCollections.observableArrayList();
	@FXML
	private void loadBagage() throws IOException {
		list.removeAll(list);
		String oui="OUI";
		String non="NON";
		list.addAll(oui,non);
		this.bagage.getItems().addAll(list);
	}
	@FXML 
	private void ModloadBagage() throws IOException{
		list.removeAll(list);
		String oui="OUI";
		String non="NON";
		list.addAll(oui,non);
		this.Modbagage.getItems().addAll(list);
	}
	@FXML
	private void AjouterOffre() throws IOException {
		String timereg = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		String reg="^$";
    	if(titre.getText().matches(reg) || dateDep ==null || heurDep.getText().matches(reg) || villeDep.getValue() == null || villeDar.getValue() == null|| bagage == null) {
    		Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("Attention.");
    		dialog.setContentText("l'un des champs est vide !!");
    		dialog.showAndWait();
    	}
    	else{
    		if(!this.heurDep.getText().matches(timereg)) {
				Alert dialog = new Alert(AlertType.ERROR);
	    		dialog.setTitle("Erreur dans La création.");
	    		dialog.setContentText("L'heure est invalide !!");
	    		dialog.showAndWait();
    		}
			else {
				float prix = Float.parseFloat(this.prix.getText());
				int nbrplac = Integer.parseInt(this.nbrPlac.getText());
				Offer offer = new Offer(this.titre.getText(),prix,this.dateDep.getValue().toString(),this.heurDep.getText(),this.villeDep.getValue(),this.villeDar.getValue(),nbrplac,this.bagage.getValue());
				if(LoginController.user.AjouterOffer(this.titre.getText(),prix,this.dateDep.getValue().toString(),this.heurDep.getText(),this.villeDep.getValue(),this.villeDar.getValue(),nbrplac,this.bagage.getValue())) {
					Alert dialog = new Alert(AlertType.INFORMATION);
		    		dialog.setTitle("Bien");
		    		dialog.setHeaderText("");
		    		dialog.setContentText("Votre offre à été bien enrgistré :)");
		    		dialog.showAndWait();
		    		toMesOffres();
				}else {
					Alert dialog = new Alert(AlertType.ERROR);
		    		dialog.setTitle("Erreur dans La création.");
		    		dialog.setContentText("Les informations son invalide !!");
		    		dialog.showAndWait();
				}
			}
    	}
	}
	@FXML
	public void toChoisirOffer() throws IOException {
		if(this.modifierOffer != null) this.modifierOffer.getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/ChoixOffre.fxml"));
		Scene scene = new Scene(root);
    	Stage Message = new Stage(); 
    	Message.setTitle("Choix d'offre");
		Message.getIcons().add(new Image("file:../../Images/icon.png"));
    	Message.setScene(scene);
    	Message.show();
	}
	public ObservableList<String> listChoix = FXCollections.observableArrayList();
	@FXML
	private void loadOffers(String c) throws IOException {
		listChoix.removeAll(listChoix);
   		ResultSet resu = LoginController.user.AfficherOffers("my");
		if(c=="mod") {
			try {
				while(resu.next()) {
				   listChoix.add(resu.getString("Titre"));
				}
				if(listChoix.isEmpty()) {
					String a = "Aucun offre disponible";
					this.ChoiceOffer.getItems().add(a);
				}
				else this.ChoiceOffer.getItems().addAll(listChoix);
			}catch(SQLException e) {
				Alert dialog = new Alert(AlertType.INFORMATION);
	    		dialog.setTitle("");
	    		dialog.setContentText("Probléme de connexion à la base de données");
	    		dialog.showAndWait();
			}
		}else if(c=="supp") {
			try {
				while(resu.next()) {
				   listChoix.add(resu.getString("Titre"));
				}
				if(listChoix.isEmpty()) {
					String a = "Aucun offre disponible";
					this.ChoiceOfferSupp.getItems().add(a);
				}
				else this.ChoiceOfferSupp.getItems().addAll(listChoix);
			}catch(SQLException e) {
				Alert dialog = new Alert(AlertType.INFORMATION);
		   		dialog.setTitle("");
		   		dialog.setContentText("Probléme de connexion à la base de données");
		   		dialog.showAndWait();
			}
		}
	}	
	@FXML
	public void toModifierOffer() throws IOException, SQLException {
		if(this.ChoiceOffer.getValue() == null) {
			Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("Attention !!");
    		dialog.setContentText("Champs est vide! Vous devez choisir l'une des offres disponibles");
    		dialog.showAndWait();
		}else {
			if(this.applymodifier != null)  this.applymodifier.getScene().getWindow().hide();
			UserController.titreOffre = this.ChoiceOffer.getValue();
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/ModifierMonOffer.fxml"));
			this.ChoiceOffer.getScene().getWindow().hide();
			Scene scene = new Scene(root);
			Stage Message = new Stage(); 
			Message.setTitle("Modification de mon offre");
			Message.getIcons().add(new Image("file:../../Images/icon.png"));
			Message.setScene(scene);
			Message.show();
		} 	
	}
	@FXML
	private void ModdifierOffer() throws IOException{
	    String timereg = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		String reg="^$";
    	if(Modtitre.getText().matches(reg)|| Modprix.getText().matches(reg) || ModdateDep ==null || ModnbrPlac.getText().matches(reg) || ModheurDep.getText().matches(reg) || ModvilleDep.getValue() == null || ModvilleDar.getValue() == null|| Modbagage == null) {
    		Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("Attention.");
    		dialog.setContentText("l'un des champs est vide !!");
    		dialog.showAndWait();
    	}
    	else{
    		if(!this.ModheurDep.getText().matches(timereg)) {
				Alert dialog = new Alert(AlertType.ERROR);
	    		dialog.setTitle("Erreur dans La création.");
	    		dialog.setContentText("L'heure est invalide !!");
	    		dialog.showAndWait();
    		}else {
				float prix = Float.parseFloat(this.Modprix.getText());
				int nbrplac = Integer.parseInt(this.ModnbrPlac.getText());
				long id_offer = LoginController.user.getInfoOffer(UserController.titreOffre).getId_offer();
				if(LoginController.user.ModdifierOffer(id_offer,this.Modtitre.getText(),prix,this.ModdateDep.getValue().toString(),this.ModheurDep.getText(),this.ModvilleDep.getValue(),this.ModvilleDar.getValue(),nbrplac,this.Modbagage.getValue())) {
					Alert dialog = new Alert(AlertType.INFORMATION);
		    		dialog.setTitle("Bien");
		    		dialog.setHeaderText("");
		    		dialog.setContentText("Votre offre à été bien modifié :)");
		    		dialog.showAndWait();
		    		this.EnrOffre.getScene().getWindow().hide();
		    		this.toMesOffres();
				}else {
					Alert dialog = new Alert(AlertType.ERROR);
		    		dialog.setTitle("Erreur dans La modification.");
		    		dialog.setContentText("Les informations son invalide !!");
		    		dialog.showAndWait();
				}
			}
		
    	}
	  
  }
	@FXML 
	private void ExitModOffre() throws IOException {
	   Stage stage = (Stage)exitButtModification.getScene().getWindow();
	   stage.close();
	   toMesOffres();
   }
	@FXML
	public void toSuppOffer() throws IOException{		
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/SupprimerMonOffer.fxml"));
		Scene scene = new Scene(root);
    	Stage Message = new Stage(); 
    	Message.setTitle("Suppression de mon offre");
		Message.getIcons().add(new Image("file:../../Images/icon.png"));
	   	Message.setScene(scene);
	    Message.show();
    	
	}
	@FXML
	public void SuppOffer() throws SQLException {
		if(this.ChoiceOfferSupp.getValue().isEmpty()) {
			Alert dialog = new Alert(AlertType.ERROR);
    		dialog.setTitle("Attention !!");
    		dialog.setContentText("Champs est vide! Vous devez choisir l'une des offres disponibles");
    		dialog.showAndWait();
		}else {
			String titre = this.ChoiceOfferSupp.getValue();
			Offer  offer = LoginController.user.getInfoOffer(titre);
			long id_offer = offer.getId_offer();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation de suppression ");
			alert.setContentText("Voulez-vous vraiment supprimer cette offre");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					if(LoginController.user.SupprimerOffer(id_offer)) {
						Alert dialog = new Alert(AlertType.INFORMATION);
						dialog.setTitle("Bien");
						dialog.setHeaderText("");
						dialog.setContentText("Votre offre à été supprimer avec succes :)");
						dialog.showAndWait();
						this.ChoiceOfferSupp.getItems().clear();
						try {
							loadOffers("supp");
							
						} catch (IOException e) {
							Alert dialog2 = new Alert(AlertType.ERROR);
							dialog2.setTitle("Erreur");
							dialog2.setContentText("Erreur dans l'application !!");
							dialog2.showAndWait();
						}
					}else {
						Alert dialog = new Alert(AlertType.ERROR);
			    		dialog.setTitle("Erreur !!");
			    		dialog.setContentText("La supprition mal fait");
			    		dialog.showAndWait();
					}} }}
	@FXML
	 public void toDemandeRec() throws IOException {
		 Stage gestionnaire = new Stage();
		 Parent root = FXMLLoader.load(getClass().getResource("/FXML/DemandeRecoit.fxml"));
		 this.DemRec.getScene().getWindow().hide();
		 Scene scene = new Scene(root);
		 gestionnaire.setTitle("Les demandes reçoit");
		 gestionnaire.getIcons().add(new Image("file:../../Images/icon.png"));
		 gestionnaire.setScene(scene);
		 gestionnaire.show();
	 }
	@FXML  
	 public void afficherDemandRec() throws IOException {
		ObservableList<Reservation> DemsRec = FXCollections.observableArrayList();
		 for(int i=0;i<DemsRec.size();i++) DemsRec.remove(0);
		    	
		    	for ( int i = 0; i<tableDemandeRec.getItems().size(); i++) tableDemandeRec.getItems().clear();
		    	try {
		    		ResultSet resu = LoginController.user.getDemandesRecoit();
					while(resu.next()) {
						 DemsRec.add(new Reservation(resu.getString("Nom"),resu.getString("NomOffre"),resu.getString("message"),resu.getInt("Nbr_places")));	 
					}
				}catch(SQLException e) {
					Alert dialog = new Alert(AlertType.INFORMATION);
		    		dialog.setTitle("");
		    		dialog.setContentText("probléme de connexion à la base de données");
		    		dialog.showAndWait();
				}
		    	this.nomUser.setCellValueFactory(new PropertyValueFactory<Reservation,String>("nom_utilisateurs"));
		    	this.TitreOffreDem.setCellValueFactory(new PropertyValueFactory<Reservation,String>("titre"));
		    	this.NbrPlaceDem.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("nbr_places"));
		    	this.messageDem.setCellValueFactory(new PropertyValueFactory<Reservation,String>("message"));
		    	tableDemandeRec.setItems(DemsRec);
	 }
   @FXML  
   public void SuppDemandRec() throws IOException {
		     Reservation ResDemRec = tableDemandeRec.getSelectionModel().getSelectedItem();
			 if(ResDemRec != null) {
				 Alert alert = new Alert(AlertType.CONFIRMATION);
				 alert.setTitle("Confirmation de suppression ");
				 alert.setContentText("Voulez-vous vraiment supprimer cette demande de réservation?");
				 Optional<ButtonType> result = alert.showAndWait();
				 if (result.get() == ButtonType.OK){
					 if(LoginController.user.SuppDemRec(ResDemRec.getId_utilisateurs(),ResDemRec.getId_offres())){
						  this.afficherDemandRec();
					 }else {
						 Alert dialog = new Alert(AlertType.INFORMATION);
			     		 dialog.setTitle("Erreur");
			     		 dialog.setContentText("La suppression mal fait");
			     		 dialog.showAndWait();
					 }
				 }
			 }else {
				 Alert dialog = new Alert(AlertType.INFORMATION);
	     		 dialog.setTitle("Erreur");
	     		 dialog.setContentText("Aucune demande séléctionnée");
	     		 dialog.showAndWait();
			 }  
	  }
	@FXML
	public void toGestionnaireMessage() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/GestioneMessages.fxml"));
		if(this.MessageU != null) this.MessageU.getScene().getWindow().hide();
		if(this.EnvoyerMessage != null) this.EnvoyerMessage.getScene().getWindow().hide();
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
		 if( this.Profile!= null || this.MesOffre!= null || this.DemRec!= null) {
			 this.Profile.getScene().getWindow().hide();
			 this.MesOffre.getScene().getWindow().hide();
			 this.DemRec.getScene().getWindow().hide();
		 }
		 loginController.ChargerInterUser();
	  }
	 @FXML
	 public void SupprimerMessages() {
		 Message message = this.tableMessages.getSelectionModel().getSelectedItem();
		 if(message != null) {
			    Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation de suppression ");
				alert.setContentText("Voulez-vous vraiment supprimer les messages resu par "+message.getEmailSrc());
				Optional<ButtonType> result = alert.showAndWait();
			 if(result.get() == ButtonType.OK) {
				 if(LoginController.user.SupprimerMessages(message.getEmailSrc())) {
					 this.AfficherMessages();
				 }
			 }
		 }else {
			 Alert dialog = new Alert(AlertType.INFORMATION);
     		 dialog.setTitle("Erreur");
     		 dialog.setContentText("Aucune messages séléctionnée");
     		 dialog.showAndWait();
			 }
	 }
	 @FXML
	 public void toEnvoyerMessage() throws IOException {
		    this.envoyer_message.getScene().getWindow().hide();
		    Parent root = FXMLLoader.load(getClass().getResource("/FXML/envoyer_message.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage Message = new Stage(); 
	    	Message.setTitle("Envoyer un Message");
			Message.getIcons().add(new Image("file:../../Images/icon.png"));
	    	Message.setScene(scene);
	    	Message.show();
	  }
	 @FXML
	 public void EnvoyerMessage() throws IOException {
		 if(LoginController.user.envoyer_message(EmailDest.getText(),MessageEnv.getText())) {
			 this.toGestionnaireMessage();
		 }else {
			 Alert dialog = new Alert(AlertType.INFORMATION);
     		 dialog.setTitle("le Message n'est pas envoyer");
     		 dialog.setContentText("ce utilisateur n'exist pas !!");
     		 dialog.showAndWait();
		 }
	 }
	 public ObservableList<Offer> dataOffreAll = FXCollections.observableArrayList();
	 @FXML
	    public void afficherOffres() {
	    	for(int i=0;i<dataOffreAll.size();i++) dataOffreAll.remove(0);
	    	for ( int i = 0;i<tableOfferAll.getItems().size();i++) tableOfferAll.getItems().clear();
	    	try {
	    		ResultSet resu = LoginController.user.AfficherOffers("all");
	    		
				while(resu.next()) {
				   dataOffreAll.add( new Offer(resu.getLong("Id_Offer"),resu.getString("Titre"),resu.getFloat("prix"),resu.getString("Date_depart"),resu.getString("Heure_depart"),resu.getString("Ville_depart"),resu.getString("Ville_arrive"),resu.getInt("Nbr_Places"),resu.getString("Bagage")));
				}

			}catch(SQLException e) {
				Alert dialog = new Alert(AlertType.INFORMATION);
	    		dialog.setTitle("Erreur");
	    		dialog.setContentText("Problème de connexion à la base de données");
	    		dialog.showAndWait();
			}
	    	this.IdOffre.setCellValueFactory(new PropertyValueFactory<Offer,Long>("Id_offer"));
	    	this.TitreOffre.setCellValueFactory(new PropertyValueFactory<Offer,String>("titre"));
	    	this.Ville_departAll.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_depart"));
	    	this.Ville_arriveAll.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_arrive"));
	    	this.Date_depart.setCellValueFactory(new PropertyValueFactory<Offer,String>("Date_departe"));
	    	this.Heure_depart.setCellValueFactory(new PropertyValueFactory<Offer,String>("Heure_depart"));
	    	this.PrixOffre.setCellValueFactory(new PropertyValueFactory<Offer,Float>("prix"));
	    	this.NbrPlaceOffre.setCellValueFactory(new PropertyValueFactory<Offer,Integer>("Nbr_places"));
	    	this.bagageAll.setCellValueFactory(new PropertyValueFactory<Offer,String>("Bagage"));
	    	this.nbrPlacesreserv.setVisible(false);
	    	Tooltip.install(this.Favoris, new Tooltip("Ajouter au favoris"));
	    	Tooltip.install(this.actualiserAll, new Tooltip("Actualiser"));
	    	Tooltip.install(this.Apply, new Tooltip("S'enregister dans l'offre séléctionnée"));
	    	Tooltip.install(this.ExitBtt, new Tooltip("Sortir"));
	    	Tooltip.install(this.favorisOnly, new Tooltip("Afficher les favoris"));
	    	Tooltip.install(this.favorisDelete, new Tooltip("Supprimer l'offre séléctionnée des favoris"));
	    	Tooltip.install(this.reservationBtt, new Tooltip("Afficher mes résérvations"));
	    	tableOfferAll.setItems(dataOffreAll);
	    	this.grandLabel.setText("Offres disponibles");
	    	this.nbrOffres.setText(LoginController.user.Nombre_offer_disp_pour(LoginController.user.IdUser)+" offres en total");
	    }
	 @FXML
	    public void showFavorisOnly() {
	    	for(int i=0;i<dataOffreAll.size();i++) dataOffreAll.remove(0);
	    	for ( int i = 0;i<tableOfferAll.getItems().size();i++) tableOfferAll.getItems().clear();
	    	try {
	    		ResultSet resu = LoginController.user.Afficher_Mes_Favorites2();
				while(resu.next()) {
				   dataOffreAll.add( new Offer(resu.getLong("Id_Offer"),resu.getString("Titre"),resu.getFloat("prix"),resu.getString("Date_depart"),resu.getString("Heure_depart"),resu.getString("Ville_depart"),resu.getString("Ville_arrive"),resu.getInt("Nbr_Places"),resu.getString("Bagage")));
				}

			}catch(SQLException e) {
				Alert dialog = new Alert(AlertType.INFORMATION);
	    		dialog.setTitle("Erreur");
	    		dialog.setContentText("Problème de connexion à la base de données");
	    		dialog.showAndWait();
			}
	    	this.IdOffre.setCellValueFactory(new PropertyValueFactory<Offer,Long>("Id_offer"));
	    	this.TitreOffre.setCellValueFactory(new PropertyValueFactory<Offer,String>("titre"));
	    	this.Ville_departAll.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_depart"));
	    	this.Ville_arriveAll.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_arrive"));
	    	this.Date_depart.setCellValueFactory(new PropertyValueFactory<Offer,String>("Date_departe"));
	    	this.Heure_depart.setCellValueFactory(new PropertyValueFactory<Offer,String>("Heure_depart"));
	    	this.PrixOffre.setCellValueFactory(new PropertyValueFactory<Offer,Float>("prix"));
	    	this.NbrPlaceOffre.setCellValueFactory(new PropertyValueFactory<Offer,Integer>("Nbr_places"));
	    	this.bagageAll.setCellValueFactory(new PropertyValueFactory<Offer,String>("Bagage"));
	    	tableOfferAll.setItems(dataOffreAll);
	    	this.nbrPlacesreserv.setVisible(false);
	    	this.grandLabel.setText("Offres Favoris");
	    	this.nbrOffres.setText(LoginController.user.Nombre_offer_disp_pour(LoginController.user.IdUser)+" offres en total");
	    }
	 @FXML
	 public void afficherReserv() {
	    	for(int i=0;i<dataOffreAll.size();i++) dataOffreAll.remove(0);
	    	for ( int i = 0;i<tableOfferAll.getItems().size();i++) tableOfferAll.getItems().clear();
	    	try {
	    		ResultSet resu = LoginController.user.AfficherReservation();
	    		
				while(resu.next()) {
				   dataOffreAll.add( new Offer(resu.getLong("idOfr"),resu.getString("Titre"),resu.getFloat("prix"),resu.getString("Date_depart"),resu.getString("Heure_depart"),resu.getString("Ville_depart"),resu.getString("Ville_arrive"),resu.getInt("nbrPlacesrest"),resu.getString("Bagage"),resu.getInt("nbrPlacesreserv")));
				}

			}catch(SQLException e) {
				Alert dialog = new Alert(AlertType.INFORMATION);
	    		dialog.setTitle("Erreur");
	    		dialog.setContentText("Problème de connexion à la base de données");
	    		dialog.showAndWait();
			}
	    	this.IdOffre.setCellValueFactory(new PropertyValueFactory<Offer,Long>("Id_offer"));
	    	this.TitreOffre.setCellValueFactory(new PropertyValueFactory<Offer,String>("titre"));
	    	this.Ville_departAll.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_depart"));
	    	this.Ville_arriveAll.setCellValueFactory(new PropertyValueFactory<Offer,String>("Ville_arrive"));
	    	this.Date_depart.setCellValueFactory(new PropertyValueFactory<Offer,String>("Date_departe"));
	    	this.Heure_depart.setCellValueFactory(new PropertyValueFactory<Offer,String>("Heure_depart"));
	    	this.PrixOffre.setCellValueFactory(new PropertyValueFactory<Offer,Float>("prix"));
	    	this.NbrPlaceOffre.setCellValueFactory(new PropertyValueFactory<Offer,Integer>("Nbr_places"));
	    	this.bagageAll.setCellValueFactory(new PropertyValueFactory<Offer,String>("Bagage"));
	    	this.nbrPlacesreserv.setCellValueFactory(new PropertyValueFactory<Offer,Integer>("nbr_places_reserv"));
	    	this.nbrPlacesreserv.setVisible(true);
	    	tableOfferAll.setItems(dataOffreAll);
	    	this.grandLabel.setText("Mes résérvations");
	    	this.nbrOffres.setText(LoginController.user.Nombre_offer_disp_pour(LoginController.user.IdUser)+" offres en total");
	    }
	 @FXML
	 public void applyToOffer() throws IOException {
		 Offer offreselect = tableOfferAll.getSelectionModel().getSelectedItem();
		 if(offreselect != null) {
			    if(this.Apply != null) this.Apply.getScene().getWindow().hide();
		 		UserController.idOffreSelect = offreselect.getId_offer();
		        Stage gestionnaire = new Stage();
		    	Parent root = FXMLLoader.load(getClass().getResource("/FXML/Demande.fxml"));
	    	    Scene scene = new Scene(root);
	    	    gestionnaire.setTitle("S'enregistrer dans une offre");
			    gestionnaire.getIcons().add(new Image("file:../../Images/icon.png"));
	    	    gestionnaire.setScene(scene);
	    	    gestionnaire.show();   
		 }else {
			 Alert dialog = new Alert(AlertType.INFORMATION);
     		 dialog.setTitle("Erreur");
     		 dialog.setContentText("Aucune offre séléctionnée");
     		 dialog.showAndWait();
		 }
	 }
	 @FXML
	 public void addToFavoris() throws IOException {
		 Offer offreselect = tableOfferAll.getSelectionModel().getSelectedItem();
		 if(offreselect != null) {
			 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			 alert.setTitle("Ajouter au favoris");
			 alert.setContentText("Voulez-vous ajouter l'offre séléctionné aux favoris?");
			 ButtonType okButton = new ButtonType("Oui", ButtonData.YES);
			 ButtonType noButton = new ButtonType("Non", ButtonData.NO);
			 alert.getButtonTypes().setAll(okButton, noButton);
			 alert.showAndWait().ifPresent(type -> {
			         if (type == okButton) {
			        	 if(LoginController.user.AjouterAuFavorites(offreselect.getId_offer())){
							 Alert dialog = new Alert(AlertType.INFORMATION);
				     		 dialog.setTitle("Succés !");
				     		 dialog.setContentText("Offre ajoutée au Favoris avec succés");
				     		 dialog.showAndWait();
						 }else {
							 Alert dialog = new Alert(AlertType.ERROR);
				     		 dialog.setTitle("Erreur");
				     		 dialog.setContentText("Déja dans les favoris");
				     		 dialog.showAndWait();
						 }
			         } else {
			         }
			 });
		 }
		 else {
			 Alert dialog = new Alert(AlertType.WARNING);
     		 dialog.setTitle("Erreur");
     		 dialog.setContentText("Aucune offre séléctionnée");
     		 dialog.showAndWait();
		 }
	 }
	 @FXML
	 public void deleteFromFavoris() throws IOException {
		 Offer offreselect = tableOfferAll.getSelectionModel().getSelectedItem();
		 if(offreselect != null) {
			 if(LoginController.user.supprFavoris(offreselect.getId_offer())){
				 Alert dialog = new Alert(AlertType.INFORMATION);
	     		 dialog.setTitle("Succés !");
	     		 dialog.setContentText("Offre supprimée des Favoris avec succés");
	     		 dialog.showAndWait();
			 }else {
				 Alert dialog = new Alert(AlertType.INFORMATION);
	     		 dialog.setTitle("Erreur");
	     		 dialog.setContentText("L'offre n'est pas dans les favoris");
	     		 dialog.showAndWait();
			 }
		 }
		 else {
			 Alert dialog = new Alert(AlertType.INFORMATION);
     		 dialog.setTitle("Erreur");
     		 dialog.setContentText("Aucune offre séléctionnée");
     		 dialog.showAndWait();
		 }
	 }
	 @FXML
	 public void apply() throws IOException {
		 String reg = "^$";
		 if(NbrPlaces.getText().matches(reg)) {
			 Alert alert = new Alert(AlertType.CONFIRMATION);
     		 alert.setTitle("Erreur");
     		 alert.setContentText("l'un des champs est vide . voulez sortir!");
     		 Optional<ButtonType> result = alert.showAndWait();
			 if(result.get() == ButtonType.OK) {
				 this.ApplyButton.getScene().getWindow().hide();
				 this.toOffresAll();
			 }
		 }else {
			 int resultat = LoginController.user.DemenderOffers(UserController.idOffreSelect,Integer.parseInt(NbrPlaces.getText()),Message.getText());
			 if(resultat == 1 ){
				 Alert dialog = new Alert(AlertType.INFORMATION);
	     		 dialog.setTitle("Succés !");
	     		 dialog.setContentText("Vous vous étes enregistré dans l'offre avec succés");
	     		 dialog.showAndWait();
	     		 if(this.ApplyButton != null) this.ApplyButton.getScene().getWindow().hide();
	     		 this.toOffresAll();
			 }else if(resultat == 0) {
				 Alert dialog = new Alert(AlertType.INFORMATION);
	     		 dialog.setTitle("Erreur !");
	     		 dialog.setContentText("le nombre de place insufisante !");
	     		 dialog.showAndWait();
			 } else{
				 Alert dialog = new Alert(AlertType.INFORMATION);
	     		 dialog.setTitle("Erreur");
	     		 dialog.setContentText("Une erreur s'est produite !");
	     		 dialog.showAndWait();
			 }
		 }
	 }
	 public ObservableList<String> listville = FXCollections.observableArrayList();
		@FXML
		private void loadeVille() throws IOException {
			listville.removeAll(list);
			listville.addAll("AL HAJEB", "AGADIR", "AL HOCEIMA", "ASSA ZAG" ,"AZILAL" ,"BENI MELLAL","BENSLIMANE", "BOUJDOUR", "BOULEMANE", "BERRECHID", "CASABLANCA", "CHEFCHAOUEN", "CHTOUKA AIT BAHA", "CHICHAOUA", "EL JADIDA", "EL KELAA DES SRAGHNAS", "ERRACHIDIA", "ESSAOUIRA", "ES SEMARA", "FES", "FIGUIG", "GUELMIM", "IFRANE", "KENITRA","KHEMISSET", "KHENIFRA", "KHOURIBGA", "LAAYOUNE", "LARACHE", "MOHAMMEDIA", "MARRAKECH", "MEKNES", "NADOR", "OUARZAZATE", "OUJDA", "OUED EDDAHAB", "RABAT", "SALE", "SKHIRAT", "TEMARA", "SEFROU", "SAFI", "SETTAT", "SIDI KACEM", "TANGER", "TAN TAN", "TAOUNAT", "TAROUDANNT", "TATA", "TAZA", "TETOUAN", "TIZNIT","ZAGORA");
			this.villeDep.getItems().addAll(listville);
			this.villeDep.valueProperty().addListener(new ChangeListener<String>() {
				@Override 
				public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String oldValue, String newValue) {                  
					if(newValue!=null) {
						villeDep.setEditable(false);
					}
	            }
			 });
			this.villeDar.getItems().addAll(listville);
			this.villeDar.valueProperty().addListener(new ChangeListener<String>() {
				@Override public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String oldValue, String newValue) {                  
					if(newValue!=null) {
						villeDar.setEditable(false);
					}
				}
			 });
		}
		@FXML 
		private void ModloadeVille() throws IOException{
			listville.removeAll(list);
			listville.addAll("AL HAJEB", "AGADIR", "AL HOCEIMA", "ASSA ZAG" ,"AZILAL" ,"BENI MELLAL","BENSLIMANE", "BOUJDOUR", "BOULEMANE", "BERRECHID", "CASABLANCA", "CHEFCHAOUEN", "CHTOUKA AIT BAHA", "CHICHAOUA", "EL JADIDA", "EL KELAA DES SRAGHNAS", "ERRACHIDIA", "ESSAOUIRA", "ES SEMARA", "FES", "FIGUIG", "GUELMIM", "IFRANE", "KENITRA","KHEMISSET", "KHENIFRA", "KHOURIBGA", "LAAYOUNE", "LARACHE", "MOHAMMEDIA", "MARRAKECH", "MEKNES", "NADOR", "OUARZAZATE", "OUJDA", "OUED EDDAHAB", "RABAT", "SALE", "SKHIRAT", "TEMARA", "SEFROU", "SAFI", "SETTAT", "SIDI KACEM", "TANGER", "TAN TAN", "TAOUNAT", "TAROUDANNT", "TATA", "TAZA", "TETOUAN", "TIZNIT","ZAGORA");
			this.ModvilleDep.getItems().addAll(listville);
			this.ModvilleDep.valueProperty().addListener(new ChangeListener<String>() {
				@Override public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String oldValue, String newValue) {
					if(newValue!=null) {
						ModvilleDep.setEditable(false);
					}
	            }
			 });
			this.ModvilleDar.getItems().addAll(listville);
			this.ModvilleDar.valueProperty().addListener(new ChangeListener<String>() {
				@Override public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String oldValue, String newValue) { 
					if(newValue!=null) {
						ModvilleDar.setEditable(false);
					}
	            }
			 });
		}
}