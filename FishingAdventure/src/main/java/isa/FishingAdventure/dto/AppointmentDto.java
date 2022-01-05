package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AdditionalService;

import java.util.Date;
import java.util.Set;

public class AppointmentDto {

    private Integer serviceProfileId;

    private double discount;

    private Date startDate;

    private Date endDate;

    private int maxPersons;

    private double price;

    public Set<AdditionalService> chosenServices;

    public AppointmentDto(Integer serviceProfileId, double discount, Date startDate, Date endDate, int maxPersons, double price, Set<AdditionalService> chosenServices) {
        this.serviceProfileId = serviceProfileId;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPersons = maxPersons;
        this.price = price;
        this.chosenServices = chosenServices;
    }

    public AppointmentDto() {
    }

    public Integer getServiceProfileId() {
        return serviceProfileId;
    }

    public void setServiceProfileId(Integer serviceProfileId) {
        this.serviceProfileId = serviceProfileId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<AdditionalService> getChosenServices() {
        return chosenServices;
    }

    public void setChosenServices(Set<AdditionalService> chosenServices) {
        this.chosenServices = chosenServices;
    }
}
