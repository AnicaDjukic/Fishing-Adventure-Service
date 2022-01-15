package isa.FishingAdventure.dto;

public class ReservationInfoDto {
    private Integer reservationId;

    private Integer serviceId;

    private String advertiserEmail;

    private String clientEmail;

    public ReservationInfoDto() {
    }

    public ReservationInfoDto(Integer reservationId, Integer serviceId, String advertiserEmail, String clientEmail) {
        this.reservationId = reservationId;
        this.serviceId = serviceId;
        this.advertiserEmail = advertiserEmail;
        this.clientEmail = clientEmail;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getAdvertiserEmail() {
        return advertiserEmail;
    }

    public void setAdvertiserEmail(String advertiserEmail) {
        this.advertiserEmail = advertiserEmail;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
