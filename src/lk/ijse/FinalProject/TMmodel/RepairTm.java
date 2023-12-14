package lk.ijse.FinalProject.TMmodel;

import javafx.scene.control.Button;

public class RepairTm {
     private String repairid;
     private double repairPrize;
     private String vehicleId;
     private Button btn;

    public RepairTm() {

    }

    public RepairTm(String repairid, double repairPrize, String vehicleId, Button btn) {
        this.repairid = repairid;
        this.repairPrize = repairPrize;
        this.vehicleId = vehicleId;
        this.btn = btn;
    }

    public String getRepairid() {
        return repairid;
    }

    public void setRepairid(String repairid) {
        this.repairid = repairid;
    }

    public double getRepairPrize() {
        return repairPrize;
    }

    public void setRepairPrize(double repairPrize) {
        this.repairPrize = repairPrize;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "RepairTm{" +
                "repairid='" + repairid + '\'' +
                ", repairPrize=" + repairPrize +
                ", vehicleId='" + vehicleId + '\'' +
                ", btn=" + btn +
                '}';
    }
}
