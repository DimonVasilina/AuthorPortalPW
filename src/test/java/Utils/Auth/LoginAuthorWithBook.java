package Utils.Auth;

import org.junit.jupiter.api.BeforeAll;

public class LoginAuthorWithBook extends InitAuth {

    @BeforeAll
    static void withoutDraft () {
        loginAsAuthor("author@with.book", "12345678");
    }

}
