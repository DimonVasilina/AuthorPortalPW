package BookDraft;

import Utils.Auth.LoginWithoutDraft;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.GetAttributeOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckEmptyStates extends LoginWithoutDraft {

    @Test
    @Description("Check empty book tab after user has been registrited")
    @Tag("draft-002")
    public void testEmptyStateOnBooksMenu () {

        startNewPage();

        page.navigate("/books");
        int countTabs = (page.locator("//ul[@class='flex items-center gap-12']/li").count());
        Assert.assertEquals(0,countTabs, "Page shows tabs with books");
        page.waitForSelector("//button[@type='button'][span[text()='Add a book']]",
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        Locator button = page.locator("//button[@type='button'][span[text()='Add a book']]");
        boolean isVisible = button.isVisible();
        System.out.println(isVisible);

    }

}
