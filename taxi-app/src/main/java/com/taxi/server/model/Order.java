package com.taxi.server.model;

import com.taxi.shared.dto.PaymentTypeDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders", schema = "taxi")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "driver", referencedColumnName = "id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id", nullable = false)
    private Client client;

    @Column(name = "start", nullable = false)
    private String start;

    @Column(name = "finish", nullable = false)
    private String finish;

    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date timestamp;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name="payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentTypeDto paymentType;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
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

    public void setPaymentType(PaymentTypeDto paymentTypeDto) {
        this.paymentType = paymentTypeDto;
    }
}
