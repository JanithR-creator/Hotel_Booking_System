package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationFormController {
    public ImageView context;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtContact;
    public DatePicker dateCheckIn;
    public DatePicker dateCheckOut;
    public TextField txtAcStatus;
    public TextField txtRoomType;
    public TableView tblRoomNum;
    public TableColumn colRoomNum;
    public Text lblAmount;

    public void reserveOnAction(ActionEvent actionEvent) {
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }

    public void availableOnAction(ActionEvent actionEvent) {
    }

    public void amountOnAction(ActionEvent actionEvent) {

    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
