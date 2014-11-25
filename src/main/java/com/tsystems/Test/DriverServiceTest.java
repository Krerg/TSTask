package com.tsystems.Test;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.tsystems.model.Driver;
import com.tsystems.services.DriverService;

/**
 * Created by User on 27.10.2014.
 */

public class DriverServiceTest {

    @Autowired
    private DriverService ds;

    @Before
    public void init() {
        ds = DriverService.getService();
    }

    @Test
    public void getInfo() {

        Assert.assertEquals(ds.getInformationForDriver(2434),null);
    }


    @Test
    public void changeStatus() {
        Assert.assertEquals(ds.changeStatus(12345678, Driver.DRIVING_STATUS),"Can not change status");
    }

    @Test
    public void deliverItem() {
        Assert.assertEquals(ds.deliverItem(1,12345678,4),"No such order/driver/item in DB");
    }

}
