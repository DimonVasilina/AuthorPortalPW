package Utils.Auth;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterAll;

import java.util.ArrayList;
import java.util.List;

public class InitAuthWithCleanupDraft extends InitAuth {

    public static  List <String> urlsOfDraft = new ArrayList<>();

    @AfterAll
    static void cleanupdraft () {
        if (!urlsOfDraft.isEmpty()) {
            System.out.println("Deleting created drafts...");
            Page deletePage = browserContext.newPage();

            for (String draftUrl : urlsOfDraft) {
                deletePage.navigate(draftUrl);
                deletePage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove")).click();
                deletePage.locator("//button[@type='button']/span[text()='Remove']").click();
                deletePage.waitForURL("/books/drafts");
                System.out.println("Deleted draft: " + draftUrl);
            }

            deletePage.close();
            urlsOfDraft.clear();
        }
    }

    }

