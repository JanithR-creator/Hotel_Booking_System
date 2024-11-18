package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SpendsFormController {
    public AnchorPane context;
    public Label lblName;
    public Label lblNic;
    public Label lblRoomNo;
    public TableView tblSpends;
    public TableColumn tblId;
    public TableColumn tblRestaurantCharge;
    public TableColumn tblGymCharge;
    public TableColumn tblBarCharge;
    public TableColumn tblSpaCharge;
    public TableColumn tblRoomCharge;
    public TableColumn tblServiceCharge;
    public Label lblTotal;

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }

    public void addSpendOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddSpendsForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
