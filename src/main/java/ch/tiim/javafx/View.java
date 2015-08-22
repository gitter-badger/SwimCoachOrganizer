package ch.tiim.javafx;

import ch.tiim.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public abstract class View<T> {

    private static final Logger LOGGER = LogManager.getLogger(View.class.getName());
    protected Parent parent;
    protected T controller;

    public View() {
        this(null, null);
    }

    public View(T controller, Map<String, Object> customInjections) {

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
            Injector.getInstance().inject(this.controller, customInjections);
        } else {
            LOGGER.warn("Controller of " + getFXMLName() + " not set!");
        }
    }

    protected String getFXMLName() {
        return getClass().getSimpleName().replace("View", "").toLowerCase() + ".fxml";
    }

    protected String getCSSName() {
        return getClass().getSimpleName().replace("View", "").toLowerCase() + ".css";
    }

    public View(T controller) {
        this(controller, null);
    }

    public View(Map<String, Object> customInjections) {
        this(null, customInjections);
    }

    public final T getController() {
        return controller;
    }

    public final Parent getParent() {
        return parent;
    }
}
