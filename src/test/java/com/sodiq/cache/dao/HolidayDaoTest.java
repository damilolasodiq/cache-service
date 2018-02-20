package com.sodiq.cache.dao;

import com.sodiq.cache.model.Holiday;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HolidayDaoTest {

    private Holiday mockHoliday;


    @Before
    public void init() {
        HolidayCacheableDao holidayDao = new HolidayCacheableDao();
        this.mockHoliday = new Holiday();
        this.mockHoliday.setId(67);
        this.mockHoliday.setHolidayCode("HOL-89887");
        this.mockHoliday.setHolidayDate(new Date());
        this.mockHoliday.setHolidayName("Christmas Holiday");
        this.mockHoliday.setModifiedPersonNumber(1);
        this.mockHoliday.setStatus(1);
        holidayDao.save(this.mockHoliday);
    }

    @Test
    public void getHolidayWhenHolidayExistThenReturnHoliday() {
        HolidayCacheableDao holidayDao = new HolidayCacheableDao();
        Holiday holiday = holidayDao.get(this.mockHoliday.getId());
        Assert.assertNotNull( holiday);
        Assert.assertEquals(this.mockHoliday, holiday);
    }

    @Test
    public void getHolidayWhenItemWasAutoRemovedThenReturnNull() throws InterruptedException {
        HolidayCacheableDao holidayDao = new HolidayCacheableDao();
        TimeUnit.SECONDS.sleep(3);
        Holiday holiday = holidayDao.get(this.mockHoliday.getId());
        Assert.assertNull( holiday);
    }

    @Test
    public void getHolidayWhenHolidayDoesNotExistThenReturnNull() {
        HolidayCacheableDao holidayDao = new HolidayCacheableDao();
        Holiday holiday = holidayDao.get(68);
        Assert.assertNull(holiday);
    }

    @Test
    public void deleteHolidayFromCache() {
        HolidayCacheableDao holidayDao = new HolidayCacheableDao();
        Holiday holiday = holidayDao.get(this.mockHoliday.getId());
        Assert.assertNotNull("Holiday cannot be null", holiday);
        Assert.assertEquals(this.mockHoliday, holiday);
        holidayDao.delete(holiday.getId());
        holiday = holidayDao.get(this.mockHoliday.getId());
        Assert.assertNull(holiday);
    }
}
