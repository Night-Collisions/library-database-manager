package libapp.view.author;

import libapp.view.Main;

public class AuthorChangeController extends AuthorWinController {
    protected String authorID;

    public AuthorChangeController(Main main, String id) {
        super(main);
        authorID = id;
    }

    protected void initialize() {
        super.initialize();
        name.setText("ch");
    }

    public AuthorChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
    }
}
