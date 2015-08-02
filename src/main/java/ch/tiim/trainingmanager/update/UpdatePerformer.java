package ch.tiim.trainingmanager.update;

import ch.tiim.log.Log;

import java.io.IOException;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class UpdatePerformer implements Runnable {
    private static final Log LOGGER = new Log(UpdatePerformer.class);

    @Override
    public void run() {
        try {
            if (VersionChecker.isNewUpdaterVersionAvailable()) {
                LOGGER.info("Updating updater..");
                updateUpdater();
            }
            launchUpdater();
        } catch (final IOException e) {
            LOGGER.warning(e);
        }
        System.exit(0);
    }

    private void launchUpdater() throws IOException {
        LOGGER.info("Launch updater:");
        LOGGER.info(Constants.LAUNCH_UPDATER);
        Runtime.getRuntime().exec(Constants.LAUNCH_UPDATER);
    }

    private void updateUpdater() throws IOException {
        LOGGER.info("Downloading updater");
        Constants.downloadFile(Constants.REMOTE_UPDATER_URL, Constants.LOCAL_UPDATER_URL);
    }
}
