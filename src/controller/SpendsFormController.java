package controller;

import config.HibernateUtil;
import entity.Customer;
import entity.CustomerRoom;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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
    public static String Nic;

    public void initialize() {
        try (Session session = HibernateUtil.getSession()) {
            Customer customer = session.find(Customer.class, Nic);
            String sql = "SELECT * FROM CustomerRoom WHERE customerNic = :nic";
            NativeQuery<CustomerRoom> query = session.createNativeQuery(sql, CustomerRoom.class);
            query.setParameter("nic", Nic);
            CustomerRoom room = query.getSingleResult();
            lblName.setText(customer.getName());
            lblNic.setText(Nic);
            lblRoomNo.setText(Integer.toString(room.getNumber()));
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void addSpendOnAction(ActionEvent actionEvent) throws IOException {
        AddSpendsFormController.customerNic = Nic;
        AddSpendsFormController.roomNo = Integer.parseInt(lblRoomNo.getText());
        setUi("AddSpendsForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
