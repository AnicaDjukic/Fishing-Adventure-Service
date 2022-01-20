package isa.FishingAdventure.dto;

public class UserPointsDto {

    private String userCategory;

    private double points;

    private double discount;

    public UserPointsDto() {
    }

    public UserPointsDto(String userCategory, double points, double discount) {
        this.userCategory = userCategory;
        this.points = points;
        this.discount = discount;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
