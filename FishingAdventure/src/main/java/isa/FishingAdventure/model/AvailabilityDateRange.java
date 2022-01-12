package isa.FishingAdventure.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AvailabilityDateRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @ManyToOne(targetEntity = ServiceProfile.class, cascade = CascadeType.MERGE)
    public ServiceProfile serviceProfile;

    public AvailabilityDateRange(Integer id, Date startDate, Date endDate, ServiceProfile serviceProfile) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.serviceProfile = serviceProfile;
    }

    public AvailabilityDateRange() {
    }

    public AvailabilityDateRange(Date startDate, Date endDate, ServiceProfile serviceProfile) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.serviceProfile = serviceProfile;
    }

    public ServiceProfile getServiceProfile() {
        return serviceProfile;
    }

    public void setServiceProfile(ServiceProfile serviceProfile) {
        this.serviceProfile = serviceProfile;
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
