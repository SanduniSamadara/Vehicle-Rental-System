package lk.ijse.FinalProject.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.FinalProject.TMmodel.VehicleTM;
import lk.ijse.FinalProject.model.Vehicle;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllVehicleFormController {
    public JFXTextField VehicleIdtxt;
    public JFXTextField Vehicletypetxt;
    public JFXTextField VehiclenoPlatetxt;
    public TableColumn colvid;
    public TableColumn colVtype;
    public TableColumn colNumplate;
    public TableColumn colOperate;
    public JFXTextField qtyId;
    public TableView<VehicleTM> tblAllVehicle;
    public JFXTextField txtDayPerCost;
    public  static String index;


    public void initialize() {
        colvid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVtype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colNumplate.setCellValueFactory(new PropertyValueFactory<>("numplate"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        try {
            loadAllVehicleData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllVehicleData() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT *FROM Vehicle");
        ObservableList<VehicleTM> oblist = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Button btn = new Button("show");
            oblist.add(new VehicleTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    btn
            ));
            btn.setOnAction(event -> {
                Stage stage = new Stage();
                VehicleTM selectedVehicle = tblAllVehicle.getSelectionModel().getSelectedItem();




                

            });
        }
        tblAllVehicle.setItems(oblist);
    }
    public void txtIdOnAction(KeyEvent keyEvent) {

    }

    public void VehicletxtIdOnAction(ActionEvent actionEvent) {
        search();
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search() {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT *FROM Vehicle WHERE VehicleID=?", VehicleIdtxt.getText());
            if (resultSet.next()) {
                VehicleIdtxt.setText(resultSet.getString(1));
                Vehicletypetxt.setText(resultSet.getString(2));
                VehiclenoPlatetxt.setText(resultSet.getString(3));
                qtyId.setText(resultSet.getString(4));

            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
