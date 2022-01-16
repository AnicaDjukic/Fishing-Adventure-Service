package isa.FishingAdventure.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewDto {

    private int id;

    private String content;

    private String clientFullName;

    private String serviceName;

    private String advertiserEmail;

    private int rating;

    private String datePosted;

    public ReviewDto() {
    }

    public ReviewDto(int id, String content, String clientFullName, String serviceName,
                     String advertiserEmail, int rating, Date datePosted) {
        this.id = id;
        this.content = content;
        this.clientFullName = clientFullName;
        this.serviceName = serviceName;
        this.advertiserEmail = advertiserEmail;
        this.rating = rating;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.datePosted = dateFormat.format(datePosted);
    }

    public ReviewDto(int id, String content, int rating, Date datePosted) {
        this.id = id;
        this.content = content;
        this.rating = rating;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.datePosted = dateFormat.format(datePosted);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAdvertiserEmail() {
        return advertiserEmail;
    }

    public void setAdvertiserEmail(String advertiserEmail) {
        this.advertiserEmail = advertiserEmail;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }
}
