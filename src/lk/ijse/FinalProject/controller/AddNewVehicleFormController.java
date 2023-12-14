package lk.ijse.FinalProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.FinalProject.TMmodel.VehicleTM;
import lk.ijse.FinalProject.model.Vehicle;
import lk.ijse.FinalProject.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddNewVehicleFormController {

    public TextField txtId;
    public TextField txttype;
    public TextField txtnoplate;
    public TextField txtqty;
    public TextField txtfee;
    public TableView tblVehicle;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colnPlate;
    public TableColumn colQty;
    public TableColumn colHire;
    public TableColumn colOperate;

    private Pattern VehicleIdPattern;
    private Pattern VehicletypePattrn;
    private Pattern VehiclenumplatePattern;
    private Pattern VehicleqtyPattern;
    private Pattern VehilefeePatern;

    //Load All Data
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colnPlate.setCellValueFactory(new PropertyValueFactory<>("numplate"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colHire.setCellValueFactory(new PropertyValueFactory<>("perdaycost"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllData();
        SetVehicleValidation();
    }

    private void SetVehicleValidation() {
        VehicleIdPattern = Pattern.compile("V[0-9][0-9][0-9]");
        VehicletypePattrn = Pattern.compile("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$");
        VehiclenumplatePattern = Pattern.compile("^[A-Z]{1,4}-[0-9]{3,5}$");
        VehicleqtyPattern = Pattern.compile("^[1]$");
        VehilefeePatern = Pattern.compile("[1-9][0-9]*(.[0-9]{2})?$");
    }


    private void loadAllData() {
        try {
            ResultSet result = CrudUtil.executeQuery("SELECT *FROM Vehicle ");
            ObservableList<VehicleTM> obList = FXCollections.observableArrayList();
            while (result.next()) {
                Button btn = new Button("Delete");
                obList.add(new VehicleTM(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getDouble(5),
                        btn
                ));
                btn.setOnAction(event -> {
                    final int index =
                            tblVehicle.getSelectionModel().getSelectedIndex();
                    if (index == 0) {
                        tblVehicle.getItems().remove(0);
                    } else if (index == 1) {
                        tblVehicle.getItems().remove(1);
                    } else if (index == 2) {
                        tblVehicle.getItems().remove(2);
                    } else if (index == 3) {
                        tblVehicle.getItems().remove(3);
                    } else if (index == 4) {
                        tblVehicle.getItems().remove(4);
                    } else if (index == 5) {
                        tblVehicle.getItems().remove(5);
                    } else if (index == 6) {
                        tblVehicle.getItems().remove(6);
                    } else if (index == 7) {
                        tblVehicle.getItems().remove(7);
                    } else if (index == 8) {
                        tblVehicle.getItems().remove(8);
                    } else if (index == 9) {
                        tblVehicle.getItems().remove(9);
                    } else if (index == 10) {
                        tblVehicle.getItems().remove(10);
                    } else if (index == 11) {
                        tblVehicle.getItems().remove(11);
                    } else if (index == 12) {
                        tblVehicle.getItems().remove(12);
                    } else if (index == 13) {
                        tblVehicle.getItems().remove(13);
                    } else if (index == 14) {
                        tblVehicle.getItems().remove(14);
                    } else if (index == 15) {
                        tblVehicle.getItems().remove(15);
                    } else if (index == 16) {
                        tblVehicle.getItems().remove(16);
                    }
                });
            }
            tblVehicle.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void daysHireOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ForDaysHireForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    Vehicle vehicle = null;

    public void addOnAction(ActionEvent actionEvent) {
        boolean isVehiIdmatched = VehicleIdPattern.matcher(txtId.getText()).matches();
        boolean isVehiTypemached = VehicletypePattrn.matcher(txttype.getText()).matches();
        boolean isVehiNumplatemached = VehiclenumplatePattern.matcher(txtnoplate.getText()).matches();
        boolean isVehicleqtymatched = VehicleqtyPattern.matcher(txtqty.getText()).matches();
        boolean isVehiclefeematched = VehilefeePatern.matcher(txtfee.getText()).matches();


        if (isVehiIdmatched) {
            if (isVehiTypemached) {
                if (isVehiNumplatemached) {
                    if (isVehicleqtymatched) {
                        if (isVehiclefeematched) {
                            vehicle = new Vehicle(txtId.getText(), txttype.getText(), txtnoplate.getText(), txtqty.getText(),
                                    Double.parseDouble(txtfee.getText()
                                    ));
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid Per Day Cost !").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Quantity !").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Number Plate !").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Name !").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Id !").show();
        }
        try {
            if (CrudUtil.executeUpdate("INSERT INTO Vehicle VALUES(?,?,?,?,?)", vehicle.getId(), vehicle.getType(), vehicle.getNumplate(),
                    vehicle.getQty(), vehicle.getPerdaycost())) ;
            txtId.clear();
            txttype.clear();
            txtnoplate.clear();
            txtqty.clear();
            txtfee.clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully").show();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        tblVehicle.refresh();
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        Vehicle vehicle = new Vehicle(txtId.getText(), txttype.getText(), txtnoplate.getText(), txtqty.getText(),
                Double.parseDouble(txtfee.getText())
        );
        boolean isUpdated = CrudUtil.executeUpdate("UPDATE Vehicle SET VehicleType=?,VehicleNoPlate=?,Vehicleqty=?,dayperCost=? WHERE VehicleID=?",
                txttype.getText(), txtnoplate.getText(), txtqty.getText(), Double.parseDouble(txtfee.getText()), txtId.getText()
        );
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully !").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Have Not Updated Try Again !");
        }
        txtId.clear();
        txttype.clear();
        txtnoplate.clear();
        txtqty.clear();
        txtfee.clear();
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
                txttype.setText(result.getString(2));
                txtnoplate.setText(result.getString(3));
                txtqty.setText(result.getString(4));
                txtfee.setText(result.getString(5));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Vehicle !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblVehicle.refresh();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        search();
        try {
            boolean isDeleted = CrudUtil.executeUpdate("DELETE FROM Vehicle WHERE VehicleID=?", txtId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delete Successfully!").show();
            }
            txtId.clear();
            txttype.clear();
            txtnoplate.clear();
            txtqty.clear();
            txtfee.clear();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
