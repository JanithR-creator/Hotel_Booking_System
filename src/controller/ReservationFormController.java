package controller;

import config.HibernateUtil;
import entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReservationFormController {
    public ImageView context;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtContact;
    public DatePicker dateCheckIn;
    public DatePicker dateCheckOut;

    public void reserveOnAction(ActionEvent actionEvent) throws IOException {
        if (txtNic.getText().isEmpty() || txtContact.getText().isEmpty() || txtName.getText().isEmpty() ||
                dateCheckIn.getValue() == null || dateCheckOut.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "You Are missing a field.").show();
            return;
        }

        Customer customer = new Customer(Long.parseLong(txtNic.getText()), txtName.getText(),
                Integer.parseInt(txtContact.getText()),
                dateCheckIn.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                dateCheckOut.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            setUi("RoomReserveForm");
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }


    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
