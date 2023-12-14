package lk.ijse.FinalProject.controller;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.FinalProject.TMmodel.CustomerTM;
import lk.ijse.FinalProject.model.Customer;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerManageFormController {

    public TextField txtId;
    public TextField txtContact;
    public TextField txtNic;
    public TextField txtName;
    public TableView<CustomerTM> customerTable;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOperate;
    public TextField txtAddress;

    private Pattern customerIdpattern;
    private Pattern customerNpattern;
    private Pattern customerNicpattern;
    private Pattern customerAddresspattern;
    private Pattern customerContactpattterns;


    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllCustomer();
        SetCustomerValidation();
    }

    private void SetCustomerValidation() {
        customerIdpattern = Pattern.compile("C[0-9][0-9][0-9]");
        customerNpattern = Pattern.compile("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$");
        //customerNicpattern=Pattern.compile("");
        customerAddresspattern = Pattern.compile("^[a-zA-Z-\\s]+$");
        customerContactpattterns = Pattern.compile("^07(7|6|8|1|2|5|0|4)-[0-9]{7}$");
    }


    private void loadAllCustomer() { //Load All Customer Table
        try {
            ResultSet resultset = CrudUtil.executeQuery("SELECT *FROM Customer");
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
            while (resultset.next()) {
                Button btn = new Button("Delete");
                obList.add(new CustomerTM(
                        resultset.getString(1),
                        resultset.getString(2),
                        resultset.getString(3),
                        resultset.getString(4),
                        resultset.getString(5),
                        btn
                ));
                btn.setOnAction(event -> {
                    final int index = customerTable.getSelectionModel().getSelectedIndex();
                    if (index == 0) {
                        customerTable.getItems().remove(0);
                    } else if (index == 1) {
                        customerTable.getItems().remove(1);
                    } else if (index == 2) {
                        customerTable.getItems().remove(2);
                    } else if (index == 3) {
                        customerTable.getItems().remove(3);
                    } else if (index == 4) {
                        customerTable.getItems().remove(4);
                    } else if (index == 5) {
                        customerTable.getItems().remove(5);
                    } else if (index == 6) {
                        customerTable.getItems().remove(6);
                    } else if (index == 7) {
                        customerTable.getItems().remove(7);
                    } else if (index == 8) {
                        customerTable.getItems().remove(8);
                    } else if (index == 9) {
                        customerTable.getItems().remove(9);
                    } else if (index == 10) {
                        customerTable.getItems().remove(10);
                    } else if (index == 11) {
                        customerTable.getItems().remove(11);
                    } else if (index == 12) {
                        customerTable.getItems().remove(12);
                    } else if (index == 13) {
                        customerTable.getItems().remove(13);
                    } else if (index == 14) {
                        customerTable.getItems().remove(14);
                    } else if (index == 15) {
                        customerTable.getItems().remove(15);
                    } else if (index == 16) {
                        customerTable.getItems().remove(16);
                    }
                });
            }
            customerTable.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void addOnAction(ActionEvent actionEvent) {
        Customer customer = null;

        boolean isCustomeridMatched = customerIdpattern.matcher(txtId.getText()).matches();
        boolean isCustomerNameMatched = customerNpattern.matcher(txtName.getText()).matches();
        //boolean isCustomerNicMatched=customerNicpattern.matcher(txtNic.getText()).matches();
        boolean isCustomerAddressMatched = customerAddresspattern.matcher(txtAddress.getText()).matches();
        boolean isCustomerContactMatched = customerContactpattterns.matcher(txtContact.getText()).matches();

      /*  Customer customer = new Customer(txtId.getText()
                , txtName.getText(), txtNic.getText(), txtAddress.getText(),
                txtContact.getText());*/


        if (isCustomeridMatched) {
            if (isCustomerNameMatched) {
                if (isCustomerAddressMatched) {
                    if (isCustomerContactMatched) {
                        customer = new Customer(txtId.getText()
                                , txtName.getText(), txtNic.getText(), txtAddress.getText(),
                                txtContact.getText());
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Contact Number").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Address !").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Name !").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Id Number !, Try Again !").show();
        }
        try {
            if (CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?)", customer.getId(), customer.getName(), customer.getNic(),
                    customer.getAddress(), customer.getContact()
            )) ;
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully").show();
            txtId.clear();
            txtName.clear();
            txtNic.clear();
            txtAddress.clear();
            txtContact.clear();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void txtFieldOnKeyRealized(KeyEvent keyEvent) {

    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        search();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search() {
        try {
            ResultSet resultset = CrudUtil.executeQuery("SELECT *FROM Customer WHERE CustID=?", txtId.getText());
            if (resultset.next()) {
                txtName.setText(resultset.getString(2));
                txtNic.setText(resultset.getString(3));
                txtAddress.setText(resultset.getString(4));
                txtContact.setText(resultset.getString(5));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Customer !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(txtId.getText(), txtName.getText(), txtNic.getText(), txtAddress.getText(),
                txtContact.getText());
        try {
            boolean isUpdated = CrudUtil.executeUpdate("UPDATE Customer SET CustName=?,CustNic=?,CustAddress=?,CustContact=? WHERE CustID=?",
                    customer.getName(), customer.getNic(), customer.getAddress(),
                    customer.getContact(), customer.getId());
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update Successed ").show();
            } else {
                new Alert(Alert.AlertType.WARNING, " Have not Update Try Again !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        search();
        boolean isDeleted = CrudUtil.executeUpdate("DELETE FROM Customer WHERE CustID =?", txtId.getText());
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Delete Successed !").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Try Again  !").show();
        }
        txtId.clear();
        txtName.clear();
        txtNic.clear();
        txtAddress.clear();
        txtContact.clear();
    }
}

