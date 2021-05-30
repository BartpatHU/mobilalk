package com.example.beadando.models;

import java.util.Date;

public class CreditProfile {

    private Date creditProfileDate;
    private int creditRiskRating;
    private int creditScore;

    public Date getCreditProfileDate() {
        return creditProfileDate;
    }

    public void setCreditProfileDate(Date creditProfileDate) {
        this.creditProfileDate = creditProfileDate;
    }

    public int getCreditRiskRating() {
        return creditRiskRating;
    }

    public void setCreditRiskRating(int creditRiskRating) {
        this.creditRiskRating = creditRiskRating;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
