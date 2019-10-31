package cucumberrunner.usertest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserManagementPage extends NavigationBar {

    @FindBy(how = How.XPATH, using = "//button[child::span[@jhitranslate='entity.action.edit']][ancestor::td[preceding-sibling::td[text()='admin']]]")
    private WebElement editAdminButton;

    @FindBy(how = How.XPATH, using = "//td[preceding-sibling::td[text()='admin']]")
    private WebElement emailAdminSpan;

    public EditUserPage editAdmin(WebDriver driver){
        editAdminButton.click();
        return PageFactory.initElements(driver, EditUserPage.class);
    }

    public WebElement checkUserProfiles(WebDriver driver, String login){
        List<WebElement> list;
        list = driver.findElements(By.xpath("//tr[child::td[text()='"+login+"']]"));
        if(list.size()>0)
        {
            return list.get(0);
        }
        else{
            return null;
        }
    }

    public String getAdminEmail(){
        return emailAdminSpan.getText();
    }

    public WebElement getEditAdminButton(){
        return editAdminButton;
    }

    public boolean checkRoles(WebElement row){
        if(row.findElements(By.xpath("./td[descendant::span[text()='ROLE_USER']][descendant::span[text()='ROLE_ADMIN']]")).size()>0)
        {
            return true;
        }
        else{
            return false;
        }
    }
}
