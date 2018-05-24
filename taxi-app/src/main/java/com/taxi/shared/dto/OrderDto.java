package com.taxi.shared.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

public class OrderDto implements Serializable {

    public OrderDto() {

    }

    @JsonCreator
    public OrderDto(@JsonProperty("id") int id,
                     @JsonProperty("driver") DriverDto driver,
                     @JsonProperty("client") ClientDto client,
                     @JsonProperty("start") String start,
                     @JsonProperty("finish") String finish,
                     @JsonProperty("timestamp") Date timestamp,
                     @JsonProperty("price") double price,
                     @JsonProperty("paymentType") PaymentTypeDto paymentType) {
        this.ID = id;
        this.driver = driver;
        this.client = client;
        this.start = start;
        this.finish = finish;
        this.timestamp = timestamp;
        this.price = price;
        this.paymentType = paymentType;
    }

    private int ID;

    private DriverDto driver;

    private ClientDto client;

    private String start;

    private String finish;

    private Date timestamp;

    private double price;

    private PaymentTypeDto paymentType;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PaymentTypeDto getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeDto paymentType) {
        this.paymentType = paymentType;
    }
}
