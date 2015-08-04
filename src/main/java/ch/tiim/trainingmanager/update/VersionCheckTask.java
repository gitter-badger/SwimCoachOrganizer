package ch.tiim.trainingmanager.update;

import ch.tiim.log.Log;
import javafx.concurrent.Task;

public class VersionCheckTask extends Task<Boolean> {
    private static final Log LOGGER = new Log(VersionCheckTask.class);

    @Override
    protected Boolean call() throws Exception {
        LOGGER.info("Version " + VersionChecker.getCurrentVersion() + " --> " +
                VersionChecker.getRemoteVersion());

        return VersionChecker.isNewVersionAvailable();
    }
}
