package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.ServiceProfile;

public class OfferDto {

    private Double price;

    private Integer persons;

    public OfferDto(Double price, Integer persons) {
        this.price = price;
        this.persons = persons;
    }

    public OfferDto() {
    }

    public OfferDto(ServiceProfile profile) {
        this.persons = profile.getPersons();
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
}
