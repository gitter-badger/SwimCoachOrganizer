package ch.tiim.sco.lenex.model;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertThat;

public class SwimTimeTest {

    @Test
    public void testAsDuration() throws Exception {
        SwimTime swimTime = new SwimTime(1, 1, 1, 1);
        Duration d = swimTime.asDuration();
        assertThat(d, Is.is(Duration.ofMillis(3_661_010)));
    }
}