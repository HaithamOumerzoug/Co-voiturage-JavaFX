package application;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
 


public class Main  extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
		Scene scene = new Scene(root,750,500);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Co-Voiturage Application");
		primaryStage.getIcons().add(new Image("file:../../Images/icon.png"));
		primaryStage.show();
		
		
	} 
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
