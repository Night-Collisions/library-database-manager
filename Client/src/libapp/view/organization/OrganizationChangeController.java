package libapp.view.organization;

import libapp.view.Main;

public class OrganizationChangeController extends OrganizationWinController {
    protected String ID;

    public OrganizationChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
    }

    public OrganizationChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
    }
}
