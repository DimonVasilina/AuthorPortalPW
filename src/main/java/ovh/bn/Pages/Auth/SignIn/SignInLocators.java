package ovh.bn.Pages.Auth.SignIn;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SignInLocators {

    private Page page = null;
    private final Locator inputEmail;

    public SignInLocators() {
        this.page = page;
        this.inputEmail = page.locator("//input[@id='email']");
    }
}
