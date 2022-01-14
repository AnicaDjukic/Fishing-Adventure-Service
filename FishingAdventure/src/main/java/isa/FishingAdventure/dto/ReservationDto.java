package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.model.ServiceProfile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDto {

    private Integer id;

    private Integer serviceId;

    private String imagePath;

    private String serviceName;

    private Date startDate;

    private Date endDate;

    private int persons;

    private List<AdditionalService> chosenServices;

    private double price;

    private Integer reviewId;

    private String serviceType;

    public ReservationDto(Integer id, Integer serviceId, String imagePath, String serviceName, Date startDate,
            Date endDate, int persons, List<AdditionalService> chosenServices, double price, Integer reviewId,
            String serviceType) {
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
        this.serviceType = serviceType;
    }

    public ReservationDto() {

    }

    public ReservationDto(Reservation reservation, ServiceProfile serviceProfile) {
        this.id = reservation.getReservationId();
        this.startDate = reservation.getAppointment().getStartDate();
        this.endDate = reservation.getAppointment().getEndDate();
        this.persons = reservation.getAppointment().getMaxPersons();
        this.price = reservation.getAppointment().getPrice();
        this.chosenServices = new ArrayList<>(reservation.getAppointment().getChosenServices());
        this.serviceId = serviceProfile.getId();
        this.serviceName = serviceProfile.getName();
        if (serviceProfile instanceof FishingAdventure)
            this.serviceType = "adventure";
        else if (serviceProfile instanceof Boat)
            this.serviceType = "boat";
        else
            this.serviceType = "cottage";
        for (Image im : serviceProfile.getImages()) {
            if (im.isCoverImage()) {
                this.imagePath = im.getPath();
            }
        }
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
