package libapp.view.AllOneColumnTable;

import libapp.view.Main;

import static libapp.view.RegularForField.setIntField;

public class AllUDCAddController extends AllOneColumnTableWinController {
    public AllUDCAddController(Main main) {
        this.main = main;
    }

    protected void initialize() {
        initialize("УДК:");
        setIntField(name);
    }

    protected void applyChange() {
        createRow("addUdc");
        super.applyChange();
    }
}
