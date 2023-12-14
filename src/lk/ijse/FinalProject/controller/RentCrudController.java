package lk.ijse.FinalProject.controller;

import lk.ijse.FinalProject.model.Rent;
import lk.ijse.FinalProject.model.RentDetail;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class RentCrudController {
    public boolean saveRent(Rent rent) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Rent VALUES(?,?,?,?)",
                rent.getId(), rent.getStartDate(), rent.getReserveDate(), rent.getCustomerId());

    }

    public boolean saveRentDetails(ArrayList<RentDetail> details) throws SQLException, ClassNotFoundException {
        for (RentDetail data : details) {
            boolean isDetailSaved = CrudUtil.executeUpdate("INSERT INTO Rentdetail VALUES(?,?,?,?,?)",
                    data.getRentId(), data.getVehicleId(), data.getSpendDays(), data.getQty(),
                    data.getPerDayCost());
            if (isDetailSaved) {
                if (!UpdateVehicleqty(data.getVehicleId(), data.getQty())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean UpdateVehicleqty(String vehicleId, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Vehicle SET  Vehicleqty= Vehicleqty-? WHERE VehicleID=?", qty, vehicleId);
    }
}
