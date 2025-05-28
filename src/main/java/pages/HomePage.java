package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@href='/home']")
    WebElement homeHeaderLink;

    @FindBy(xpath = "//a[@href='/about']")
    WebElement aboutHeaderLink;

    @FindBy(xpath = "//a[@href='/login']")
    WebElement loginHeaderLink;

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://telranedu.web.app/home");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void clickHomeHeaderLink() {
        homeHeaderLink.click();
    }

    public void clickAboutHeaderLink() {
        aboutHeaderLink.click();
    }

    public void clickLoginHeaderLink() {
        loginHeaderLink.click();
    }
}
