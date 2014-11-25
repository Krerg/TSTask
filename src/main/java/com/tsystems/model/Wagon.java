package com.tsystems.model;

import java.lang.String;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name = "wagon")
public class Wagon {
    @Id
    private int id;

    @Column(name="REGISTRATION_NUMBER")
    private String registrationNumber;

    @Column(name="CAPACITY_CLASS")
    private int capacityClass;

    @OneToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @Column(name="REQUIRED_NUMBER_OF_DRIVERS")
    private int requiredNumberOfDrivers;

    @OneToMany(mappedBy = "wagon")
    private List<Driver> driverList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacityClass() {
        return capacityClass;
    }

    public void setCapacityClass(int capacityClass) {
        this.capacityClass = capacityClass;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public int getRequiredNumberOfDrivers() {
        return requiredNumberOfDrivers;
    }

    public void setRequiredNumberOfDrivers(int requiredNumberOfDrivers) {
        this.requiredNumberOfDrivers = requiredNumberOfDrivers;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString()
    {
        return registrationNumber+" Capacity:"+capacityClass+" Number of drivers:"+requiredNumberOfDrivers;
    }
}