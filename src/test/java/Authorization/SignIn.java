package Authorization;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class SignIn {

    @Test
    public void SignInAsExistUser() {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                             );
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setGeolocation(41.890221, 12.492348)
                .setPermissions(Arrays.asList("geolocation")));
        Page page = browser.newPage();

        String username = "without@boo.ks";
        String userPassword = "12345678";

            page.navigate("https://bnpro.ovh/");
            page.locator("//a[@type='button']/*[text()[contains(., 'Log in')]]").click();
            page.locator("//input[@id='email']").fill(username);
            page.locator("//input[@id='password']").fill(userPassword);
            page.locator("//button[@type='submit']").first().click();
            page.waitForURL("https://bnpro.ovh/dashboard");

            String currenURL = page.url();
            System.out.println(currenURL);

            Assertions.assertTrue(currenURL.equals("https://bnpro.ovh/dashboard"), "This page is not being expected");

        }
    }


