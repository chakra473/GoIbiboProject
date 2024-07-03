package com.example.goibibo.tests;

import com.example.goibibo.base.BaseTest;
import com.example.goibibo.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoibiboTests extends BaseTest {

    @Test
    public void testFlightsTabNavigation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFlightsTab();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("flights"), "Navigation to Flights tab failed");
    }

    @DataProvider(name = "flightSearchData")
    public Object[][] flightSearchData() {
        return new Object[][] {
                {"New York", "London"},
                {"San Francisco", "Tokyo"},
                {"Mumbai", "Dubai"}
        };
    }

    @Test(dataProvider = "flightSearchData")
    public void testFlightSearch(String from, String to) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.searchFlights(from, to);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("flights"), "Flight search failed");
    }

    @Test(enabled = false)
    public void testHotelBookingNavigation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickHotelsTab();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("hotels"), "Navigation to Hotels tab failed");
    }

    @DataProvider(name = "hotelSearchData")
    public Object[][] hotelSearchData() {
        return new Object[][] {
                {"New York", "2023-08-01", "2023-08-10"},
                {"London", "2023-09-15", "2023-09-25"},
                {"Tokyo", "2023-10-05", "2023-10-15"}
        };
    }

    @Test(dataProvider = "hotelSearchData",enabled = false)
    public void testHotelSearch(String location, String checkIn, String checkOut) {
        HomePage homePage = new HomePage(driver);
        homePage.searchHotels(location, checkIn, checkOut);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("hotels-search"), "Hotel search failed");
    }

    @Test(enabled = false)
    public void testUserLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        homePage.login("testuser", "password");

        String loggedInUser = homePage.getLoggedInUserName();
        Assert.assertEquals(loggedInUser, "testuser", "Login failed");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"invaliduser", "password"},
                {"testuser", "wrongpassword"},
                {"", ""}
        };
    }

    @Test(dataProvider = "invalidLoginData",enabled = false)
    public void testInvalidLogin(String username, String password) {
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        homePage.login(username, password);

        String errorMessage = homePage.getLoginErrorMessage();
        Assert.assertTrue(errorMessage.contains("invalid"), "Error message not displayed for invalid login");
    }

    @Test(enabled = false)
    public void testFlightBookingProcess() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFlightsTab();
        homePage.searchFlights("New York", "London");
        homePage.selectFlight();
        homePage.proceedToCheckout();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout"), "Flight booking process failed");
    }

    @Test(enabled = false)
    public void testHotelBookingProcess() {
        HomePage homePage = new HomePage(driver);
        homePage.clickHotelsTab();
        homePage.searchHotels("New York", "2023-08-01", "2023-08-10");
        homePage.selectHotel();
        homePage.proceedToCheckout();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout"), "Hotel booking process failed");
    }

    @Test(enabled = false)
    public void testProfileUpdate() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        homePage.login("testuser", "password");
        homePage.updateProfile("newname", "newemail@example.com", "9999999999");

        String updatedName = homePage.getProfileName();
        String updatedEmail = homePage.getProfileEmail();
        String updatedPhone = homePage.getProfilePhone();
        Assert.assertEquals(updatedName, "newname", "Profile name update failed");
        Assert.assertEquals(updatedEmail, "newemail@example.com", "Profile email update failed");
        Assert.assertEquals(updatedPhone, "9999999999", "Profile phone update failed");
    }

    @Test(enabled = false)
    public void testFlightCancellation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        homePage.login("testuser", "password");
        homePage.bookFlight("New York", "London");
        homePage.cancelFlight();

        String cancellationStatus = homePage.getCancellationStatus();
        Assert.assertTrue(cancellationStatus.contains("cancelled"), "Flight cancellation failed");
    }

    @Test(enabled = false)
    public void testHotelCancellation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        homePage.login("testuser", "password");
        homePage.bookHotel("New York", "2023-08-01", "2023-08-10");
        homePage.cancelHotel();

        String cancellationStatus = homePage.getCancellationStatus();
        Assert.assertTrue(cancellationStatus.contains("cancelled"), "Hotel cancellation failed");
    }

    @Test(enabled = false)
    public void testFlightFilterOptions() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFlightsTab();
        homePage.searchFlights("New York", "London");
        homePage.applyFlightFilters("Non-stop", "Morning");

        int filteredFlightsCount = homePage.getFilteredFlightsCount();
        Assert.assertTrue(filteredFlightsCount > 0, "Flight filter options failed");
    }

    @Test(enabled = false)
    public void testHotelFilterOptions() {
        HomePage homePage = new HomePage(driver);
        homePage.clickHotelsTab();
        homePage.searchHotels("New York", "2023-08-01", "2023-08-10");
        homePage.applyHotelFilters("5-star", "Free WiFi");

        int filteredHotelsCount = homePage.getFilteredHotelsCount();
        Assert.assertTrue(filteredHotelsCount > 0, "Hotel filter options failed");
    }

    @Test(enabled = false)
    public void testFlightSortingOptions() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFlightsTab();
        homePage.searchFlights("New York", "London");
        homePage.sortFlightsBy("Price");

        boolean isSorted = homePage.verifyFlightsSortedBy("Price");
        Assert.assertTrue(isSorted, "Flight sorting options failed");
    }

    @Test(enabled = false)
    public void testHotelSortingOptions() {
        HomePage homePage = new HomePage(driver);
        homePage.clickHotelsTab();
        homePage.searchHotels("New York", "2023-08-01", "2023-08-10");
        homePage.sortHotelsBy("Rating");

        boolean isSorted = homePage.verifyHotelsSortedBy("Rating");
        Assert.assertTrue(isSorted, "Hotel sorting options failed");
    }
}
