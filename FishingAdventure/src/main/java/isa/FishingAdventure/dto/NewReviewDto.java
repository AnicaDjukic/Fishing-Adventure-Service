package isa.FishingAdventure.dto;

public class NewReviewDto {

    private Integer reservationId;

    private String content;

    private  Integer rate;

    public NewReviewDto(Integer reservationId, String content, Integer rate) {
        this.reservationId = reservationId;
        this.content = content;
        this.rate = rate;
    }

    public NewReviewDto() {
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
