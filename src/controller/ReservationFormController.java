package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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

    public void backOnAction(ActionEvent actionEvent) {
    }

    public void availableOnAction(ActionEvent actionEvent) {
    }

    public void amontOnAction(ActionEvent actionEvent) {

    }
}
