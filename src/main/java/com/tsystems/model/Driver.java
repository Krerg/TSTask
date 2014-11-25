package com.tsystems.model;

import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="driver")
public class Driver {

    public static final String AT_WORK_STATUS = "At work";
    public static final String DRIVING_STATUS = "Driving";
    public static final String NOT_WORKING = "Not working";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="DRIVER_LICENSE")
    private int driverLicense;

    @Column(name="SURNAME")
    private String surname;

    @Column(name="NAME")
    private String name;

    @Column(name="PATRONYMIC")
    private String patronymic;

    @Column(name="STATUS")
    private String status;

    @Column(name="IN_WAGON")
    private boolean inWagon;

    @ManyToOne
    @JoinColumn(name="WAGON_ID")
    private Wagon wagon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(int driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isInWagon() {
        return inWagon;
    }

    public void setInWagon(boolean inWagon) {
        this.inWagon = inWagon;
    }

    public Driver()
    {

    }

    public Wagon getWagon() {
        return wagon;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    @Override
    public String toString()
    {
        return driverLicense+" Name:"+surname+" "+name+" Status:"+status;
    }

    public String fullInfo()
    {
        String wagonNumber=" ";
        String co_drivers="";
        String orderInfo="";
        String items="";
        if(wagon!=null)
        {
            wagonNumber+=wagon.getRegistrationNumber();
            if(wagon.getDriverList().size()!=0)
            {
                for(Driver d:wagon.getDriverList())
                {
                    if(d.driverLicense!=this.driverLicense) {
                        co_drivers += " Co-Driver:";
                        co_drivers += d.getDriverLicense();
                        co_drivers += ";";
                    }
                }
            }
            if(wagon.getOrder()!=null)
            {
                orderInfo+=" Order number:";
                orderInfo+=wagon.getOrder().getNumber();
            }
            if(wagon.getOrder().getItemList().size()!=0)
            {
                for(Item i:wagon.getOrder().getItemList())
                {
                    items+=" Item:";
                    items+=i.toString();
                    items+=";";
                }
            }
        }

        return driverLicense+co_drivers+wagonNumber+orderInfo+items;
    }
}