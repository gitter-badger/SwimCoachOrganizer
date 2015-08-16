package ch.tiim.sco.gui.utils;

import ch.tiim.javafx.View;

public class AddDeleteView<T> extends View<AddDeletePresenter<T>> {
    public AddDeleteView() {
        super(new AddDeletePresenter<>());
    }
}
