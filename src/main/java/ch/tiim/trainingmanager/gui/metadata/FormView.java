package ch.tiim.trainingmanager.gui.metadata;

import ch.tiim.javafx.View;

public class FormView extends View<FormPresenter> {
    public FormView() {
        super(new FormPresenter());
    }

    @Override
    protected String getFXMLName() {
        return "focus.fxml";
    }
}
