package com.sodiq.cache.model;

import java.util.Date;

public class BaseEntity {

    private long id;

    private int status;

    private Date lastModifiedDate;

    private long modifiedPersonNumber;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public long getModifiedPersonNumber() {
        return modifiedPersonNumber;
    }

    public void setModifiedPersonNumber(long modifiedPersonNumber) {
        this.modifiedPersonNumber = modifiedPersonNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
