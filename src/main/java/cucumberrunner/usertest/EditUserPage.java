package cucumberrunner.usertest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditUserPage extends NavigationBar {

    @FindBy(how = How.XPATH, using = "//input[@type='email'][@name='email']")
    private WebElement emailUser;

    @FindBy(how = How.XPATH, using = "//button[@type='submit'][descendant::span[text()='Save']]")
    private WebElement saveButton;

    public UserManagementPage changeUserMail(WebDriver driver, String mail){
        emailUser.clear();
        emailUser.sendKeys(mail);
        saveButton.click();
        return PageFactory.initElements(driver, UserManagementPage.class);
    }

    public WebElement getSaveButton(){
        return saveButton;
    }
}
