package lk.ijse.FinalProject.model;

import javafx.scene.control.Button;

public class CartTM {
    private String id;
    private String type;
    private String numplate;
    private int qty;
    private double perdaycost;
    private int spenddays;
    private double totalcost;
    private Button btn;


    public CartTM() {
    }

    public CartTM(String id, String type, String numplate, int qty, double perdaycost, int spenddays, double totalcost, Button btn) {
        this.id = id;
        this.type = type;
        this.numplate = numplate;
        this.qty=qty;
        this.perdaycost = perdaycost;
        this.spenddays = spenddays;
        this.totalcost = totalcost;
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
    //==
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPerdaycost() {
        return perdaycost;
    }

    public void setPerdaycost(double perdaycost) {
        this.perdaycost = perdaycost;
    }

    public int getSpenddays() {
        return spenddays;
    }

    public void setSpenddays(int spenddays) {
        this.spenddays = spenddays;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }



    @Override
    public String toString() {
        return "CartTM{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", numplate='" + numplate + '\'' +
                ", qty=" + qty +
                ", perdaycost=" + perdaycost +
                ", spenddays=" + spenddays +
                ", totalcost=" + totalcost +
                ", btn=" + btn +
                '}';
    }
}
