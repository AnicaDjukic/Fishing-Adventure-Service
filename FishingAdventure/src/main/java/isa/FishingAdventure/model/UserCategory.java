package isa.FishingAdventure.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "points", nullable = false)
    private double points;

    @Column(name = "category", nullable = false)
    private UserCategoryName category;

    @Column(name = "percentage", nullable = false)
    private double percentage;

    public UserCategory(Integer id, double points, UserCategoryName category, double percentage) {
        this.id = id;
        this.points = points;
        this.category = category;
        this.percentage = percentage;
    }

    public UserCategory() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPoints() {
        return this.points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public UserCategoryName getCategory() {
        return this.category;
    }

    public void setCategory(UserCategoryName category) {
        this.category = category;
    }

    public double getPercentage() {
        return this.percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

}