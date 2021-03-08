package phone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import phone.Main;

public class MainViewController3 {
	@FXML
	AnchorPane root;
	Main main= new Main();
    @FXML
    void onClickAdd2(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/Add.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onClickDelete2(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/Delete.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onClickSearch2(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/Search.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onClickUpdate2(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/Update.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void onClickBack(ActionEvent event) {
      	main.showMainView();
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("../view/AddressMain.fxml"));
			Main.mainLayout.setCenter(root);
			Main.setPrimaryStage("�ּҷ� ����");
		} catch(Exception e) {
			e.printStackTrace();
		}
 
    }

}
