package pages;

import dto.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(name = "email")
    WebElement inputEmail;
    @FindBy(name = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//button[@name='registration']")
    WebElement registrationButton;
    @FindBy(xpath = "//div[@class='login_login__3EHKB']/div")
    WebElement wrongEmailOrPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void typeLoginForm(User user) {
        inputEmail.clear();
        inputEmail.sendKeys(user.getUsername());
        inputPassword.clear();
        inputPassword.sendKeys(user.getPassword());
        submitButton.click();
    }

    public void typeRegistrationForm(User user) {
        inputEmail.clear();
        inputEmail.sendKeys(user.getUsername());
        inputPassword.clear();
        inputPassword.sendKeys(user.getPassword());
        registrationButton.click();
    }

    public void closeAlert() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept();
    }

    public boolean isErrorMessagePresent(String message) {
        return isTextInElementPresent(wrongEmailOrPassword, message);
    }
}
