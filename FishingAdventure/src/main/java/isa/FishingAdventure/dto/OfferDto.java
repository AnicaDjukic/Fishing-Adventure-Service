package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.ServiceProfile;

public class OfferDto {

    private Double price;

    private Integer persons;

    private Integer serviceProfileId;

    public OfferDto(Double price, Integer persons, Integer serviceProfileId) {
        this.price = price;
        this.persons = persons;
        this.serviceProfileId = serviceProfileId;
    }

    public OfferDto() {
    }

    public OfferDto(ServiceProfile profile) {
        this.persons = profile.getPersons();
        this.serviceProfileId = profile.getId();
        this.price = profile.getPricePerDay();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public Integer getServiceProfileId() {
        return serviceProfileId;
    }

    public void setServiceProfileId(Integer serviceProfileId) {
        this.serviceProfileId = serviceProfileId;
    }
}
