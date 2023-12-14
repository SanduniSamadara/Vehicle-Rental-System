package lk.ijse.FinalProject.TMmodel;

import javafx.scene.control.Button;

public class EmployeeTM {
    private String id;
    private String name;
    private String address;
    private String contact;
    private double salary;
    private String position;
    private Button btn;

    public EmployeeTM() {
    }

    public EmployeeTM(String id, String name, String address, String contact, double salary, String position, Button btn) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.salary = salary;
        this.position = position;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                ", btn=" + btn +
                '}';
    }
}
