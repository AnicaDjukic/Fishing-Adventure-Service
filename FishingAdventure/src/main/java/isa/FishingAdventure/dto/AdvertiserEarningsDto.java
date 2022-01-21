package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AdvertiserEarnings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdvertiserEarningsDto {

    private Integer id;

    private String advertiserEmail;

    private Double amountEarned;

    private String dateOfTransaction;

    public AdvertiserEarningsDto() {
    }

    public AdvertiserEarningsDto(Integer id, String advertiserEmail, Double amountEarned, Date dateOfTransaction) {
        this.id = id;
        this.advertiserEmail = advertiserEmail;
        this.amountEarned = amountEarned;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dateOfTransaction = dateFormat.format(dateOfTransaction);
    }

    public AdvertiserEarningsDto(AdvertiserEarnings earnings) {
        this.id = earnings.getId();
        this.advertiserEmail = earnings.getAdvertiserEmail();
        this.amountEarned = earnings.getAmountEarned();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dateOfTransaction = dateFormat.format(earnings.getDateOfTransaction());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdvertiserEmail() {
        return advertiserEmail;
    }

    public void setAdvertiserEmail(String advertiserEmail) {
        this.advertiserEmail = advertiserEmail;
    }

    public Double getAmountEarned() {
        return amountEarned;
    }

    public void setAmountEarned(Double amountEarned) {
        this.amountEarned = amountEarned;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }
}
