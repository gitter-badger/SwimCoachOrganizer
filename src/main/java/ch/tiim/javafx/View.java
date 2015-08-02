package ch.tiim.javafx;

import ch.tiim.inject.Injector;
import ch.tiim.log.Log;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public abstract class View<T> {

    private static final Log LOGGER = new Log(View.class);
    protected Parent parent;
    protected T controller;

    public View() {
        this(null);
    }

    public View(T controller) {

        FXMLLoader l = new FXMLLoader();
        l.setLocation(getClass().getResource(getFXMLName()));
        if (controller != null) {
            l.setController(controller);
        }
        try {
            parent = l.load();
        } catch (IOException | IllegalStateException e) {
            LOGGER.error("Could not load fxml " + getFXMLName(), e);
        }
        this.controller = l.getController();
        URL css = getClass().getResource(getCSSName());
        if (css != null) {
            parent.getStylesheets().add(css.toExternalForm());
        }
        if (this.controller != null) {
            Injector.getInstance().inject(this.controller);
        } else {
            LOGGER.warning("Controller of " + getFXMLName() + " not set!");
        }
    }

    public final T getController() {
        return controller;
    }

    public final Parent getParent() {
        return parent;
    }

    protected String getFXMLName() {
        return getClass().getSimpleName().replace("View", "").toLowerCase() + ".fxml";
    }

    protected String getCSSName() {
        return getClass().getSimpleName().replace("View", "").toLowerCase() + ".css";
    }
}
