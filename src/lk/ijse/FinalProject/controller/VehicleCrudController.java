package lk.ijse.FinalProject.controller;

import lk.ijse.FinalProject.model.Vehicle;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleCrudController {
    //get Vehicle id According to database
    public static ArrayList<String> getVehicleId() throws SQLException, ClassNotFoundException {      //RentForm ekta passe krene Array List ekak nisa
        ResultSet resultSet = CrudUtil.executeQuery("SELECT VehicleID FROM Vehicle");//Data base ekta request karayi
        ArrayList<String> id = new ArrayList<>();
        while (resultSet.next()) {     //ids extract kaaramin array List referece ekta da gani...
            id.add(resultSet.getString(1));
        }
        return id;  //array list ek lese return karavi
    }
    //Get Vehicle details according to vehicle Id

    public static Vehicle getVehicle(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT *FROM Vehicle WHERE VehicleID=?",id);    //Requst to data base & ema data refernce ekkakata store kara ganima
        if(resultSet.next()){                                                                     // resulset refernce eken ekin eka refernce ekta extract kara ganima
           return new Vehicle(
                   resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getString(3),
                   resultSet.getString(4),
                   resultSet.getDouble(5)
           );
        }
        return null;
    }
}
