package lk.ijse.FinalProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.FinalProject.TMmodel.EmployeeTM;
import lk.ijse.FinalProject.model.Employee;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class EmployeeManagementFormController {


    public TextField txtId;
    public TextField txtSalary;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtName;
    public TableColumn colEid;
    public TableColumn colEname;
    public TableColumn colEaddress;
    public TableColumn colEContact;
    public TableColumn colEsalary;
    public TableColumn colPosition;
    public TextField txtPosition;
    public TableView<EmployeeTM> tblEmployee;
    public TableColumn operateColId;

    private Pattern EmplIdpattern;
    private Pattern EmplNumpattern;
    private Pattern Empladdress;
    private Pattern EmplContact;
    private Pattern EmplSalary;
    private Pattern EmplPosition;


    private void setEmployeeValidation() {
        EmplIdpattern = Pattern.compile("U[0-9][0-9][0-9]");
        EmplNumpattern = Pattern.compile("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$");
        Empladdress = Pattern.compile("^[a-zA-Z-\\s]+$");
        EmplContact = Pattern.compile("^07(7|6|8|1|2|5|0|4)[0-9]{7}$");
        EmplSalary = Pattern.compile("[1-9][0-9]*(.[0-9]{2})?$");
        EmplPosition = Pattern.compile("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$");
    }

    Employee employee = null;

    public void AddOnAction(ActionEvent actionEvent) throws ClassNotFoundException {                //Add Employee OnAction
        boolean isEmployeeIdMatched = EmplIdpattern.matcher(txtId.getText()).matches();
        boolean isEmployeeNameMatched = EmplNumpattern.matcher(txtName.getText()).matches();
        boolean isEmployeeAddressMatched = Empladdress.matcher(txtAddress.getText()).matches();
        boolean isEmployeeContact = EmplContact.matcher(txtContact.getText()).matches();
        boolean isEmployeeSalary = EmplSalary.matcher(txtSalary.getText()).matches();
        boolean isEmployeePosition = EmplPosition.matcher(txtPosition.getText()).matches();

        if (isEmployeeIdMatched) {
            if (isEmployeeNameMatched) {
                if (isEmployeeAddressMatched) {
                    if (isEmployeeContact) {
                        if (isEmployeeSalary) {
                            if (isEmployeePosition) {
                                employee = new Employee(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(),
                                        Double.parseDouble(txtSalary.getText()), txtPosition.getText()
                                );
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid Position !").show();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid Salary!").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Contact !").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Address!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Name!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Id!").show();
        }
        try {
            if (CrudUtil.executeUpdate("INSERT INTO Employee VALUES (?,?,?,?,?,?)", employee.getId(), employee.getName(), employee.getAddress(),
                    employee.getContact(), employee.getSalary(), employee.getPosition())) ;
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed").show();
            txtId.clear();
            txtName.clear();
            txtAddress.clear();
            txtContact.clear();
            txtSalary.clear();
            txtPosition.clear();
            tblEmployee.refresh();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void initialize() {          //Load Da (Employee)
        colEid.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colEname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEaddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colEContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colEsalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        operateColId.setCellValueFactory(new PropertyValueFactory<>("btn"));
        try {
            loadAllEmployee();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setEmployeeValidation();
    }


    private void loadAllEmployee() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT *FROM Employee");
        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Button btn = new Button("Delete");
            obList.add(
                    new EmployeeTM(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDouble(5),
                            resultSet.getString(6),
                            btn
                    ));
            btn.setOnAction(event -> {
                final int index = tblEmployee.getSelectionModel().getSelectedIndex();
                if (index == 0) {
                    tblEmployee.getItems().remove(0);
                } else if (index == 1) {
                    tblEmployee.getItems().remove(1);
                } else if (index == 2) {
                    tblEmployee.getItems().remove(2);
                } else if (index == 3) {
                    tblEmployee.getItems().remove(3);
                } else if (index == 4) {
                    tblEmployee.getItems().remove(4);
                } else if (index == 5) {
                    tblEmployee.getItems().remove(5);
                } else if (index == 6) {
                    tblEmployee.getItems().remove(6);
                } else if (index == 7) {
                    tblEmployee.getItems().remove(7);
                } else if (index == 8) {
                    tblEmployee.getItems().remove(8);
                } else if (index == 9) {
                    tblEmployee.getItems().remove(9);
                } else if (index == 10) {
                    tblEmployee.getItems().remove(10);
                } else if (index == 11) {
                    tblEmployee.getItems().remove(11);
                } else if (index == 12) {
                    tblEmployee.getItems().remove(12);
                } else if (index == 13) {
                    tblEmployee.getItems().remove(13);
                } else if (index == 14) {
                    tblEmployee.getItems().remove(14);
                } else if (index == 15) {
                    tblEmployee.getItems().remove(15);
                } else if (index == 16) {
                    tblEmployee.getItems().remove(16);
                }
            });
        }
        tblEmployee.setItems(obList);
    }

    public void DeleteOnAction(ActionEvent actionEvent) {           //Delete OnAction
        search();
        try {
            boolean isDeleted = CrudUtil.executeUpdate("DELETE FROM Employee WHERE EmployeeId= ?", txtId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Succesed !").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Please Try Again !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtSalary.clear();
        txtPosition.clear();
    }

    public void updateOnAction(ActionEvent actionEvent) {       //Update OnAction
        //search();
        Employee employee = new Employee(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(),
                Double.parseDouble(txtSalary.getText()), txtPosition.getText()
        );
        try {
            boolean isUpdated = CrudUtil.executeUpdate("UPDATE Employee SET EmployeeName =?,EmployeeAddress=?,EmployeeContact=?,EmployeeSalary=?,EmplyoyeeJobtype=? WHERE EmployeeID= ?",
                    employee.getName(), employee.getAddress(), employee.getContact(), employee.getSalary(), employee.getPosition(), employee.getId());
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "EMPLOYEE UPDATED...! ").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtSalary.clear();
        txtPosition.clear();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {  //Search OnAction
        search();
    }

    public void searchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        search();
    }

    public void search() {
        try {
            ResultSet resultset = CrudUtil.executeQuery("SELECT *FROM Employee WHERE EmployeeID =?", txtId.getText());
            if (resultset.next()) {
                txtName.setText(resultset.getString(2));
                txtAddress.setText(resultset.getString(3));
                txtContact.setText(resultset.getString(4));
                txtSalary.setText(resultset.getString(5));
                txtPosition.setText(resultset.getString(6));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Employees").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
