package libapp.view.publication.TechnicalDoc;

import libapp.view.Main;

public class TechnicalDocChangeController extends TechnicalDocWinController {
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
