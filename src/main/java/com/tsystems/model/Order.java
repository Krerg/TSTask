package com.tsystems.model;

import java.lang.String;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="order_")
public class Order {

    public static final String CONFIRMED_STATUS = "Confirmed";
    public static final String SHIPPED_STATUS = "Shipped";
    public static final String DELIVERED_STATUS = "Delivered";
    public static final String CREATED_STATUS = "Created";
    public static final String CLOSED_STATUS = "Closed";

    @Id
    private int id;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    @OneToMany(mappedBy = "order")
    private List<Item> itemList;

    @OneToOne(mappedBy = "order")
    private Wagon wagon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public String getSHIPPED_STATUS() {
        return SHIPPED_STATUS;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString()
    {
        String items="";
        if(itemList!=null)
        for(Item e:itemList)
        {
            items+=e.toString();
            items+=" ";
        }
        if(wagon!=null) {
            if(wagon.getDriverList().size()==0) {
                String wagonInfo = wagon.toString();
                return number + " Status:" + orderStatus + " Wagon:" + wagonInfo+" Items:"+items;
            } else {
                String driversInfo = "";
                for(Driver d:wagon.getDriverList()) {
                    driversInfo += d.toString()+"; ";
                }
                String wagonInfo = wagon.toString();
                return number + " Status:" + orderStatus + " Wagon:" + wagonInfo + " Drivers:"+driversInfo+" Items:"+items ;
            }
            } else {

            return number + " Status:" + orderStatus+" Items:"+items;
        }
        }

}