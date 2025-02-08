package ovh.bn.Auth.SignIn;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ColorScheme;
import org.testng.annotations.Test;

import java.lang.management.PlatformLoggingMXBean;

import static java.lang.Thread.sleep;

public class SignInLogic {

    private final Page page;
    public SignInLogic (Page page){
        this.page = page;
    }

    public void signInAsExistUser(String username, String password)  {

//        String username = "without@boo.ks";
//        String userPassword = "12345678";

        page.navigate("https://bnpro.ovh/");
        page.locator("//a[@type='button']/*[text()[contains(., 'Log in')]]").click();
        page.locator("//input[@id='email']").fill(username);
        page.locator("//input[@id='password']").fill(password);
        page.locator("//button[@type='submit']").first().click();

        page.waitForURL("https://bnpro.ovh/dashboard");

    }

}
