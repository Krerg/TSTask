package com.tsystems.model;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    public static final boolean DELIVERED_STATUS = true;
    public static final boolean NOT_DELIVERED_STATUS = false;


    @Id
    private int id;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "WEIGHT")
    private int weight;

    @Column(name = "GPS")
    private String gps;

    @Column(name = "ITEM_STATUS")
    private boolean itemStatus;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public boolean isItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return itemName + " Deliver Status:" + itemStatus;
    }

}