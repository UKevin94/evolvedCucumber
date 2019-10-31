package cucumberrunner.usertest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends NavigationBar{

    @FindBy(how = How.XPATH, using = "//span[parent::span[@id='home-logged-message']]")
    private WebElement logInMessage;

    public WebElement getLogInMessage(){
        return logInMessage;
    }
}
