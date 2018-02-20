package com.sodiq.cache.model;


import com.sodiq.cache.CacheableTable;

import java.util.Date;

@CacheableTable(name = "REF_HOLIDAY", autoEvict = true, ttlMilliSeconds = 2000)
public class Holiday extends BaseEntity {

    private String holidayCode;

    private String holidayName;

    private Date holidayDate;

    public String getHolidayCode() {
        return holidayCode;
    }

    public void setHolidayCode(String holidayCode) {
        this.holidayCode = holidayCode;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }
}
