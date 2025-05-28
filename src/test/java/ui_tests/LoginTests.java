package ui_tests;

import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTest() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginHeaderLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeLoginForm("test@test15.com", "!Q123ABCpd");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By headerLocator = By.xpath("//*[@id='root']/div[2]/div/h2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        
        String headerText = driver.findElement(headerLocator).getText();
        Assert.assertEquals(headerText, "Add new by clicking on Add in NavBar!");
    }
}
