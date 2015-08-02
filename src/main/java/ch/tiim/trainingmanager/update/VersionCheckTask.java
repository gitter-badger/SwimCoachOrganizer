package ch.tiim.trainingmanager.update;

import javafx.concurrent.Task;

public class VersionCheckTask extends Task<Boolean> {

    @Override
    protected Boolean call() throws Exception {
        return VersionChecker.isNewVersionAvailable();
    }
}
