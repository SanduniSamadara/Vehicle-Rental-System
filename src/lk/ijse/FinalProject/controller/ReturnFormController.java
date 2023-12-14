package lk.ijse.FinalProject.controller;

import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.FinalProject.TMmodel.RentTM;
import lk.ijse.FinalProject.model.Rent;
import lk.ijse.FinalProject.model.Vehicle;
import lk.ijse.FinalProject.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnFormController {

    public TableView tblVehicleForm;
    public TableColumn colId;
    public TableColumn coltype;
    public TableColumn colnoplate;
    public TableColumn colqty;
    public TableColumn colcost;
    public JFXTextField txtqty;
    public JFXTextField txtId;
    public JFXTextField vehicletype;
    public JFXTextField vehiclenoPlate;
    public JFXTextField vehicledaypercost;
    public TableView<RentTM> tblRent;
    public TableColumn colrentid;
    public TableColumn colstartdateid;
    public TableColumn colreservedateId;
    public TableColumn colcustomerId;
    public TableColumn colbutton;

    public void initialize() {
        viewRentdata();
        Viewdata();
        try {
            loadVehicleStore();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            loadRentData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void viewRentdata() {
        colrentid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colstartdateid.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        colreservedateId.setCellValueFactory(new PropertyValueFactory<>("ReserveDate"));
        colcustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colbutton.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void Viewdata() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colnoplate.setCellValueFactory(new PropertyValueFactory<>("numplate"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colcost.setCellValueFactory(new PropertyValueFactory<>("perdaycost"));
    }


    private void loadVehicleStore() throws SQLException, ClassNotFoundException {
        ResultSet resultset = CrudUtil.executeQuery("SELECT*FROM Vehicle");
        ObservableList<Vehicle> oblist = FXCollections.observableArrayList();
        while (resultset.next()) {
            oblist.add(new Vehicle(
                    resultset.getString(1),
                    resultset.getString(2),
                    resultset.getString(3),
                    resultset.getString(4),
                    resultset.getDouble(5)
            ));
        }
        tblVehicleForm.setItems(oblist);
        tblVehicleForm.refresh();
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
                final int index= tblRent.getSelectionModel().getSelectedIndex();
                if(index==0){
                    tblRent.getItems().remove(0);
                }else if(index==1){
                    tblRent.getItems().remove(1);
                }else if(index==2){
                    tblRent.getItems().remove(2);
                }else if(index==3){
                    tblRent.getItems().remove(3);
                }else if(index==4){
                    tblRent.getItems().remove(4);
                }else if(index==4){
                    tblRent.getItems().remove(4);
                }else if(index==5) {
                    tblRent.getItems().remove(5);
                }else if(index ==6){
                    tblRent.getItems().remove(6);
                }else if(index ==7){
                    tblRent.getItems().remove(7);
                }else if(index ==8){
                    tblRent.getItems().remove(8);
                }else if(index ==9){
                    tblRent.getItems().remove(9);
                }else if(index ==10){
                    tblRent.getItems().remove(10);
                }else if(index ==11){
                    tblRent.getItems().remove(11);
                }else if(index ==12){
                    tblRent.getItems().remove(12);
                }else if(index ==13){
                    tblRent.getItems().remove(13);
                }else if(index ==14){
                    tblRent.getItems().remove(14);
                }else if(index ==15){
                    tblRent.getItems().remove(15);
                }else if(index ==16){
                    tblRent.getItems().remove(16);
                }

            });
        }
        //tblRent.getItems().removeAll(obList);
        tblRent.setItems(obList);
        tblRent.refresh();
    }

    public void RepairOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("../view/RepairForm.fxml"))
        ));
        stage.setTitle("Repair Form");
        stage.show();
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = new Vehicle(txtId.getText(),
                vehicletype.getText(),
                vehiclenoPlate.getText(), txtqty.getText(),
                Double.parseDouble(vehicledaypercost.getText())
        );
        boolean isUpdated = CrudUtil.executeUpdate("UPDATE Vehicle SET VehicleType=?,VehicleNoPlate=?,Vehicleqty=?,dayperCost=? WHERE VehicleID=?",
                vehicletype.getText(), vehiclenoPlate.getText(), txtqty.getText(), Double.parseDouble(vehicledaypercost.getText()), txtId.getText()
        );
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Succesd !").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Have Not Updated Try Again !");
        }
        txtId.clear();
        vehicletype.clear();
        vehiclenoPlate.clear();
        txtqty.clear();
        vehicledaypercost.clear();
    }

    public void txtIdOnAction(ActionEvent actionEvent) {

        search();
    }

    public void searchOnAction(ActionEvent actionEvent) {

        search();
    }

    private void search() {
        try {
            ResultSet result = CrudUtil.executeQuery("SELECT * FROM Vehicle WHERE VehicleID=?", txtId.getText());
            if (result.next()) {
                vehicletype.setText(result.getString(2));
                vehiclenoPlate.setText(result.getString(3));
                txtqty.setText(result.getString(4));
                vehicledaypercost.setText(result.getString(5));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Vehicle !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblVehicleForm.refresh();
    }
}
