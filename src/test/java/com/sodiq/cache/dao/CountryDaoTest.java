package com.sodiq.cache.dao;

import com.sodiq.cache.model.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class CountryDaoTest {

    private Country mockCountry;


    @Before
    public void init() {
        CountryCacheableDao holidayDao = new CountryCacheableDao();
        this.mockCountry = new Country();
        this.mockCountry.setId(67);
        this.mockCountry.setCountryCode("USD");
        this.mockCountry.setCountryName("United States of America");
        this.mockCountry.setLastModifiedDate(new Date());
        this.mockCountry.setModifiedPersonNumber(1);
        this.mockCountry.setStatus(1);
        holidayDao.save(this.mockCountry);
    }

    @Test
    public void getCountryWhenCountryExistThenReturnCountry() {
        CountryCacheableDao countryDao = new CountryCacheableDao();
        Country country = countryDao.get(this.mockCountry.getId());
        Assert.assertNotNull("Country cannot be null", country);
        Assert.assertEquals(this.mockCountry, country);
    }

    @Test
    public void getCountryWhenCountryDoesNotExistThenReturnNull() {
        CountryCacheableDao countryDao = new CountryCacheableDao();
        Country country = countryDao.get(68);
        Assert.assertNull(country);
    }

    @Test
    public void deleteCountryFromCache() {
        CountryCacheableDao countryDao = new CountryCacheableDao();
        Country country = countryDao.get(this.mockCountry.getId());
        Assert.assertNotNull("Country cannot be null", country);
        Assert.assertEquals(this.mockCountry, country);
        countryDao.delete(country.getId());
        country = countryDao.get(this.mockCountry.getId());
        Assert.assertNull(country);
    }
}
