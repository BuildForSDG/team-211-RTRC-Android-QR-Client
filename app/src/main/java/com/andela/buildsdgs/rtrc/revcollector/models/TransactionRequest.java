package com.andela.buildsdgs.rtrc.revcollector.models;

public class TransactionRequest {

   private String vehicle;
   private String location;

    public TransactionRequest(String vehicle, String location) {
        this.vehicle = vehicle;
        this.location = location;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
