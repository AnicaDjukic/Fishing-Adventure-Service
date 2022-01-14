package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.AvailabilityDateRange;

import java.util.Date;

public class AvailablityDateRangeDto {

    private Integer id;

    private String title;

    private Date start;

    private Date end;

    public AvailablityDateRangeDto(Integer id, String title, Date start, Date end) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public AvailablityDateRangeDto() {
    }

    public AvailablityDateRangeDto(AvailabilityDateRange dateRange) {
        this.id = dateRange.getId();
        this.start = dateRange.getStartDate();
        this.end = dateRange.getEndDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
