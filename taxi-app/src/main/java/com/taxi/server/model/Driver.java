package com.taxi.server.model;

import com.taxi.shared.dto.StatusDto;

import javax.persistence.*;

@Entity
@Table(name = "drivers", schema = "taxi")
public class Driver {
    @Column
    private String car;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusDto status;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", updatable = false, nullable = false)
    private int ID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "description")
    private String description;

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

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto statusDto) {
        this.status = statusDto;
    }
}
