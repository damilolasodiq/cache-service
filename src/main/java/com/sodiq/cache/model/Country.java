package com.sodiq.cache.model;

import com.sodiq.cache.CacheableTable;

@CacheableTable(name = "REF_COUNTRY")
public class Country extends BaseEntity {

    private String countryCode;

    private String countryName;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
