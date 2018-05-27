package com.taxi.shared.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ClientDto implements Serializable {

    public ClientDto() {

    }

    @JsonCreator
    public ClientDto(@JsonProperty("id") int id,
                     @JsonProperty("name") String name,
                     @JsonProperty("password") String password,
                     @JsonProperty("description") String description) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.description = description;
    }

    private int id;

    private String name;

    private String password;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
