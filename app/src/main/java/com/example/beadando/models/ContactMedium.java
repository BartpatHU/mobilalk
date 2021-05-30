package com.example.beadando.models;

import java.util.Date;

public class ContactMedium {

    private String mediumType;
    private boolean preferred;
    private Date validFor;

    public String getMediumType() {
        return mediumType;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public Date getValidFor() {
        return validFor;
    }

    public void setValidFor(Date validFor) {
        this.validFor = validFor;
    }
}
