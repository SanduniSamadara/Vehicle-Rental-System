package lk.ijse.FinalProject.controller;

import lk.ijse.FinalProject.model.Customer;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerCrudController {
    //get  Customer Only Id
    public static ArrayList<String> getCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT  CustID FROM Customer");// should be idetyfied because  give your types,there fore you must assinged Variable
        ArrayList<String>id=new ArrayList<>();
        while (resultSet.next()){               //data base eken ena data extract karagana(next ()>>) true venkan,after arrayList eke variable ekta assing karaganna  (Data base eken dena ids tika)
            id.add(resultSet.getString(1));
        }
        return id; // rent order form ekta return krnau labai
    }
    //get Customer details According to the Customer Id
    public  static  Customer getCustomer(String id) throws SQLException, ClassNotFoundException {  //Rent order ekt customer Object ekak passa karai
       ResultSet resultSet= CrudUtil.executeQuery("SELECT *FROM Customer WHERE CustId=?",id);
       if(resultSet.next()){
           return new Customer(
                   resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getString(3),
                   resultSet.getString(4),
                   resultSet.getString(5)
           );
       }
       return  null;
    }
}
