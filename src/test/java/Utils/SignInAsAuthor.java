package Utils;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ovh.bn.Auth.SignIn.SignInLogic;

public class SignInAsAuthor {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext browserContext;
    protected static Page page;

    @BeforeAll
    static void loginAsAuthor () {

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();

                // Perform login once
                Page loginPage = browserContext.newPage();
                SignInLogic login = new SignInLogic(loginPage);
                login.signInAsExistUser ("without@boo.ks", "12345678");

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
