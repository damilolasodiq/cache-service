package com.sodiq.cache;

import com.sodiq.cache.dao.CountryDaoTest;
import com.sodiq.cache.dao.CurrencyDaoTest;
import com.sodiq.cache.dao.HolidayDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HolidayDaoTest.class, CurrencyDaoTest.class, CountryDaoTest.class})
public class DaoTestSuite {
}
