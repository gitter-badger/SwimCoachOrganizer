package ch.tiim.sco.lenex.model;

import java.time.Duration;

public class SwimTime {
    public int hour;
    public int minute;
    public int second;
    public int hsec;

    public SwimTime(int hour, int minute, int second, int hsec) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.hsec = hsec;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d.%02d", hour, minute, second, hsec);
    }

    public Duration asDuration() {
        long milis = hour * 60l * 60l * 1000l;
        milis += minute * 60l * 1000l;
        milis += second * 1000l;
        milis += hsec * 10l;
        return Duration.ofMillis(milis);
    }

    public int getHour() {
        return hour;
    }

    public int getHsec() {
        return hsec;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
