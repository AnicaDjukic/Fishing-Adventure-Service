package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.InstructorAvailability;

import java.util.Date;

public class InstructorAvailabilityDto {

    private Integer id;

    private Date startDate;

    private Date endDate;

    public InstructorAvailabilityDto() {
    }

    public InstructorAvailabilityDto(Integer id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public InstructorAvailabilityDto(InstructorAvailability instructorAvailability) {
        this.id = instructorAvailability.getId();
        this.startDate = instructorAvailability.getStartDate();
        this.endDate = instructorAvailability.getEndDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
