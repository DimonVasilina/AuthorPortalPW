package Utils.Auth;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import ovh.bn.Pages.Auth.SignIn.SignInLogic;

public class InitAuth {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext browserContext;
    protected Page page;

    protected static String baseUrl = "https://bnpro.ovh";

    protected static void loginAsAuthor (String username, String password) {

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setBaseURL(baseUrl));

                // Perform login once
                Page loginPage = browserContext.newPage();
                SignInLogic login = new SignInLogic(loginPage);
                login.signInAsExistUser (username, password);

                // Close login page but keep session
                loginPage.close();
    }

    @AfterAll
    static void tearDownOnce() {


                browser.close();
                playwright.close();
    }

    protected void startNewPage() {
                page = browserContext.newPage();  // Reuse authenticated session
            }
        }
