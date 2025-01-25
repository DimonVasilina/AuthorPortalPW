package Authorization;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ColorScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeClass;
import ovh.bn.Utils.InitialBrowser;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


import static java.lang.Thread.sleep;

public class SignIn {


    @Test
    public void SignInAsExistUser() {

        String username = "ukraine@booknet.com";
        String userPassword = "12345678";


        try (Playwright playwright = Playwright.create()){
            Page page = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false))
                    .newPage(new Browser.NewPageOptions()
                            .setViewportSize(430, 932)
                            .setColorScheme(ColorScheme.DARK));

            page.navigate("https://bnpro.ovh/");
            page.locator("//a[@type='button']/*[text()[contains(., 'Log in')]]").click();
            page.locator("//input[@id='email']").fill(username);
            page.locator("//input[@id='password']").fill(userPassword);
            page.locator("//button[@type='submit']").first().click();
            page.waitForURL("https://bnpro.ovh/dashboard");

            String currenURL = page.url();
            System.out.println(currenURL);

            Assertions.assertTrue(currenURL.equals("https://bnpro.ovh/dashboard"), "This page is not being expected");

            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
