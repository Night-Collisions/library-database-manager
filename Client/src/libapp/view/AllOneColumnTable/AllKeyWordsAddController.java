package libapp.view.AllOneColumnTable;

import libapp.view.Main;

public class AllKeyWordsAddController extends AllOneColumnTableWinController {
    public AllKeyWordsAddController(Main main) {
        this.main = main;
    }

    protected void initialize() {
        initialize("Ключевое слово:");
    }

    protected void applyChange() {
        createRow("addKeyword");
        super.applyChange();
    }
}
