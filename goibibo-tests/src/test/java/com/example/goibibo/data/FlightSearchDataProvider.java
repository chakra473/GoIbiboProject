package com.example.goibibo.data;

import org.testng.annotations.DataProvider;

public class FlightSearchDataProvider {

    @DataProvider(name = "flightSearchData")
    public Object[][] flightSearchData() {
        return new Object[][] {
                {"New York", "London"},
                {"San Francisco", "Tokyo"},
                {"Mumbai", "Dubai"}
        };
    }
}
