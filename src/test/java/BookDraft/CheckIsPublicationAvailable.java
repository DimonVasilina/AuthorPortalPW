package BookDraft;

import Utils.Auth.LoginAuthorWithBook;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class CheckIsPublicationAvailable extends LoginAuthorWithBook {

    @Test
    @Description("All list of requirements is fulfilled")
    @Tag("draft-001")
    public void checkPublicationPageIsAvailable () {

        startNewPage();

        page.navigate("/books/drafts/01jmsw82zn6p6e6s4br914mzzm/chapters");
        page.locator("//a/span").getByText("Publication").click();
        page.waitForURL("**/publish");
        Locator publishButton =
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Publish"));
        boolean isActive = publishButton.isVisible();

        System.out.println(isActive);

        Assert.assertTrue(isActive);
    }

    @Test
    @Description("Negative. All list of requirements is fail")
    @Tag("draft-001")

    public void showListOfRequirementsToPublish () {

        int expectedFailRequirements = 5;
        startNewPage();

        page.navigate("/books");

        page.locator("//button/span").getByText("Add a book").click();
        page.waitForURL("**/chapters");

        page.locator("//a/span").getByText("Publication").click();
        page.waitForSelector("//*[contains(@class, 'bg-orange')]").isVisible();
        int actualFailRequirements = page.locator("//*[contains(@class, 'bg-orange')]").count();
        Assert.assertEquals(actualFailRequirements,expectedFailRequirements, "Count of failed requirements unexpected");

    }

}
