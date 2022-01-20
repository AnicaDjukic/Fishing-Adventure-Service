package isa.FishingAdventure.dto;

import isa.FishingAdventure.model.UserCategory;

import java.util.List;

public class LoyaltyProgramDto {

    private double clientPointsPercentage;

    private double advertiserPointsPercentage;

    private double clientSilverPoints;

    private double clientGoldPoints;

    private double advertiserSilverPoints;

    private double advertiserGoldPoints;

    private double advertiserBaseCut;

    public LoyaltyProgramDto() {
    }

    public LoyaltyProgramDto(double clientPointsPercentage, double advertiserPointsPercentage,
                             double clientSilverPoints, double clientGoldPoints,
                             double advertiserSilverPoints, double advertiserGoldPoints,
                             double advertiserBaseCut) {
        this.clientPointsPercentage = clientPointsPercentage;
        this.advertiserPointsPercentage = advertiserPointsPercentage;
        this.clientSilverPoints = clientSilverPoints;
        this.clientGoldPoints = clientGoldPoints;
        this.advertiserSilverPoints = advertiserSilverPoints;
        this.advertiserGoldPoints = advertiserGoldPoints;
        this.advertiserBaseCut = advertiserBaseCut;
    }

    public LoyaltyProgramDto(List<UserCategory> categories) {
        for (UserCategory category : categories) {
            switch (category.getName()) {
                case "SILVER_CLIENT":
                    this.clientSilverPoints = category.getPoints();
                    break;
                case "GOLD_CLIENT":
                    this.clientGoldPoints = category.getPoints();
                    break;
                case "REGULAR_ADVERTISER":
                    this.advertiserBaseCut = category.getPercentage();
                    break;
                case "SILVER_ADVERTISER":
                    this.advertiserSilverPoints = category.getPoints();
                    break;
                case "GOLD_ADVERTISER":
                    this.advertiserGoldPoints = category.getPoints();
                    break;
            }
        }
    }


    public double getClientPointsPercentage() {
        return clientPointsPercentage;
    }

    public void setClientPointsPercentage(double clientPointsPercentage) {
        this.clientPointsPercentage = clientPointsPercentage;
    }

    public double getAdvertiserPointsPercentage() {
        return advertiserPointsPercentage;
    }

    public void setAdvertiserPointsPercentage(double advertiserPointsPercentage) {
        this.advertiserPointsPercentage = advertiserPointsPercentage;
    }

    public double getClientSilverPoints() {
        return clientSilverPoints;
    }

    public void setClientSilverPoints(double clientSilverPoints) {
        this.clientSilverPoints = clientSilverPoints;
    }

    public double getClientGoldPoints() {
        return clientGoldPoints;
    }

    public void setClientGoldPoints(double clientGoldPoints) {
        this.clientGoldPoints = clientGoldPoints;
    }

    public double getAdvertiserSilverPoints() {
        return advertiserSilverPoints;
    }

    public void setAdvertiserSilverPoints(double advertiserSilverPoints) {
        this.advertiserSilverPoints = advertiserSilverPoints;
    }

    public double getAdvertiserGoldPoints() {
        return advertiserGoldPoints;
    }

    public void setAdvertiserGoldPoints(double advertiserGoldPoints) {
        this.advertiserGoldPoints = advertiserGoldPoints;
    }

    public double getAdvertiserBaseCut() {
        return advertiserBaseCut;
    }

    public void setAdvertiserBaseCut(double advertiserBaseCut) {
        this.advertiserBaseCut = advertiserBaseCut;
    }
}
