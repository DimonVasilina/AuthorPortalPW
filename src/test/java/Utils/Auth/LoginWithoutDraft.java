package Utils.Auth;

import org.junit.jupiter.api.BeforeAll;

public class LoginWithoutDraft extends InitAuth {

    @BeforeAll
    static void withoutDraft () {
        loginAsAuthor("without@draft.com", "12345678");
    }

}
