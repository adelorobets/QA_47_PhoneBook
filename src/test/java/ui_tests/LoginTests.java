package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTest() {
        User user = new User("test@test15.com", "!Q123ABCpd");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLoginHeaderLink();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);

        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.isContactsPresent());
    }

    @Test
    public void loginNegativeTest_invalidPassword() {
        User user = new User("test@test15.com", "!Q123ABCpd");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLoginHeaderLink();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.closeAlert();
        Assert.assertTrue(loginPage.isErrorMessagePresent("Login Failed with code 401"));
    }
}
