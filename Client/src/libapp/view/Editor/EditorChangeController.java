package libapp.view.Editor;

import libapp.view.Main;

public class EditorChangeController extends EditorWinController{
    protected String ID;

    public EditorChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
    }

    public EditorChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
    }
}
