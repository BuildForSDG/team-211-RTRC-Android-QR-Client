package com.andela.buildsdgs.rtrc.revcollector.models;

public class Location {
    private Collector[] collectors;
    private String address;
    private String updated_at;
    private String name;
    private String active;
    private String created_at;
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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
