package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.DeleteRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteRequestForAdminDto {

    private Integer id;

    private String email;

    private String requestContent;

    private String dateCreated;

    private String role;

    public DeleteRequestForAdminDto() {
    }

    public DeleteRequestForAdminDto(Integer id, String email, String requestContent, Date dateCreated, String role) {
        this.id = id;
        this.email = email;
        this.requestContent = requestContent;
        this.role = role;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dateCreated = dateFormat.format(dateCreated);
    }

    public DeleteRequestForAdminDto(DeleteRequest request) {
        this.id = request.getId();
        this.email = request.getEmail();
        this.requestContent = request.getRequestContent();
        this.role = request.getUserRole();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dateCreated = dateFormat.format(request.getDateCreated());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String userRole) {
        this.role = userRole;
    }
}
