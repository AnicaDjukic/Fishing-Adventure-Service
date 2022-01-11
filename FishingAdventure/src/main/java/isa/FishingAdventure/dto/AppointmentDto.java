package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.model.ServiceProfile;

import java.time.Duration;
import java.util.Date;
import java.util.Set;

public class AppointmentDto {


    private Integer offerId;

    private Integer serviceProfileId;

    private String serviceProfileName;

    private String coverImage;

    private double discount;

    private Date startDate;

    private Date endDate;

    private int maxPersons;

    private double price;

    private Set<AdditionalService> chosenServices;

    private Date dateCreated;

    private Duration duration;

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

    public AppointmentDto(Appointment appointment) {
        this.offerId = appointment.getAppointmentId();
        this.discount = appointment.getDiscount();
        this.startDate = appointment.getStartDate();
        this.endDate = appointment.getEndDate();
        this.maxPersons = appointment.getMaxPersons();
        this.price = appointment.getPrice();
        this.chosenServices = appointment.getChosenServices();
        this.dateCreated = appointment.getDateCreated();
        this.duration = appointment.getDuration();
    }

    public AppointmentDto(Appointment appointment, ServiceProfile profile) {
        this.offerId = appointment.getAppointmentId();
        this.discount = appointment.getDiscount();
        this.startDate = appointment.getStartDate();
        this.endDate = appointment.getEndDate();
        this.maxPersons = appointment.getMaxPersons();
        this.price = appointment.getPrice();
        this.chosenServices = appointment.getChosenServices();
        this.dateCreated = appointment.getDateCreated();
        this.duration = appointment.getDuration();
        this.serviceProfileName = profile.getName();
        this.serviceProfileId = profile.getId();
        for (Image img : profile.getImages()) {
            if (img.isCoverImage()) {
                this.coverImage = img.getPath();
                break;
            }
        }
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getServiceProfileName() {
        return serviceProfileName;
    }

    public void setServiceProfileName(String serviceProfileName) {
        this.serviceProfileName = serviceProfileName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
