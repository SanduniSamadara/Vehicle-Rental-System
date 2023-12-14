package lk.ijse.FinalProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.FinalProject.TMmodel.RentTM;
import lk.ijse.FinalProject.model.RentDetail;
import lk.ijse.FinalProject.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentAndDetailFormController {

    public TableColumn colrentId;
    public TableColumn StartDate;
    public TableColumn ReserveDate;
    public TableColumn CustomerId;
    public TableColumn ColOperate;
    public TableView<RentTM> tblrent;
    public TableView <RentDetail>tblRentDetail;
    public TableColumn colRenrIdDetail;
    public TableColumn colVehiId;
    public TableColumn colSpendDay;
    public TableColumn colVehicleQty;
    public TableColumn colDayPerCost;

    public void initialize() {
        colRenrIdDetail.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVehiId.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        colSpendDay.setCellValueFactory(new PropertyValueFactory<>("ReserveDate"));
        colVehicleQty.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colDayPerCost.setCellValueFactory(new PropertyValueFactory<>("btn"));




        try {
            displayerentDetailData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadRentData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DisplayData();
    }

    private void displayerentDetailData() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.executeQuery("SELECT *FROM Rentdetail");
        ObservableList<RentDetail> obList = FXCollections.observableArrayList();
        while(resultSet.next()){
            obList.add(new RentDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5)
            ));
        }
        tblRentDetail.setItems(obList);


    }

    private void DisplayData() {
        colrentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        StartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        ReserveDate.setCellValueFactory(new PropertyValueFactory<>("ReserveDate"));
        CustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        ColOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }


    private void loadRentData() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery("SELECT * FROM Rent");
        ObservableList<RentTM> obList = FXCollections.observableArrayList();
        while (result.next()) {
            Button btn = new Button("Delete");

            obList.add(new RentTM(
                    result.getString(1),
                    result.getString(2),
                    result.getDate(3),
                    result.getString(4),
                    btn
            ));
            btn.setOnAction(event -> {
                final int index = tblrent.getSelectionModel().getSelectedIndex();
                if (index == 0) {
                    tblrent.getItems().remove(0);
                } else if (index == 1) {
                    tblrent.getItems().remove(1);
                } else if (index == 2) {
                    tblrent.getItems().remove(2);
                } else if (index == 3) {
                    tblrent.getItems().remove(3);
                } else if (index == 4) {
                    tblrent.getItems().remove(4);
                } else if (index == 4) {
                    tblrent.getItems().remove(4);
                } else if (index == 5) {
                    tblrent.getItems().remove(5);
                } else if (index == 6) {
                    tblrent.getItems().remove(6);
                } else if (index == 7) {
                    tblrent.getItems().remove(7);
                } else if (index == 8) {
                    tblrent.getItems().remove(8);
                } else if (index == 9) {
                    tblrent.getItems().remove(9);
                } else if (index == 10) {
                    tblrent.getItems().remove(10);
                } else if (index == 11) {
                    tblrent.getItems().remove(11);
                } else if (index == 12) {
                    tblrent.getItems().remove(12);
                } else if (index == 13) {
                    tblrent.getItems().remove(13);
                } else if (index == 14) {
                    tblrent.getItems().remove(14);
                } else if (index == 15) {
                    tblrent.getItems().remove(15);
                } else if (index == 16) {
                    tblrent.getItems().remove(16);
                }

            });
        }
        //tblRent.getItems().removeAll(obList);
        tblrent.setItems(obList);
        tblrent.refresh();


    }


    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
