package isa.FishingAdventure.dto;

public class ComplaintResponseDto {
    private Integer complaintId;
    private String clientMessage;
    private String advertiserMessage;
    private String clientEmail;
    private String advertiserEmail;

    public ComplaintResponseDto() {
    }

    public ComplaintResponseDto(Integer complaintId, String clientMessage, String advertiserMessage, String clientEmail, String advertiserEmail) {
        this.complaintId = complaintId;
        this.clientMessage = clientMessage;
        this.advertiserMessage = advertiserMessage;
        this.clientEmail = clientEmail;
        this.advertiserEmail = advertiserEmail;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public String getClientMessage() {
        return clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public String getAdvertiserMessage() {
        return advertiserMessage;
    }

    public void setAdvertiserMessage(String advertiserMessage) {
        this.advertiserMessage = advertiserMessage;
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
