package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactsPage extends BasePage {

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(), 'No Contacts here!')]")
    WebElement noContactsMsg;

    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']")
    WebElement contactsCards;

    @FindBy(xpath = "//button[contains(text(), 'Edit')]")
    WebElement editContactButton;

    @FindBy(xpath = "//button[contains(text(), 'Remove')]")
    WebElement deleteContactButton;

    public boolean isContactsPageDisplayed() {
        return isElementPresent(contactsCards);
    }

    public boolean isNoContactsMessageDisplayed() {
        return isElementPresent(noContactsMsg);
    }

    public boolean isExpectedNoContactsTextPresent(String expectedMessage) {
        return isTextInElementPresent(noContactsMsg, expectedMessage);
    }

    public void deleteContact() {
        contactsCards.click();
        deleteContactButton.click();
    }
}