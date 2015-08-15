package ch.tiim.sco.lenex.model;

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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getHsec() {
        return hsec;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d.%02d", hour, minute, second, hsec);
    }
}
