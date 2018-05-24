package com.taxi.server.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "taxi")
public class OrdersEntity {
    private Integer id;
    private String start;
    private String finish;
    private Double price;
    private Date timestamp;
    private String paymentType;
    private DriversEntity driversByDriver;
    private ClientsEntity clientsByClient;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start", nullable = false, length = 45)
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Basic
    @Column(name = "finish", nullable = false, length = 45)
    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    @Basic
    @Column(name = "price", nullable = false, length = 45)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "timestamp", nullable = false)
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "payment_type", nullable = false, length = 45)
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(start, that.start) &&
                Objects.equals(finish, that.finish) &&
                Objects.equals(price, that.price) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(paymentType, that.paymentType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, start, finish, price, timestamp, paymentType);
    }

    @ManyToOne
    @JoinColumn(name = "driver", referencedColumnName = "id", nullable = false)
    public DriversEntity getDriversByDriver() {
        return driversByDriver;
    }

    public void setDriversByDriver(DriversEntity driversByDriver) {
        this.driversByDriver = driversByDriver;
    }

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id", nullable = false)
    public ClientsEntity getClientsByClient() {
        return clientsByClient;
    }

    public void setClientsByClient(ClientsEntity clientsByClient) {
        this.clientsByClient = clientsByClient;
    }
}
