package isa.FishingAdventure.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DeleteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    @Column(nullable = false)
    private Integer version;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "request_content", nullable = false, length = 1024)
    private String requestContent;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "reviewed", nullable = false)
    private boolean reviewed;

    @Column(name = "userRole", nullable = false)
    private String userRole;

    public DeleteRequest(String email, String requestContent, Date dateCreated, String userRole) {
        this.email = email;
        this.requestContent = requestContent;
        this.dateCreated = dateCreated;
        this.reviewed = false;
        this.userRole = userRole;
    }

    public DeleteRequest() {
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
