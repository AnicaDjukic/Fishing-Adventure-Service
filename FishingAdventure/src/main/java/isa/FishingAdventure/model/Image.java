package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne(targetEntity = ServiceProfile.class,cascade = CascadeType.ALL)
    public ServiceProfile serviceProfile;

    public Image(Integer id, String path, ServiceProfile serviceProfile) {
        this.id = id;
        this.path = path;
        this.serviceProfile = serviceProfile;
    }

    public Image() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ServiceProfile getServiceProfile() {
        return this.serviceProfile;
    }

    public void setServiceProfile(ServiceProfile serviceProfile) {
        this.serviceProfile = serviceProfile;
    }

}