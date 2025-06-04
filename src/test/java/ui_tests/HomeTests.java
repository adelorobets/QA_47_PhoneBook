package ui_tests;

import manager.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTests extends ApplicationManager {

    @Test
    public void homeHeaderLinkPositive(){
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.clickHomeHeaderLink();
    }
}
