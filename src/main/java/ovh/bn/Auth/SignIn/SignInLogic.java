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

    @Test
    void signInAsExistUser() throws InterruptedException {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage(new Browser.NewPageOptions()
                .setViewportSize(390,844)
                .setColorScheme(ColorScheme.DARK));

        page.navigate("https://bnpro.ovh/");
        sleep (5000);


        page.close();
        browser.close();

    }

}
