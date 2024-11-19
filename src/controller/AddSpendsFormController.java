package controller;

import com.sun.deploy.trace.Trace;
import config.HibernateUtil;
import entity.Bill;
import entity.Customer;
import entity.CustomerRoom;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;

public class AddSpendsFormController {
    public ImageView context;
    public TextField txtResCharge;
    public TextField txtGymCharge;
    public TextField txtSpaCharge;
    public TextField txtBarCharge;
    public Label lblName;
    public Label lblNic;
    public static String customerNic;
    public static int roomNo;

    public void initialize() {
        try (Session session = HibernateUtil.getSession()) {
            Customer customer = session.find(Customer.class, customerNic);
            lblName.setText(customer.getName());
            lblNic.setText(customerNic);
        }
    }

    public void addOnAction(ActionEvent actionEvent) throws IOException {

        try (Session session = HibernateUtil.getSession()) {
            CustomerRoom room = session.find(CustomerRoom.class, roomNo);
            Customer customer = session.find(Customer.class, customerNic);

            if (SpendsFormController.newCustomerStatus) {
                Bill bill = new Bill(
                        Integer.parseInt(txtResCharge.getText()),
                        Integer.parseInt(txtGymCharge.getText()),
                        Integer.parseInt(txtBarCharge.getText()),
                        Integer.parseInt(txtSpaCharge.getText()),
                        room.getPrice(),
                        Integer.parseInt(txtResCharge.getText()) * 0.3
                );
                bill.setCustomer(customer);
                Transaction transaction = session.beginTransaction();
                session.save(bill);
                transaction.commit();
            } else {
                String sql = "SELECT * FROM Bill WHERE customer_id = :nic";
                NativeQuery<Bill> query = session.createNativeQuery(sql, Bill.class);
                query.setParameter("nic", customerNic);
                Bill bill1 = query.getSingleResult();

                bill1.setRoomCharge(room.getPrice());
                bill1.setServiceCharge(Integer.parseInt(txtResCharge.getText()) * 0.3);
                bill1.setRestCharge(Integer.parseInt(txtResCharge.getText()));
                bill1.setSpaCharge(Integer.parseInt(txtSpaCharge.getText()));
                bill1.setGymCharge(Integer.parseInt(txtGymCharge.getText()));
                bill1.setBarCharge(Integer.parseInt(txtBarCharge.getText()));
                bill1.setCustomer(customer);
                Transaction transaction = session.beginTransaction();
                session.saveOrUpdate(bill1);
                transaction.commit();
            }
            setUi("SpendsForm");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong...").show();
        }

    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SpendsForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
