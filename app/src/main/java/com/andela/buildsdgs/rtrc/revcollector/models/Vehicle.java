package com.andela.buildsdgs.rtrc.revcollector.models;

public class Vehicle {
    private int vehicleId;
    private String vehicleName;
    private String vehicleCategory;

    public Vehicle() {
    }

    public Vehicle(int vehicleId, String vehicleName, String vehicleCategory) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.vehicleCategory = vehicleCategory;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }
}
