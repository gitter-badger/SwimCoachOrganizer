package ch.tiim.sco.lenex.model;

import java.time.Duration;

public class ReactionTime {
    public int time;

    public ReactionTime(int time) {
        this.time = time;
    }

    public Duration asDuration() {
        return Duration.ofMillis(time * 10l);
    }

    public int getTime() {
        return time;
    }
}
