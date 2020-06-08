package com.andela.buildsdgs.rtrc.revcollector.models;

import com.google.gson.annotations.SerializedName;

public class Vehicle {
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("registration_number")
    private String registrationNumber;
    @SerializedName("chassis_number")
    private String chassisNumber;
    @SerializedName("qr_code")
    private String qrCode;
    @SerializedName("created_at")
    private String createdAt;
    private String model;
    private String id;
    @SerializedName("category")
    private VehicleCategory category;
    private User user;
    @SerializedName("license_number")
    private String licenseNumber;
    @SerializedName("sufficient_funds")
    private boolean sufficientFunds;


    public boolean isSufficientFunds() {
        return sufficientFunds;
    }

    public void setSufficientFunds(boolean sufficientFunds) {
        this.sufficientFunds = sufficientFunds;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
