package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AdditionalService;

import java.util.Date;
import java.util.List;

public class ReservationDto {

    private Integer id;

    private Integer serviceId;

    private String imagePath;

    private String serviceName;

    private Date startDate;

    private Date endDate;

    private int  persons;

    private List<AdditionalService> chosenServices;

    private double price;

    private Integer reviewId;

    public ReservationDto(Integer id, Integer serviceId, String imagePath, String serviceName, Date startDate, Date endDate, int persons, List<AdditionalService> chosenServices, double price, Integer reviewId) {
        this.id = id;
        this.serviceId = serviceId;
        this.imagePath = imagePath;
        this.serviceName = serviceName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.persons = persons;
        this.chosenServices = chosenServices;
        this.price = price;
        this.reviewId = reviewId;
    }

    public ReservationDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }
}
