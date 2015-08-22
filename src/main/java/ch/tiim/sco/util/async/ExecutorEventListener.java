package ch.tiim.sco.util.async;

import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ExecutorEventListener {
    private static final Logger LOGGER = LogManager.getLogger(ExecutorEventListener.class.getName());
    private final ScheduledThreadPoolExecutor e;

    public ExecutorEventListener(ScheduledThreadPoolExecutor e) {
        this.e = e;
    }

    @Subscribe
    public void onRunnable(Runnable r) {
        LOGGER.trace("Running " + r);
        e.execute(r);
    }
}
