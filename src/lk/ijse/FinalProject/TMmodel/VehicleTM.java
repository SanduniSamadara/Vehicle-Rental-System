package lk.ijse.FinalProject.TMmodel;

import javafx.scene.control.Button;

public class VehicleTM {

        private String id;
        private String type;
        private String numplate;
        private String qty;
        private double perdaycost;
        private Button btn;

    public VehicleTM() {
    }

    public VehicleTM(String id, String type, String numplate, String qty, double perdaycost, Button btn) {
        this.id = id;
        this.type = type;
        this.numplate = numplate;
        this.qty = qty;
        this.perdaycost = perdaycost;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumplate() {
        return numplate;
    }

    public void setNumplate(String numplate) {
        this.numplate = numplate;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public double getPerdaycost() {
        return perdaycost;
    }

    public void setPerdaycost(double perdaycost) {
        this.perdaycost = perdaycost;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "VehicleTM{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", numplate='" + numplate + '\'' +
                ", qty='" + qty + '\'' +
                ", perdaycost=" + perdaycost +
                ", btn=" + btn +
                '}';
    }
}
