package BookDraft;

import Utils.Auth.LoginWithDraft;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.github.javafaker.Faker;
import com.microsoft.playwright.options.AriaRole;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import ovh.bn.Pages.CreateDraft.CreateDraftLogic;

import static Utils.Auth.InitAuthWithCleanupDraft.urlsOfDraft;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CreateDraft extends LoginWithDraft {



    @Test
    @Description("Go to new draft without chapters")
    public void checkEmptyDraftContent ()  {

        startNewPage();

        page.navigate("/books/drafts/01jkp2c3k1cx3xn2vds91f57pw/chapters");
        assertThat(page.getByText("Add the first chapter"));

    }

    @Test
    public void createChapterAsDone () throws InterruptedException {

        Faker faker = new Faker();
        String title = faker.book().title();
        String chapterText = new CreateDraftLogic().generateChapterText(3500);

        startNewPage();

        page.navigate("/books");

        page.locator("//button/span").getByText("Add a book").click();
        page.waitForURL("**/chapters");

        urlsOfDraft.add(page.url());
        System.out.println(urlsOfDraft);

        page.locator("//*[@type='button']/span[text()='Add a chapter']")
              .click();
        page.getByPlaceholder("Title of the chapter").fill(title);
        page.locator("//p[@data-placeholder='Start writing here...']").fill(chapterText);
        page.locator("//select").first().selectOption("done");
        page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Save")).click();
        page.waitForURL("**/chapters");

          Locator row = page.locator("//a[contains(text(),'"+title+"')]/ancestor::tr");
          String status = row.locator("//td/span").innerText();
          System.out.println("chapter status - " + status);
          Assert.assertEquals(status, "Done", "Chapter hasn't status 'Done'");

    }


    @Test
    public void createChapterAsDraft () {

        Faker faker = new Faker();
        String title = faker.book().title();

        startNewPage();

        page.navigate("/books");

        page.locator("//button/span").getByText("Add a book").click();
        page.waitForURL("**/chapters");

        urlsOfDraft.add(page.url());
        System.out.println(urlsOfDraft);

        page.locator("//*[@type='button']/span[text()='Add a chapter']")
                .click();
        page.getByPlaceholder("Title of the chapter").fill(title);
        Assert.assertTrue(page.locator("//p[@data-placeholder='Start writing here...']").isVisible(),
                "Placeholder 'Start writing here...' doesn't show");
        page.locator("//select").first().selectOption("draft");
        page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Save")).click();
        page.waitForURL("**/chapters");

        Locator row = page.locator("//a[contains(text(),'"+title+"')]/ancestor::tr");
        String status = row.locator("//td/span").innerText();
        System.out.println("chapter status - " + status);
        Assert.assertEquals(status, "Draft", "Chapter hasn't status 'Draft'");

    }

    @Test
    public void addTextToDraftSaveAsDone (){

        Faker faker = new Faker();
        String title = faker.book().title();
        String chapterText = new CreateDraftLogic().generateChapterText(3500);

        startNewPage();

        page.navigate("/books");

        page.locator("//button/span").getByText("Add a book").click();
        page.waitForURL("**/chapters");

        urlsOfDraft.add(page.url());
        System.out.println(urlsOfDraft);

        page.locator("//*[@type='button']/span[text()='Add a chapter']")
                .click();
        page.getByPlaceholder("Title of the chapter").fill(title);
        Assert.assertTrue(page.locator("//p[@data-placeholder='Start writing here...']").isVisible(),
                "Placeholder 'Start writing here...' doesn't show");
        //page.locator("//select").first().selectOption("draft");
        page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Save")).click();
        page.waitForURL("**/chapters");

        page.locator("//a[contains(text(),'"+title+"')]").click();

        assertThat(page.getByText(title));
        page.locator("//p[@data-placeholder='Start writing here...']").fill(chapterText);
        page.locator("//select").first().selectOption("done");
        page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Save")).click();
        Locator row = page.locator("//a[contains(text(),'"+title+"')]/ancestor::tr");
        String status = row.locator("//td/span").innerText();
        System.out.println("chapter status - " + status);
        Assert.assertEquals(status, "Done", "Chapter hasn't status 'Draft'");

    }
}

