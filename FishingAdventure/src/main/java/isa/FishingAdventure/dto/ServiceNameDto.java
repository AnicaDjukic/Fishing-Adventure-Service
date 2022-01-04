package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.ServiceProfile;

public class ServiceNameDto {

    private Integer code;

    private String label;

    public ServiceNameDto(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public ServiceNameDto() {
    }

    public ServiceNameDto(ServiceProfile service) {
        this.code = service.getId();
        this.label = service.getName();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
