package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class RegistrationTest extends ApplicationManager {

    @Test
    public void RegistrationPositiveTest() {
        User user = new User("test@test15.com", "!Q123ABCpd");
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginHeaderLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeRegistrationForm(user);
    }
}