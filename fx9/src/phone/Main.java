package phone;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage primaryStage;
	public static Stage stage;
	public static BorderPane mainLayout;
	
	public static String sw = "off";
	public static String login = "OFF";
	public static String position = null;
	public static String t_name;
	public static String m_name,m_phone,m_address;
	
	public static String main_username;
	public static String main_password;
	
	Parent root;

	
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;						
			
			showMainView();		
			showMainItems();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showMainView() {
		try {
			mainLayout = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
			Scene scene = new Scene(mainLayout,1500,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("���հ���");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
		
	public void showMainItems() {
		try {
			root = FXMLLoader.load(getClass().getResource("view/MainItem.fxml"));
			mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}			
	}		
	
	public static void showPizzaMenuView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/PizzaMenu.fxml"));
		AnchorPane checkView = loader.load();

		stage = new Stage();
		stage.setTitle("Pizza Order");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		Scene scene = new Scene(checkView);
		stage.setScene(scene);
		stage.showAndWait();

	}	
	
	public static void showEmployeeAddStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AddNewEmployee.fxml"));
		BorderPane addNewEmployee = loader.load();
		
		Stage stage = new Stage();
		stage.setTitle("Add New Employee");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		Scene scene = new Scene(addNewEmployee);
		stage.setScene(scene);
		stage.showAndWait();
	
	}
	
	public void stopMainView() {
		primaryStage.close();
	}
	
	public static void setPrimaryStage(String str) {
		primaryStage.setTitle(str);
	}
	
	public static void stopstageView() {
		stage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
