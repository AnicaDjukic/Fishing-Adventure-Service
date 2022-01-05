package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AdditionalService;

import java.util.Date;
import java.util.List;

public class NewReservationDto {

    private Integer cottageId;

    private Date startDate;

    private Date endDate;

    private int  persons;

    private List<AdditionalService > chosenServices;

    private double price;

    public NewReservationDto(Integer cottageId, Date startDate, Date endDate, int persons, List<AdditionalService> chosenServices, double price) {
        this.cottageId = cottageId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.persons = persons;
        this.chosenServices = chosenServices;
        this.price = price;
    }

    public NewReservationDto() {

    }

    public Integer getCottageId() {
        return cottageId;
    }

    public void setCottageId(Integer cottageId) {
        this.cottageId = cottageId;
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

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public List<AdditionalService> getChosenServices() {
        return chosenServices;
    }

    public void setChosenServices(List<AdditionalService> chosenServices) {
        this.chosenServices = chosenServices;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
