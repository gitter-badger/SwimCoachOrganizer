package ch.tiim.trainingmanager.gui;

import javafx.scene.control.TextField;

public interface Page {
    void opened();
    String getName();

    static boolean validateTextField(TextField t, String pattern) {
        if (!t.getText().matches(pattern)) {
            t.requestFocus();
            t.selectAll();
            return false;
        }
        return true;
    }
}
