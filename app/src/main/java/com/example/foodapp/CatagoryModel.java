package com.example.foodapp;

public class CatagoryModel {

    String catagoryId,catagoryName;

    public CatagoryModel(String catagoryId, String catagoryName) {
        this.catagoryId = catagoryId;
        this.catagoryName = catagoryName;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }
}


