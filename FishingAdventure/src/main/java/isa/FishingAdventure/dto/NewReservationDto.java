package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AdditionalService;

import java.util.Date;
import java.util.List;

public class NewReservationDto {

    private Integer serviceId;

    private Date startDate;

    private Date endDate;

    private int  persons;

    private List<AdditionalService > chosenServices;

    private double price;

    private boolean ownersPresence;

    private String clientEmail;

    public NewReservationDto(Integer cottageId, Date startDate, Date endDate, int persons, List<AdditionalService> chosenServices, double price, boolean ownersPresence) {
        this.serviceId = cottageId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.persons = persons;
        this.chosenServices = chosenServices;
        this.price = price;
        this.ownersPresence = ownersPresence;
    }

    public NewReservationDto(Integer serviceId, Date startDate, Date endDate, int persons,
                             List<AdditionalService> chosenServices, double price, boolean ownersPresence,
                             String clientEmail) {
        this.serviceId = serviceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.persons = persons;
        this.chosenServices = chosenServices;
        this.price = price;
        this.ownersPresence = ownersPresence;
        this.clientEmail = clientEmail;
    }

    public NewReservationDto() {

    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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

    public boolean isOwnersPresence() {
        return ownersPresence;
    }

    public void setOwnersPresence(boolean ownersPresence) {
        this.ownersPresence = ownersPresence;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
