package ch.tiim.sco.util;

import javafx.stage.Stage;

import java.nio.file.Path;

public class FileChooserUtil {
    //TODO: Fix nullpointer
    public static Path saveFile(Stage s, javafx.stage.FileChooser.ExtensionFilter... exts) {
        javafx.stage.FileChooser fc = new javafx.stage.FileChooser();
        fc.setTitle("Select file to export");
        fc.getExtensionFilters().addAll(exts);
        return fc.showSaveDialog(s).toPath();
    }

    public static Path openFile(Stage s, javafx.stage.FileChooser.ExtensionFilter... exts) {
        javafx.stage.FileChooser fc = new javafx.stage.FileChooser();
        fc.setTitle("Select file to open");
        fc.getExtensionFilters().addAll(exts);
        return fc.showOpenDialog(s).toPath();
    }
}
