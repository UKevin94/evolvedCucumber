package cucumberrunner.usertest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationBar {
    @FindBy(how = How.XPATH, using = "//a[@id='account-menu']")
    private WebElement accountButton;

    @FindBy(how = How.XPATH, using = "//a[@id='login']")
    private WebElement signInOpenLink;

    @FindBy(how = How.XPATH, using = "//input[@id='username']")
    private WebElement usernameInput;

    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[@type='submit'][@jhitranslate='login.form.button']")
    private WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//a[@id='admin-menu']")
    private WebElement adminButton;

    @FindBy(how = How.XPATH, using = "//a[@class='dropdown-item'][contains(@href,'#/admin/user-management')]")
    private WebElement userManagementButton;

    public WebElement getSignInButton() {
        return signInButton;
    }

    public void signInWithAdmin(WebDriverWait wait){
        accountButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(signInOpenLink));
        signInOpenLink.click();
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        usernameInput.clear();
        usernameInput.sendKeys("admin");
        passwordInput.clear();
        passwordInput.sendKeys("admin");
        signInButton.click();
    }

    public UserManagementPage goToUsermanagementPage(WebDriver driver, WebDriverWait wait){
        adminButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(userManagementButton));
        userManagementButton.click();
        return PageFactory.initElements(driver, UserManagementPage.class);
    }
}
