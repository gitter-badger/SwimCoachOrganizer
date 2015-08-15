package ch.tiim.sco.util.async;

import ch.tiim.log.Log;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ExecutorEventListener {
    private static final Log LOGGER = new Log(ExecutorEventListener.class);
    private final ScheduledThreadPoolExecutor e;

    public ExecutorEventListener(ScheduledThreadPoolExecutor e) {
        this.e = e;
    }

    @Subscribe
    public void onRunnable(Runnable r) {
        LOGGER.info("Running " + r);
        e.execute(r);
    }
}
