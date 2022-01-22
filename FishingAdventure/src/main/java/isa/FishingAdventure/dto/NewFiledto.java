package isa.FishingAdventure.dto;

import java.util.List;

public class NewFiledto {

    private List<String> images;
    private String serviceName;
    private Integer coverImage;

    public NewFiledto() {
    }

    public NewFiledto(List<String> images, String serviceName) {
        this.images = images;
        this.serviceName = serviceName;
    }

    public List<String> getImages() {
        return this.images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getCoverImage() {
        return this.coverImage;
    }

    public void setCoverImage(Integer coverImage) {
        this.coverImage = coverImage;
    }

}
