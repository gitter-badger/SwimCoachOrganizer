package ch.tiim.sco.update;

import ch.tiim.log.Log;
import javafx.concurrent.Task;

public class VersionCheckTask extends Task<Boolean> {
    private static final Log LOGGER = new Log(VersionCheckTask.class);

    @Override
    protected Boolean call() throws Exception {
        LOGGER.info("Version " + VersionChecker.getCurrentVersion() + " --> " +
                VersionChecker.getRemoteVersion());
        Thread.sleep(2000); //Wait a bit to not overwhelm the user.
        return VersionChecker.isNewVersionAvailable();
    }
}
