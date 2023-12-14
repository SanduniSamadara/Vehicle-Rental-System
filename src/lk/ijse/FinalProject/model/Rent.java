package lk.ijse.FinalProject.model;

import lk.ijse.FinalProject.TMmodel.RentTM;

import java.sql.Date;
import java.time.LocalDate;

public class Rent  {
    private String id;
    private String StartDate;
    private LocalDate ReserveDate;
    private String CustomerId;

    public Rent() {
    }

    public Rent(String id, String startDate, LocalDate reserveDate, String customerId) {
        this.id = id;
        StartDate = startDate;
        ReserveDate = reserveDate;
        CustomerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public LocalDate getReserveDate() {
        return ReserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        ReserveDate = reserveDate;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id='" + id + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", ReserveDate='" + ReserveDate + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                '}';
    }
}
