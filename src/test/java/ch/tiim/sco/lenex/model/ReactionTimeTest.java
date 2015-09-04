package ch.tiim.sco.lenex.model;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class ReactionTimeTest {

    @Test
    public void testAsDuration() throws Exception {
        ReactionTime t = new ReactionTime(45); // 0.45sec
        Duration duration = t.asDuration();
        assertThat(duration, Is.is(Duration.ofMillis(450)));
    }
}