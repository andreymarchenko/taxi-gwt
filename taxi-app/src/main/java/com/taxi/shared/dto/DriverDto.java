package com.taxi.shared.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DriverDto implements Serializable {
    public DriverDto() {

    }

    @JsonCreator
    public DriverDto(@JsonProperty("id") int id,
                     @JsonProperty("name") String name,
                     @JsonProperty("password") String password,
                     @JsonProperty("description") String description,
                     @JsonProperty("car") String car,
                     @JsonProperty("status") StatusDto status) {
        this.ID = id;
        this.name = name;
        this.password = password;
        this.description = description;
        this.car = car;
        this.status = status;
    }

    private int ID;

    private String name;

    private String password;

    private String description;

    private String car;

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    private StatusDto status;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

}
