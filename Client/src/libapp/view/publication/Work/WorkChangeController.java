package libapp.view.publication.Work;

import libapp.view.Main;

public class WorkChangeController extends WorkWinController {
    protected void initialize() {
        super.initialize();
        name.setText("ch");
    }

    public WorkChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
    }
}
