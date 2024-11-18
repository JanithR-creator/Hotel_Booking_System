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
import java.nio.file.SecureDirectoryStream;
import java.util.List;

public class RoomReserveFormController {
    public AnchorPane context;
    public ComboBox cmbAcStatus;
    public ComboBox cmbBedType;
    public TableView tblRooms;
    public TableColumn colNo;
    public TableColumn colAcStatus;
    public TableColumn colBedType;
    public TableColumn colDesc;
    public TableColumn colPrice;

    public void initialize(){
        colNo.setCellValueFactory(new PropertyValueFactory<>("number"));
        colAcStatus.setCellValueFactory(new PropertyValueFactory<>("acStatus"));
        colBedType.setCellValueFactory(new PropertyValueFactory<>("bedType"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        loadTableData();
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void reserveOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }

    private void loadTableData(){
        try(Session session = HibernateUtil.getSession()) {
            ObservableList<RoomTm> obList = FXCollections.observableArrayList();
            List<HotelRoom> hotelRooms = session.createQuery("FROM HotelRoom").list();

            for(HotelRoom tempRoom : hotelRooms){
                obList.add(new RoomTm(tempRoom.getNumber(),tempRoom.getAcStatus(),tempRoom.getBedType(),
                        tempRoom.getDescription(),tempRoom.getPrice()));
            }
            tblRooms.setItems(obList);
        }

    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
