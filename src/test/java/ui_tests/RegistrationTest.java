package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.ThreadLocalRandom;

public class RegistrationTest extends ApplicationManager {

    private LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLoginHeaderLink();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void testSuccessfulRegistration() {
        int randomNum = ThreadLocalRandom.current().nextInt(100, 9999);
        String uniqueEmail = "test" + randomNum + "@test.com";
        User user = new User(uniqueEmail, "!Q123ABCpd");

        loginPage.typeRegistrationForm(user);
        Assert.assertTrue(new ContactsPage(getDriver())
                .isExpectedNoContactsTextPresent("No Contacts here!"));
    }

    //Negative Tests
    private void registrationAndAssertFailure(User user) {
        loginPage.typeRegistrationForm(user);
        loginPage.closeAlert();
        Assert.assertTrue(loginPage.errorMessageRegistrationFailed("Registration failed"));
    }

    @Test
    public void testNegative_UserAlreadyExist() {
        registrationAndAssertFailure(new User("test@test15.com", "!Q123ABCpd"));
    }

    @Test
    public void testNegative_emptyUsername() {
        registrationAndAssertFailure(new User("", "!Q123ABCpd"));
    }

    @Test
    public void testNegative_emptyPassword() {
        registrationAndAssertFailure(new User("test@test15.com", ""));
    }

    @Test
    public void testNegative_invalidUsernameFormat() {
        registrationAndAssertFailure(new User("testtest15.com", "!Q123ABCpd"));
    }

    @Test
    public void testNegative_invalidUsernameDomain() {
        registrationAndAssertFailure(new User("test@.com", "!Q123ABCpd"));
    }

    @Test
    public void testNegative_invalidUsername_withSpace() {
        registrationAndAssertFailure(new User("test @test.com", "!Q123ABCpd"));
    }

    @Test
    public void testNegative_invalidPasswordShort() {
        registrationAndAssertFailure(new User("test@test15.com", "A1!b"));
    }

    @Test
    public void testNegative_invalidPasswordLong() {
        registrationAndAssertFailure(new User("test@test15.com", "A1!b2c3d4e5f6g7h8i9j0K1L2M3N4O5"));
    }

    @Test
    public void testNegative_invalidPasswordNoDigit() {
        registrationAndAssertFailure(new User("test@test15.com", "!QWERTYabc"));
    }

    @Test
    public void testNegative_invalidPasswordNoSymbol() {
        registrationAndAssertFailure(new User("test@test15.com", "Abc123456"));
    }
}
