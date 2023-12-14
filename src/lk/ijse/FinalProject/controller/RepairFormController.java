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
import lk.ijse.FinalProject.TMmodel.RepairTm;
import lk.ijse.FinalProject.model.Repair;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RepairFormController {

    public JFXTextField repirId;
    public JFXTextField repairPaymenttxt;
    public JFXTextField vehiclesId;
    public TableView<RepairTm> tblrepair;
    public TableColumn colId;
    public TableColumn paymentId;
    public TableColumn vehicleId;
    public TableColumn colOperate;


    private Pattern repairIDpattern;
    private Pattern repirCostpattern;
    private Pattern vehicleIDpattern;

    public void initialize() {
        loadAllrepairdata();
        displayRepairData();
        repairValidation();
    }


    private void repairValidation() {
        repairIDpattern = Pattern.compile("K[0-9][0-9][0-9]");
        repirCostpattern = Pattern.compile("[1-9][0-9]*(.[0-9]{2})?$");
        vehicleIDpattern = Pattern.compile("V[0-9][0-9][0-9]");
    }


    private void displayRepairData() {
        colId.setCellValueFactory(new PropertyValueFactory<>("repairid"));
        paymentId.setCellValueFactory(new PropertyValueFactory<>("repairPrize"));
        vehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    private void loadAllrepairdata() {
        try {
            ResultSet result = CrudUtil.executeQuery("SELECT *FROM Repair");
            ObservableList<RepairTm> oblist = FXCollections.observableArrayList();
            while (result.next()) {
                Button btn = new Button("Delete");
                oblist.add(new RepairTm(
                        result.getString(1),
                        result.getDouble(2),
                        result.getString(3),
                        btn
                ));
                btn.setOnAction(event -> {
                    final int index = tblrepair.getSelectionModel().getSelectedIndex();
                    if (index == 0) {
                        tblrepair.getItems().remove(0);
                    } else if (index == 1) {
                        tblrepair.getItems().remove(1);
                    } else if (index == 2) {
                        tblrepair.getItems().remove(2);
                    } else if (index == 3) {
                        tblrepair.getItems().remove(3);
                    } else if (index == 4) {
                        tblrepair.getItems().remove(4);
                    } else if (index == 4) {
                        tblrepair.getItems().remove(4);
                    } else if (index == 5) {
                        tblrepair.getItems().remove(5);
                    } else if (index == 6) {
                        tblrepair.getItems().remove(6);
                    } else if (index == 7) {
                        tblrepair.getItems().remove(7);
                    } else if (index == 8) {
                        tblrepair.getItems().remove(8);
                    } else if (index == 9) {
                        tblrepair.getItems().remove(9);
                    } else if (index == 10) {
                        tblrepair.getItems().remove(10);
                    } else if (index == 11) {
                        tblrepair.getItems().remove(11);
                    } else if (index == 12) {
                        tblrepair.getItems().remove(12);
                    } else if (index == 13) {
                        tblrepair.getItems().remove(13);
                    } else if (index == 14) {
                        tblrepair.getItems().remove(14);
                    } else if (index == 15) {
                        tblrepair.getItems().remove(15);
                    } else if (index == 16) {
                        tblrepair.getItems().remove(16);
                    }
                });

            }
            tblrepair.setItems(oblist);
            tblrepair.refresh();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Repair repair = null;

    public void RepairOnAction(ActionEvent actionEvent) {
        boolean isRepairidMatched = repairIDpattern.matcher(repirId.getText()).matches();
        boolean isRepairCostMatched = repirCostpattern.matcher(repairPaymenttxt.getText()).matches();
        boolean isVehicleIdMatched = vehicleIDpattern.matcher(vehiclesId.getText()).matches();

        if (isRepairidMatched) {
            if (isRepairCostMatched) {
                if (isVehicleIdMatched) {
                    repair = new Repair(
                            repirId.getText(), Double.parseDouble(repairPaymenttxt.getText()
                    ), vehiclesId.getText());
                } else {
                    new Alert(Alert.AlertType.ERROR, "InValid Vehicle Number !").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Repair Cost! ").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Repair Id!").show();
        }
        try {
            if (CrudUtil.executeUpdate("INSERT INTO Repair VALUES (?,?,?)", repirId.getText(),
                    repairPaymenttxt.getText(), vehiclesId.getText()
            )) ;
            repirId.clear();
            repairPaymenttxt.clear();
            vehiclesId.clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Try Again");
        }
        tblrepair.refresh();

    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void txtrepairOnAction(ActionEvent actionEvent) {
    }
}
