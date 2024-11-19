package controller;

import config.HibernateUtil;
import entity.HotelRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
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
    private String searchAcStatus;
    private String searchRoomType;

    public void initialize() {
        colNo.setCellValueFactory(new PropertyValueFactory<>("number"));
        colAcStatus.setCellValueFactory(new PropertyValueFactory<>("acStatus"));
        colBedType.setCellValueFactory(new PropertyValueFactory<>("bedType"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        setCmbAcStatus();
        setCmbRoomType();
        loadTableData();
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
        searchAcStatus = cmbAcStatus.getValue().toLowerCase().trim();
        searchRoomType = cmbRoomType.getValue().toLowerCase().trim();
        loadTableData(searchAcStatus, searchRoomType);
    }

    public void reserveOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }

    private void loadTableData() {
        try (Session session = HibernateUtil.getSession()) {
            ObservableList<RoomTm> obList = FXCollections.observableArrayList();
            List<HotelRoom> hotelRooms = session.createQuery("FROM HotelRoom").list();

            for (HotelRoom tempRoom : hotelRooms) {
                obList.add(new RoomTm(tempRoom.getNumber(), tempRoom.getAcStatus(), tempRoom.getRoomType(),
                        tempRoom.getDescription(), tempRoom.getPrice()));
            }
            tblRooms.setItems(obList);
        }

    }

    private void loadTableData(String searchAcStatus, String searchRoomType) {
        try (Session session = HibernateUtil.getSession()) {
            ObservableList<RoomTm> obList = FXCollections.observableArrayList();
            List<HotelRoom> hotelRooms = session.createQuery("FROM HotelRoom").list();

            for (HotelRoom tempRoom : hotelRooms) {
                if (tempRoom.getRoomType().toLowerCase().equals(searchRoomType) &&
                        tempRoom.getAcStatus().toLowerCase().equals(searchAcStatus)) {
                    obList.add(new RoomTm(tempRoom.getNumber(), tempRoom.getAcStatus(), tempRoom.getRoomType(),
                            tempRoom.getDescription(), tempRoom.getPrice()));
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
