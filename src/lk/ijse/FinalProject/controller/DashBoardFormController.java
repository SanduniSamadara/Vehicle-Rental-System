package lk.ijse.FinalProject.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.FinalProject.TMmodel.VehicleTM;
import lk.ijse.FinalProject.db.DBConnection;
import lk.ijse.FinalProject.model.Vehicle;
import lk.ijse.FinalProject.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DashBoardFormController {
    public AnchorPane contest;
    public Label lblTime;
    public Label lblDate;
    public TableColumn ColId;
    public TableColumn colType;
    public TableColumn colqty;
    public TableView <Vehicle>tblAvailable;
    public Label totalRentId;
    public Label totalRentId2;
    public Label totalRentId3;
    public Label totalCustomer;
    // private Map<String, Object> paraMap;

    public void initialize() {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        loadAlldata();
        RunningTime();
        try {
            LoadAllData();
        } catch (SQLException e) {
            System.out.println(e);
           // e.printStackTrace();
        } catch ( ClassNotFoundException  e) {
            System.out.println(e);
         //   e.printStackTrace();
        }

    }
    public void LoadAllData() throws SQLException, ClassNotFoundException {
        ResultSet result1=CrudUtil.executeQuery("SELECT COUNT(RentID)FROM Rent");
        if(result1.next()) {
            totalRentId.setText(result1.getString(1));
        }
        ResultSet result2=CrudUtil.executeQuery("SELECT COUNT( EmployeeID) FROM Employee");
        if(result2.next()){
            totalRentId2.setText(result2.getString(1));
        }
        ResultSet resultSet=CrudUtil.executeQuery("SELECT COUNT (VehicleID) FROM  Vehicle");
        if(resultSet.next()){
            totalRentId3.setText(resultSet.getString(1));
        }
       /* ResultSet result4=CrudUtil.executeQuery("SELECT COUNT (CustID) FROM customer");
        if(result4.next()){
            totalCustomer.setText(result4.getString(1));
        }*/

    }

    private void loadAlldata()  {
        try {
            ResultSet result = CrudUtil.executeQuery("SELECT *FROM Vehicle ");
            ObservableList<Vehicle> obList = FXCollections.observableArrayList();
            while(result.next()){
                obList.add(new Vehicle(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getDouble(5)

                ));
            }
            tblAvailable.setItems(obList);

        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void RunningTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(time);
                });
            }
        });
        thread.start();
    }


    public void HomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contest.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));

    }

    public void AddVehicleOnActon(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/AddNewVehicleForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }

    public void AllVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/AllVehicleForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }

    public void RentOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/RentManageForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);

    }

    public void ReturnOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/ReturnForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }


    public void EmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/EmployeeManagementForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }

    public void ReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        //System.out.println("Hi");
      //  JasperReport compileReport=(JasperReport)JRLoader.loadObject(this.getClass().getResource("/lk/ijse/FinalProject/view/reports/Rent.jrxml"));
       // JasperReport jasperPrint=JasperFillManager.fillReport(compileReport,null,new JREmptyDataSource(1));

       /* try {


            //2STEP----compile the reports
            JasperReport compileReport= JasperCompileManager.compileReport(load);

            //3STEP fill the informationn which reprt needed
            JasperPrint jasperPrint=  JasperFillManager.fillReport(compileReport,null, new JREmptyDataSource(1));
            //4STEP----

            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }*/
        ///JasperDesign load=JRXmlLoader.load(this,getClass().getResourceAsStream("lk/ijse/FinalProject/view/reports/Rent.jrxml"));
        JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/lk/ijse/FinalProject/view/reports/RentAll.jrxml"));
        JasperReport compileReport=JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint=JasperFillManager.fillReport(compileReport,null,new JREmptyDataSource(1));
        JasperViewer.viewReport(jasperPrint,false);





    }

    public void LogoutOnAction(ActionEvent actionEvent) {

    }

    public void CustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/CustomerManageForm.fxml "));
        contest.getChildren().clear();
        contest.getChildren().add(parent);


    }
}
