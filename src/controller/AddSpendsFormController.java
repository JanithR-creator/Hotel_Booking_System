package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class AddSpendsFormController {
    public ImageView context;
    public TextField txtResCharge;
    public TextField txtGymCharge;
    public TextField txtSpaCharge;
    public TextField txtBarCharge;
    public Label lblName;
    public Label lblNic;

    public void addOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SpendsForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
