package pages;

import dto.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='root']/div[2]/div/form")
    WebElement loginForm;

    @FindBy(name = "email")
    WebElement inputEmail;

    @FindBy(name = "password")
    WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//button[@name='registration']")
    WebElement registrationButton;

    @FindBy(xpath = "//*[contains(text(), 'Registration failed')]")
    WebElement errorMsgRegistration;

    @FindBy(xpath = "//*[contains(text(), 'Login Failed')]")
    WebElement errorMsgLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginFormDisplayed() {
        return isElementPresent(loginForm);
    }

    public void typeLoginForm(User user) {
        fillCredentials(user);
        submitButton.click();
    }

    public void typeRegistrationForm(User user) {
        fillCredentials(user);
        registrationButton.click();
    }

    private void fillCredentials(User user) {
        inputEmail.clear();
        inputEmail.sendKeys(user.getUsername());
        inputPassword.clear();
        inputPassword.sendKeys(user.getPassword());
    }

    public void closeAlert() {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert text: " + alert.getText());
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert present: " + e.getMessage());
        }
    }

    public boolean errorMessageLoginFailed(String expectedMessage) {
        return isTextInElementPresent(errorMsgLogin, expectedMessage);
    }

    public boolean errorMessageRegistrationFailed(String expectedMessage) {
        return isTextInElementPresent(errorMsgRegistration, expectedMessage);
    }
}