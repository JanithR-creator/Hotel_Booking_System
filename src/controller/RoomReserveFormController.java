package controller;

import config.HibernateUtil;
import entity.Customer;
import entity.CustomerRoom;
import entity.HotelRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import view.tm.RoomTm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomReserveFormController {
    public AnchorPane context;
    public ComboBox<String> cmbAcStatus;
    public ComboBox<String> cmbRoomType;
    public TableView<RoomTm> tblRooms;
    public TableColumn<?, ?> colNo;
    public TableColumn<?, ?> colAcStatus;
    public TableColumn<?, ?> colBedType;
    public TableColumn<?, ?> colDesc;
    public TableColumn<?, ?> colPrice;
    public static Customer customer;
    private String searchAcStatus;
    private String searchRoomType;
    private RoomTm tm;


    public void initialize() {
        colNo.setCellValueFactory(new PropertyValueFactory<>("number"));
        colAcStatus.setCellValueFactory(new PropertyValueFactory<>("acStatus"));
        colBedType.setCellValueFactory(new PropertyValueFactory<>("bedType"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        setCmbAcStatus();
        setCmbRoomType();
        loadTableData();
        tblRooms.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        tm = newValue;
                    }
                });
    }

    public void setCmbAcStatus() {
        ArrayList<String> acStatus = new ArrayList<>(Arrays.asList("Ac", "Non Ac"));
        ObservableList<String> obList = FXCollections.observableArrayList(acStatus);
        cmbAcStatus.setItems(obList);
    }

    public void setCmbRoomType() {
        ArrayList<String> types = new ArrayList<>(Arrays.asList("Single", "Double", "Family"));
        ObservableList<String> obList = FXCollections.observableArrayList(types);
        cmbRoomType.setItems(obList);
    }

    public void searchOnAction(ActionEvent actionEvent) {

        if (cmbRoomType.getValue() == null) {
            new Alert(Alert.AlertType.INFORMATION, "Please Select Room Type.").show();
            return;
        } else if (cmbAcStatus.getValue() == null) {
            new Alert(Alert.AlertType.INFORMATION, "Please Select Ac/Non Ac.").show();
            return;
        }
        searchAcStatus = cmbAcStatus.getValue().toLowerCase().trim();
        searchRoomType = cmbRoomType.getValue().toLowerCase().trim();
        loadTableData(searchAcStatus, searchRoomType);
        cmbAcStatus.setValue(null);
        cmbRoomType.setValue(null);
    }

    public void reserveOnAction(ActionEvent actionEvent) throws IOException {
        if (tm == null) {
            new Alert(Alert.AlertType.INFORMATION, "Please Select Your Room.").show();
            return;
        }
        addCustomerRoom(customer, tm);
        setUi("DashBoardForm");
    }

    public void addCustomerRoom(Customer customer, RoomTm tm) {
        try (Session session = HibernateUtil.getSession()) {
            CustomerRoom customerRoom = new CustomerRoom(
                    tm.getNumber(), tm.getAcStatus(), tm.getBedType(),
                    tm.getDescription(), tm.getPrice()
            );
            customerRoom.setCustomer(customer);
            Transaction transaction = session.beginTransaction();
            session.save(customerRoom);
            transaction.commit();

        }
    }

    private void loadTableData() {
        try (Session session = HibernateUtil.getSession()) {
            ObservableList<RoomTm> obList = FXCollections.observableArrayList();

            String hql = "FROM HotelRoom h WHERE h.number NOT IN (SELECT c.number FROM CustomerRoom c)";
            List<HotelRoom> availableRooms = session.createQuery(hql, HotelRoom.class).getResultList();

            for (HotelRoom availableRoom : availableRooms) {
                obList.add(new RoomTm(availableRoom.getNumber(), availableRoom.getAcStatus(),
                        availableRoom.getRoomType(), availableRoom.getDescription(),
                        availableRoom.getPrice()));
            }

            tblRooms.setItems(obList);
        }
    }

    private void loadTableData(String searchAcStatus, String searchRoomType) {
        try (Session session = HibernateUtil.getSession()) {
            ObservableList<RoomTm> obList = FXCollections.observableArrayList();
            List<HotelRoom> hotelRooms = session.createQuery("FROM HotelRoom").list();

            for (HotelRoom tempRoom : hotelRooms) {

            }
            tblRooms.setItems(obList);
        }
        try (Session session = HibernateUtil.getSession()) {
            ObservableList<RoomTm> obList = FXCollections.observableArrayList();

            String hql = "FROM HotelRoom h WHERE h.number NOT IN (SELECT c.number FROM CustomerRoom c)";
            List<HotelRoom> availableRooms = session.createQuery(hql, HotelRoom.class).getResultList();

            for (HotelRoom availableRoom : availableRooms) {
                if (availableRoom.getRoomType().toLowerCase().equals(searchRoomType) &&
                        availableRoom.getAcStatus().toLowerCase().equals(searchAcStatus)) {
                    obList.add(new RoomTm(availableRoom.getNumber(), availableRoom.getAcStatus(), availableRoom.getRoomType(),
                            availableRoom.getDescription(), availableRoom.getPrice()));
                }
            }

            tblRooms.setItems(obList);
        }

    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
