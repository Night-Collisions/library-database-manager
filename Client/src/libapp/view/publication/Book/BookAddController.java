package libapp.view.publication.Book;

public class BookAddController extends BookWinController{
    protected void initialize() {
        super.initialize();

        //fillPubHouseCombobox();
    }

    public void fillPubHouseCombobox() {
        publishingHouses.getItems().add("хуй");
    }

    protected void applyChange() {
        super.applyChange();
    }
}
