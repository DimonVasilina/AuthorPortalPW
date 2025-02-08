package BookDraft;

import Utils.SignInAsAuthor;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class CreateDraft extends SignInAsAuthor {


    @Test
    public void createFirstDraft () throws InterruptedException {

        startNewPage();

        page.navigate("https://bnpro.ovh/books");

        page.locator("//button/span").getByText("Add a book").click();
        page.waitForURL("**/chapters");

        String curUrl = page.url();
        System.out.println(curUrl);

        Assert.assertTrue(curUrl.contains("chapters"),"URL doesn't contain 'draft/UID/chapters");

    }

    @Test
    public void checkEmptyContent ()  {

        startNewPage();

        page.navigate("https://bnpro.ovh/books/drafts/01jkdz2d4qggzj2n4nh1b99cjt/chapters");
        assertThat (page.locator("//a[@type='button']")).isVisible();

    }

}

