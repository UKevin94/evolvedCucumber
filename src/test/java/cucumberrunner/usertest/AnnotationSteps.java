package cucumberrunner.usertest;

import cucumber.api.java.fr.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AnnotationSteps {

    WebDriver driver;
    WebDriverWait wait;
    WebElement rowUser;
    HomePage homePage;
    UserManagementPage userManagementPage;
    EditUserPage editUserPage;
    static final String NEW_MAIL = "concombre@concombre.com";

    @Étantdonnéque("je suis sur la page de jacksonviews")
    public void je_suis_sur_la_page_de_jacksonviews() throws Exception {
        String browser = System.getProperty("web.browser");
        switch(browser){
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver = new ChromeDriver();
                break;
            default:
                throw new Exception("No supported browser has been specified");
        }
        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(System.getProperty("page.url"));
    }

    @Étantdonnéque("je suis connecté en tant qu'admin")
    public void je_suis_connecté_en_tant_qu_admin() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.signInWithAdmin(wait);
    }

    @Soit("je vais sur la page User Management")
    public void je_vais_sur_la_page_User_Management() {
        //wait.until(ExpectedConditions.stalenessOf(homePage.getSignInButton()));
        wait.until(ExpectedConditions.visibilityOf(homePage.getLogInMessage()));
        userManagementPage = homePage.goToUsermanagementPage(driver, wait);
    }

    @Lorsque("je clique sur le bouton Edit de l'administrateur")
    public void je_clique_sur_le_bouton_Edit_de_l_administrateur() {
        wait.until(ExpectedConditions.elementToBeClickable(userManagementPage.getEditAdminButton()));
        editUserPage = userManagementPage.editAdmin(driver);
    }

    @Lorsque("je modifie son mail et valide")
    public void je_modifie_son_mail_et_valide() {
        wait.until(ExpectedConditions.visibilityOf(editUserPage.getSaveButton()));
        userManagementPage = editUserPage.changeUserMail(driver, NEW_MAIL);
    }

    @Alors("le mail est modifié")
    public void le_mail_est_modifié() {
        wait.until(ExpectedConditions.visibilityOf(userManagementPage.getEditAdminButton()));
        Assert.assertEquals("Le mail a bien été modifié", NEW_MAIL, userManagementPage.getAdminEmail());
        driver.quit();
    }

    @Etantdonnéque("je suis sur la page UserManagement")
    public void je_suis_sur_la_page_UserManagement() {
        wait.until(ExpectedConditions.visibilityOf(homePage.getLogInMessage()));
        userManagementPage = homePage.goToUsermanagementPage(driver, wait);
    }

    @Quand("je récupère les profils de l'utilisateur avec le login {string}")
    public void je_récupère_les_profils_de_l_utilisateur_avec_le_login(String string) {
        wait.until(ExpectedConditions.visibilityOf(userManagementPage.getEditAdminButton()));
        rowUser = userManagementPage.checkUserProfiles(driver, string);
    }

    @Alors("je peux voir le profil User et Admin")
    public void je_peux_voir_le_profil_User_et_Admin() {
        boolean result = userManagementPage.checkRoles(rowUser);
        driver.quit();
        Assert.assertTrue("L'utilisateur ne possède pas les bons rôles", result);
    }
}
