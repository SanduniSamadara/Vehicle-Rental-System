package lk.ijse.FinalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.FinalProject.db.DBConnection;
import lk.ijse.FinalProject.model.*;
import lk.ijse.FinalProject.util.CrudUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RentManageFormController {
    public JFXComboBox<String> cmbCustomer;
    public JFXTextField txtCustName;
    public JFXTextField txtCustNic;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCustContact;
    public JFXComboBox<String> cmbVehicle;
    public JFXTextField txtVehicleName;
    public JFXTextField txtVehicleNoplate;
    public JFXTextField txtVehicleqty;
    public JFXTextField txtVehiclePerDayCost;
    public JFXTextField txtVehicleSpendDays;
    public TableView<CartTM> tblcartTM;
    public TableColumn colid;
    public TableColumn coltype;
    public TableColumn colnoplate;
    public TableColumn colcost;
    public TableColumn colspendays;
    public TableColumn coltotalcost;
    public TableColumn colbutton;
    public Label lblOrderTotal;
    public JFXDatePicker CalenderId;
    public Label lbldate;

    public void initialize() {
        setDate();
        disPlayCartTm();
        setCustomerId();
        setVehicleID();
        //---------------------------------------------------------------------
        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> //Customer Form eken  dataupdate karta passe new value ek reent Orderr eken display kirimata
                setCustomerDetail(newValue));
        //-----------------------------------------------------------------------
        cmbVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                setVehicleDetail(newValue));
    }

    private void setDate() {
        lbldate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private void setVehicleDetail(String selectedVehicleId) {
        try {
            Vehicle v = VehicleCrudController.getVehicle(selectedVehicleId);    //Vehicle Crud eke Vehicle Object eka passe kara atha & assing the references
            if (v != null) {
                txtVehicleName.setText(v.getType());                          //Set values  the text Field
                txtVehicleNoplate.setText(v.getNumplate());
                txtVehicleqty.setText(v.getQty());
                txtVehiclePerDayCost.setText(String.valueOf(v.getPerdaycost()));   // double Casting to string
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Vehicle !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setVehicleID() {           // 1st Set Vehicle ID to vehicle Combom box
        try {
            ObservableList<String> viObList = FXCollections.observableArrayList(   //Vehicle Crud eken iNport krna array List ek Observeble list ekta set krirma
                    VehicleCrudController.getVehicleId()  //method  call from Vehicle Crud Controller
            );
            cmbVehicle.setItems(viObList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerDetail(String selectedCustomerId) {
        try {
            Customer c = CustomerCrudController.getCustomer(selectedCustomerId);
            if (c != null) {
                txtCustName.setText(c.getName());
                txtCustNic.setText(c.getNic());
                txtCustAddress.setText(c.getAddress());
                txtCustContact.setText(c.getContact());
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setCustomerId() {
        try {
            ObservableList<String> cidObList = FXCollections.observableArrayList(  //CustomerCrud Controller eke ids get kara gani.
                    CustomerCrudController.getCustomerId()
            );
            cmbCustomer.setItems(cidObList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("../view/CustomerManageForm.fxml"))
        ));
        stage.show();
    }

    private void disPlayCartTm() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colnoplate.setCellValueFactory(new PropertyValueFactory<>("numplate"));
        colcost.setCellValueFactory(new PropertyValueFactory<>("perdaycost"));
        colspendays.setCellValueFactory(new PropertyValueFactory<>("spenddays"));
        coltotalcost.setCellValueFactory(new PropertyValueFactory<>("totalcost"));
        colbutton.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    ObservableList<CartTM> tmList = FXCollections.observableArrayList();          //Cart tm object ekta refer karagatta  values and data Table ekta danna redi vei   Obsaerveble list ek eliyen danne siyalum values Table ekta fill venna one nisa

    public void addToCartOnAction(ActionEvent actionEvent) {
        double perDaycost = Double.parseDouble(txtVehiclePerDayCost.getText());     //gananayta avashya data reference vlta ganima
        int spendDay = Integer.parseInt(txtVehicleSpendDays.getText());
        int qty = Integer.parseInt(txtVehicleqty.getText());
        double totalcost = qty * perDaycost * spendDay;                                       // Sulukirima
        Button btn = new Button("Remove ");


        CartTM isExists = isExists(cmbVehicle.getValue());          //Vehicle Id without null nam tm list eken ema row ektama new values add  kalayuthui
        if (isExists != null) {
            for (CartTM temp : tmList) {
                if (temp.equals(isExists)) {
                    temp.setSpenddays(temp.getSpenddays() + spendDay);          //new Spend days ekta ekthu kalayuthui
                    temp.setTotalcost(temp.getTotalcost() + totalcost);         //New total ekta kalin total eka ekathu kalayuthui
                }
            }
        } else {
            //-----------------------------------------------------------------------Add the CartTm Table
            CartTM cartTM = new CartTM(
                    cmbVehicle.getValue(),                      // Get the all values
                    txtVehicleName.getText(),                   // Only get The text Field
                    txtVehicleNoplate.getText(),
                    qty,
                    perDaycost,
                    spendDay,
                    totalcost,
                    btn
            );
            btn.setOnAction(event -> {                  //Cart Tm ekehi athi button ekta OnAction
                for (CartTM tempTm : tmList) {              //Tm list eken reference ekta dagatt
                    if (tempTm.getId().equals(cartTM.getId())) {
                        tmList.remove(tempTm);
                        calculateTotal();               //because aduvena values gananaya Kirimata
                    }
                }
            });
            tmList.add(cartTM);         // cart tm eke values Observeble list ekta store kara gani
            tblcartTM.setItems(tmList); //Obserble refernce eke store karagat data <CratTm> Table ekta add karai
        }
        calculateTotal();
        tblcartTM.refresh();
    }

    public void removeOnAction(ActionEvent actionEvent) {              //1st Select Row After Delete the This row
        final int index = tblcartTM.getSelectionModel().getSelectedIndex();
        if (index == 0) {
            tblcartTM.getItems().remove(0);
        } else if (index == 1) {
            tblcartTM.getItems().remove(1);
        } else if (index == 2) {
            tblcartTM.getItems().remove(2);
        } else if (index == 3) {
            tblcartTM.getItems().remove(3);
        } else if (index == 4) {
            tblcartTM.getItems().remove(4);
        } else if (index == 5) {
            tblcartTM.getItems().remove(5);
        } else if (index == 6) {
            tblcartTM.getItems().remove(6);
        }
    }

    private CartTM isExists(String vehicleId) {             //Dublicate vehicle cartTm ekta add kara ganimedi ehi new row ekak laba ganima valakvima
        for (CartTM cartTM : tmList) {
            if (cartTM.getId().equals(vehicleId)) {
                return cartTM;
            }
        }
        return null;
    }

    private void calculateTotal() {                                   // Full Total Calculation
        double total = 0;
        for (CartTM cartTM : tmList) {
            total = total + cartTM.getTotalcost();
        }
        lblOrderTotal.setText(String.valueOf(total));
    }


    public void RentOrderOnAction(ActionEvent actionEvent) throws SQLException {
        Rent rent = new Rent(                                              //1st rent object ekk create krala rent table ekta pass krai
                "R004",
                lbldate.getText(),
                CalenderId.getValue(),
                cmbCustomer.getValue()
        );
        ArrayList<RentDetail> details = new ArrayList<>();
        for (CartTM cartTM : tmList) {
            details.add(
                    new RentDetail(
                            "R004",
                            cartTM.getId(),
                            cartTM.getSpenddays(),
                            cartTM.getQty(),
                            cartTM.getPerdaycost()
                    ));
        }
        //---------------------------------Using Transaction Part
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);        // setAutocomits(false) kalavita table vlta data damimata Stand byva thabagani
            boolean isRentSaved = new RentCrudController().saveRent(rent);
            if (isRentSaved) {
                boolean isDetailSaved = new RentCrudController().saveRentDetails(details);
                if (isDetailSaved) {
                    connection.commit();            // Data Input karanna puluvan u vita athulatn kirima
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed !").show();
                } else {
                    connection.rollback();   // table vlta input kale nattam e values thibuna thanaama tiyanna
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            } else {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            connection.setAutoCommit(true);
        }


    }

    public void RentOrderDetailOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/RentAndDetailForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();



    }
   /* private String generatePaymentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT RentID FROM Rent ORDER BY RentID DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()) {

            int tempId = Integer.
                    parseInt(rst.getString(1).split("")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "P-00" + tempId;
            } else if (tempId < 99) {

                return "P-0" + tempId;

            } else {
                return "P-" + tempId;
            }

        } else {
            return "P-001";
        }
    }*/


}
