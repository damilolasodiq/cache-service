package com.sodiq.cache.dao;

import com.sodiq.cache.model.Currency;
import com.sodiq.cache.model.Holiday;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class CurrencyDaoTest {

    private Currency mockCurrency;


    @Before
    public void init() {
        CurrencyCacheableDao currencyDao = new CurrencyCacheableDao();
        this.mockCurrency = new Currency();
        this.mockCurrency.setId(1);
        this.mockCurrency.setCurrencyCode("USD");
        this.mockCurrency.setCurrencySymbol("$");
        this.mockCurrency.setCurrencyName("United State Dollar");
        this.mockCurrency.setLastModifiedDate(new Date());
        this.mockCurrency.setModifiedPersonNumber(1);
        this.mockCurrency.setStatus(1);
        currencyDao.save(this.mockCurrency);
    }

    @Test
    public void getCurrencyWhenCurrencyExistThenReturnCurrency() {
        CurrencyCacheableDao currencyDao = new CurrencyCacheableDao();
        Currency currency = currencyDao.get(this.mockCurrency.getId());
        Assert.assertNotNull("Currency cannot be null", currency);
        Assert.assertEquals(this.mockCurrency, currency);
    }

    @Test
    public void getCurrencyWhenCurrencyDoesNotExistThenReturnNull() {
        HolidayCacheableDao holidayDao = new HolidayCacheableDao();
        Holiday holiday = holidayDao.get(68);
        Assert.assertNull(holiday);
    }

    @Test
    public void deleteCurrencyFromCache() {
        CurrencyCacheableDao currencyDao = new CurrencyCacheableDao();
        Currency currency = currencyDao.get(this.mockCurrency.getId());
        Assert.assertNotNull("Currency cannot be null", currency);
        Assert.assertEquals(this.mockCurrency, currency);
        currencyDao.delete(currency.getId());
        currency = currencyDao.get(this.mockCurrency.getId());
        Assert.assertNull(currency);
    }
}
