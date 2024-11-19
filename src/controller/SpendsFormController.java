package controller;

import config.HibernateUtil;
import decorator.SpendsManager;
import entity.Bill;
import entity.Customer;
import entity.CustomerRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import view.tm.BillTm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpendsFormController {
    public AnchorPane context;
    public Label lblName;
    public Label lblNic;
    public Label lblRoomNo;
    public TableView<BillTm> tblSpends;
    public TableColumn<?, ?> colId;
    public TableColumn<?, ?> colRestaurantCharge;
    public TableColumn<?, ?> colGymCharge;
    public TableColumn<?, ?> colBarCharge;
    public TableColumn<?, ?> colSpaCharge;
    public TableColumn<?, ?> colRoomCharge;
    public TableColumn<?, ?> colServiceCharge;
    public Label lblTotal;
    public static String Nic;
    private double amount;
    public static boolean newCustomerStatus;

    public void initialize() {
        SpendsManager manager = new SpendsManager();
        amount = manager.manager();
        lblTotal.setText(Double.toString(amount));

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

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRestaurantCharge.setCellValueFactory(new PropertyValueFactory<>("restCharge"));
        colGymCharge.setCellValueFactory(new PropertyValueFactory<>("gymCharge"));
        colBarCharge.setCellValueFactory(new PropertyValueFactory<>("barCharge"));
        colSpaCharge.setCellValueFactory(new PropertyValueFactory<>("spaCharge"));
        colRoomCharge.setCellValueFactory(new PropertyValueFactory<>("roomCharge"));
        colServiceCharge.setCellValueFactory(new PropertyValueFactory<>("serviceCharge"));

        loadTableData();
    }

    public void loadTableData() {
        try (Session session = HibernateUtil.getSession()) {
            ObservableList<BillTm> obList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM Bill WHERE customer_id = :nic";
            NativeQuery<Bill> query = session.createNativeQuery(sql, Bill.class);
            query.setParameter("nic", Nic);
            Bill bill = query.getSingleResult();
            obList.add(new BillTm(
                    bill.getId(),
                    bill.getRestCharge(),
                    bill.getGymCharge(),
                    bill.getBarCharge(),
                    bill.getSpaCharge(),
                    bill.getRoomCharge(),
                    bill.getServiceCharge()
            ));
            tblSpends.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void addSpendOnAction(ActionEvent actionEvent) throws IOException {
        AddSpendsFormController.customerNic = Nic;
        AddSpendsFormController.roomNo = Integer.parseInt(lblRoomNo.getText());
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are New Customer.", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            newCustomerStatus = true;
        } else {
            newCustomerStatus = false;
        }
        setUi("AddSpendsForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
