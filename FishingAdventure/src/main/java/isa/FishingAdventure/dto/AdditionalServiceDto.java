package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AdditionalService;

public class AdditionalServiceDto {

    private Integer id;

    private String name;

    private double price;

    public AdditionalServiceDto(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public AdditionalServiceDto(AdditionalService additionalService){
        this.id = additionalService.getId();
        this.name = additionalService.getName();
        this.price = additionalService.getPrice();
    }

    public AdditionalServiceDto(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
