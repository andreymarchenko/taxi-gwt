package com.taxi.shared.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class OrderDto implements Serializable {

    public OrderDto() {

    }

    @JsonCreator
    public OrderDto(@JsonProperty("id") int id,
                     @JsonProperty("driver") DriverDto driver,
                     @JsonProperty("client") ClientDto client,
                     @JsonProperty("start") String start,
                     @JsonProperty("finish") String finish,
                     @JsonProperty("timestamp") String timestamp,
                     @JsonProperty("price") double price,
                     @JsonProperty("paymentType") String paymentType) {
        this.id = id;
        this.driver = driver;
        this.client = client;
        this.start = start;
        this.finish = finish;
        this.timestamp = timestamp;
        this.price = price;
        this.paymentType = paymentType;
    }

    private int id;

    private DriverDto driver;

    private ClientDto client;

    private String start;

    private String finish;

    private String timestamp;

    private double price;

    private String paymentType;

    public int getId() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public DriverDto getDriver() {
        return driver;
    }

    public void setDriver(DriverDto driver) {
        this.driver = driver;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
