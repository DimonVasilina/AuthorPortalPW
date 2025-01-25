package ovh.bn.Utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ColorScheme;

import static com.microsoft.playwright.Playwright.create;

public class InitialBrowser {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    String url = "https://bnpro.ovh/";

    public Page initBrowser (String browserName, String url) {

        playwright = create();
        switch (browserName.toLowerCase()){
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

                break;

            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));

                break;

            default:
                System.out.println("please enter right browser name");
                break;
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(url);

        return page;
    }

    public void initDARKBrowserSize ( int width, int height) {

        playwright = create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage(new Browser.NewPageOptions()
                .setViewportSize(width,height)
                .setColorScheme(ColorScheme.DARK));


    }
}
