package libapp.view.publication.Thesis;

import libapp.view.Main;

public class ThesisChangeController extends ThesisWinController {
    protected String ID;

    public ThesisChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        where.setDisable(true);
        super.initialize();
    }

    public ThesisChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
    }
}
