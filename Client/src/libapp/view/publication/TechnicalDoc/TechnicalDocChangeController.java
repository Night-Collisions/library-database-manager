package libapp.view.publication.TechnicalDoc;

import libapp.view.Main;

public class TechnicalDocChangeController extends TechnicalDocWinController {
    protected String ID;

    public TechnicalDocChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
        name.setText("Ch");
    }

    public TechnicalDocChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
    }
}
