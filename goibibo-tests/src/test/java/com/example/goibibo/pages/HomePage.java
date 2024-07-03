package com.example.goibibo.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private WebDriver driver;

    private By flightsTab = By.xpath("/html/body/div/div[1]/div/header/ul/li[1]/a/span[1]");
    private By hotelsTab = By.id("hotels-tab");
    private By loginButton = By.id("login-button");
    private By from1 = By.xpath("/html/body/div/div[4]/div/div/div[2]/div[1]/div/div/p");
    private By to1 = By.xpath("/html/body/div/div[4]/div/div/div[2]/div[2]/div/div/p");
    private By searchFlight = By.xpath("/html/body/div/div[4]/div/div/div[4]/span");
    private By toFlight = By.xpath("/html/body/div/div[4]/div/div/div[2]/div[2]/div/div[2]/div/input");
    private By fromSearch= By.xpath("/html/body/div/div[4]/div/div/div[2]/div[1]/div/div[2]/div/input");

    //private By toSearch= By.xpath("")
    // Add more locators as needed

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFlightsTab() {
        driver.findElement(flightsTab).click();
    }

    public void clickHotelsTab() {
        driver.findElement(hotelsTab).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


    public void login(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-submit")).click();
    }

    public String getLoggedInUserName() {
        return driver.findElement(By.id("logged-in-user")).getText();
    }

    public String getLoginErrorMessage() {
        return driver.findElement(By.id("login-error")).getText();
    }

    public void searchFlights(String from, String to) {
        driver.findElement(from1).click();
        driver.findElement(fromSearch).sendKeys(from);
        driver.findElement(fromSearch).sendKeys(Keys.ENTER);
//        Actions action1= new Actions(driver);
//        action1.sendKeys(search,from).sendKeys(Keys.ENTER);
        driver.findElement(fromSearch).click();
        driver.findElement(fromSearch).sendKeys(Keys.ENTER);
        //driver.findElement(to1).click();
        driver.findElement(toFlight).sendKeys(to);
        driver.findElement(toFlight).click();
        driver.findElement(toFlight).sendKeys(Keys.ENTER);
        driver.findElement(searchFlight).click();
    }

    public void searchHotels(String location, String checkIn, String checkOut) {
        driver.findElement(By.id("location")).sendKeys(location);
        driver.findElement(By.id("checkin")).sendKeys(checkIn);
        driver.findElement(By.id("checkout")).sendKeys(checkOut);
        driver.findElement(By.id("search-hotels")).click();
    }

    public void selectFlight() {
        driver.findElement(By.cssSelector(".select-flight")).click();
    }

    public void proceedToCheckout() {
        driver.findElement(By.id("proceed-checkout")).click();
    }

    public void selectHotel() {
        driver.findElement(By.cssSelector(".select-hotel")).click();
    }

    public void updateProfile(String name, String email, String phone) {
        driver.findElement(By.id("profile-name")).clear();
        driver.findElement(By.id("profile-name")).sendKeys(name);
        driver.findElement(By.id("profile-email")).clear();
        driver.findElement(By.id("profile-email")).sendKeys(email);
        driver.findElement(By.id("profile-phone")).clear();
        driver.findElement(By.id("profile-phone")).sendKeys(phone);
        driver.findElement(By.id("profile-submit")).click();
    }

    public String getProfileName() {
        return driver.findElement(By.id("profile-name")).getAttribute("value");
    }

    public String getProfileEmail() {
        return driver.findElement(By.id("profile-email")).getAttribute("value");
    }

    public String getProfilePhone() {
        return driver.findElement(By.id("profile-phone")).getAttribute("value");
    }

    public void bookFlight(String from, String to) {
        searchFlights(from, to);
        selectFlight();
        proceedToCheckout();
    }

    public void cancelFlight() {
        driver.findElement(By.id("cancel-flight")).click();
    }

    public String getCancellationStatus() {
        return driver.findElement(By.id("cancellation-status")).getText();
    }

    public void bookHotel(String location, String checkIn, String checkOut) {
        searchHotels(location, checkIn, checkOut);
        selectHotel();
        proceedToCheckout();
    }

    public void cancelHotel() {
        driver.findElement(By.id("cancel-hotel")).click();
    }

    public void applyFlightFilters(String... filters) {
        for (String filter : filters) {
            driver.findElement(By.id("filter-" + filter)).click();
        }
    }

    public int getFilteredFlightsCount() {
        return driver.findElements(By.cssSelector(".filtered-flight")).size();
    }

    public void applyHotelFilters(String... filters) {
        for (String filter : filters) {
            driver.findElement(By.id("filter-" + filter)).click();
        }
    }

    public int getFilteredHotelsCount() {
        return driver.findElements(By.cssSelector(".filtered-hotel")).size();
    }

    public void sortFlightsBy(String criteria) {
        driver.findElement(By.id("sort-flights-" + criteria)).click();
    }

    public boolean verifyFlightsSortedBy(String criteria) {
        // Implement sorting verification logic
        return true;
    }

    public void sortHotelsBy(String criteria) {
        driver.findElement(By.id("sort-hotels-" + criteria)).click();
    }

    public boolean verifyHotelsSortedBy(String criteria) {
        // Implement sorting verification logic
        return true;
    }
}
