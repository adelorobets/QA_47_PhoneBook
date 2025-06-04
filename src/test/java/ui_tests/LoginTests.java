package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {

    private LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLoginHeaderLink();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void testLoginFormDisplayed() {
        Assert.assertTrue(loginPage.isLoginFormDisplayed());
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.typeLoginForm(new User("test@test15.com", "!Q123ABCpd"));
        Assert.assertTrue(new ContactsPage(getDriver()).isContactsPageDisplayed());
    }

    @Test
    public void testSuccessfulLogin_uppercaseEmail() {
        loginPage.typeLoginForm(new User("Test@test15.com", "!Q123ABCpd"));
        Assert.assertTrue(new ContactsPage(getDriver()).isContactsPageDisplayed());
    }

    @Test
    public void testSuccessfulLogin_withTrimmedEmailInput() {
        loginPage.typeLoginForm(new User("test@test15.com ", "!Q123ABCpd"));
        Assert.assertTrue(new ContactsPage(getDriver()).isContactsPageDisplayed());
    }

    //Negative Tests
    private void loginAndAssertFailure(User user) {
        loginPage.typeLoginForm(user);
        loginPage.closeAlert();
        Assert.assertTrue(loginPage.errorMessageLoginFailed("Login Failed"));
    }

    @Test
    public void loginNegativeTest_unregisteredUser() {
        loginAndAssertFailure(new User("unregistered@test.com", "!W123ABCpd"));
    }

    @Test
    public void loginNegativeTest_wrongPassword() {
        loginAndAssertFailure(new User("test@test15.com", "!H123ABCpd"));
    }

    @Test
    public void loginNegativeTest_emptyUsername() {
        loginAndAssertFailure(new User("", "!Q123ABCpd"));
    }

    @Test
    public void loginNegativeTest_emptyPassword() {
        loginAndAssertFailure(new User("test@test15.com", ""));
    }

    @Test
    public void loginNegativeTest_invalidUsernameFormat() {
        loginAndAssertFailure(new User("testtest15.com", "!Q123ABCpd"));
    }

    @Test
    public void loginNegativeTest_invalidUsernameDomain() {
        loginAndAssertFailure(new User("test@.com", "!Q123ABCpd"));
    }

    @Test
    public void loginNegativeTest_invalidUsername_withSpace() {
        loginAndAssertFailure(new User("test @test15.com", "!Q123ABCpd"));
    }

    @Test
    public void loginNegativeTest_invalidPasswordShort() {
        loginAndAssertFailure(new User("test@test15.com", "!Q1p"));
    }

    @Test
    public void loginNegativeTest_invalidPasswordLong() {
        loginAndAssertFailure(new User("test@test15.com", "A1!b2c3d4e5f6g7h8i9j0K"));
    }

    @Test
    public void loginNegativeTest_invalidPasswordNoDigit() {
        loginAndAssertFailure(new User("test@test15.com", "!QqweABCpd"));
    }

    @Test
    public void loginNegativeTest_invalidPasswordNoSymbol() {
        loginAndAssertFailure(new User("test@test15.com", "aQ123ABCpd"));
    }
}
