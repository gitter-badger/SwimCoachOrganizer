package ch.tiim.sco.update;

import ch.tiim.log.Log;
import com.google.common.eventbus.EventBus;

public class VersionCheckTask implements Runnable {
    private static final Log LOGGER = new Log(VersionCheckTask.class);
    private final EventBus eventBus;

    public VersionCheckTask(EventBus eventBus) {

        this.eventBus = eventBus;
    }

    @Override
    public void run() {
        LOGGER.info("Version " + VersionChecker.getCurrentVersion() + " --> " +
                VersionChecker.getRemoteVersion());
        try {
            Thread.sleep(2000); //Wait a bit to not overwhelm the user.
        } catch (InterruptedException e) {
            LOGGER.info("Interrupted");
        }
        if (VersionChecker.isNewVersionAvailable()) {
            eventBus.post(new NewVersionEvent(
                    VersionChecker.getCurrentVersion(),
                    VersionChecker.getRemoteVersion()
            ));
        }
    }
}
