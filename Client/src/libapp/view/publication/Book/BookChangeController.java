package libapp.view.publication.Book;

import libapp.view.Main;

public class BookChangeController extends BookWinController {
    protected String ID;

    public BookChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
    }

    public BookChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
    }
}
