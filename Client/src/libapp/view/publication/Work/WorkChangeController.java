package libapp.view.publication.Work;

import libapp.view.Main;

public class WorkChangeController extends WorkWinController {
    protected String ID;

    public WorkChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
        name.setText("ch");
        ph.setDisable(true);
    }

    public WorkChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
    }
}
