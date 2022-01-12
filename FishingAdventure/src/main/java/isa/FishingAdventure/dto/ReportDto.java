package isa.FishingAdventure.dto;

public class ReportDto {

    private String content;

    private String option;

    private Integer reservationId;

    public ReportDto(String content, String option, Integer reservationId) {
        this.content = content;
        this.option = option;
        this.reservationId = reservationId;
    }

    public ReportDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }
}
