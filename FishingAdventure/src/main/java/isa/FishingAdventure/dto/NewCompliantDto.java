package isa.FishingAdventure.dto;

public class NewCompliantDto {

    private Integer reservationId;

    private String content;

    private Integer serviceId;

    public NewCompliantDto(Integer reservationId, String content, Integer serviceId) {
        this.reservationId = reservationId;
        this.content = content;
        this.serviceId = serviceId;
    }

    public NewCompliantDto() {
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
