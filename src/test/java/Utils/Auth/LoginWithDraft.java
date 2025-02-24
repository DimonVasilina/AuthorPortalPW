package Utils.Auth;

import org.junit.jupiter.api.BeforeAll;

public class LoginWithDraft extends InitAuthWithCleanupDraft {

    @BeforeAll
    static void withDraft ( ) {
        loginAsAuthor("some@draft.com", "12345678");
    }
}
