package com.andela.buildsdgs.rtrc.revcollector.models;

import com.google.gson.annotations.SerializedName;

public class TollResults {
    private Collector[] collectors;
    private String address;
    @SerializedName("updated_at")
    private String updatedAt;
    private String name;
    private String active;
    @SerializedName("created_at")
    private String createdAt;
    private String id;

    public Collector[] getCollectors() {
        return collectors;
    }

    public void setCollectors(Collector[] collectors) {
        this.collectors = collectors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
