package controller;

import config.HibernateUtil;
import decorator.SpendsManager;
import entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class LoginFormController {
    public TextField txtNic;
    public AnchorPane context;

    public void seeAmountOnAction(ActionEvent actionEvent) throws IOException {
        try (Session session = HibernateUtil.getSession()) {
            Customer customer = session.find(Customer.class, txtNic.getText());
            if (customer == null) {
                new Alert(Alert.AlertType.ERROR, "No Customer With Nic " + txtNic.getText()).show();
                return;
            }
            SpendsFormController.Nic = txtNic.getText();
            SpendsManager.Nic=txtNic.getText();
            setUi("SpendsForm");
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
