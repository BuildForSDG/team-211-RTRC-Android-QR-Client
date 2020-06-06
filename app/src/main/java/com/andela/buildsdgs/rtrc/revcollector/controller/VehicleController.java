package com.andela.buildsdgs.rtrc.revcollector.controller;


import com.andela.buildsdgs.rtrc.revcollector.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleController {
    public VehicleController() {
    }

    public List<Vehicle> getVehicleList() {
        List<Vehicle> vehicleList = new ArrayList<>();
        // public Vehicle(int vehicleId, String vehicleName, String vehicleCategory) {
        vehicleList.add(new Vehicle(1, "Nissan Rogue", "4x4"));
        vehicleList.add(new Vehicle(2, "Honda CRV", "4x4"));
        vehicleList.add(new Vehicle(1, "Nissan Rogue", "4x4"));
        vehicleList.add(new Vehicle(2, "Honda CRV", "4x4"));
        vehicleList.add(new Vehicle(3, "Toyota Corolla", "Saloon"));
        vehicleList.add(new Vehicle(3, "Toyota Corolla", "Saloon"));
        return vehicleList;
    }
}
