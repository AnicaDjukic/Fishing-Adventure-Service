package isa.FishingAdventure.dto;

public class ReservationIssueDto {
    private Integer id;

    private String content;

    private String clientFullName;

    private String advertiserFullName;

    private String serviceName;

    private String clientEmail;

    private String advertiserEmail;

    public ReservationIssueDto() {
    }

    public ReservationIssueDto(Integer id, String content, String clientFullName, String advertiserFullName, String serviceName, String clientEmail, String advertiserEmail) {
        this.id = id;
        this.content = content;
        this.clientFullName = clientFullName;
        this.advertiserFullName = advertiserFullName;
        this.serviceName = serviceName;
        this.clientEmail = clientEmail;
        this.advertiserEmail = advertiserEmail;
    }

    public ReservationIssueDto(Integer id, String content, String clientEmail, String advertiserEmail) {
        this.id = id;
        this.content = content;
        this.clientEmail = clientEmail;
        this.advertiserEmail = advertiserEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getAdvertiserFullName() {
        return advertiserFullName;
    }

    public void setAdvertiserFullName(String advertiserFullName) {
        this.advertiserFullName = advertiserFullName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getAdvertiserEmail() {
        return advertiserEmail;
    }

    public void setAdvertiserEmail(String advertiserEmail) {
        this.advertiserEmail = advertiserEmail;
    }
}
