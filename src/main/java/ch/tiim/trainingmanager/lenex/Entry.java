package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;

public class Entry {
    /**
     * Nullable
     */
    private int agegroupid;
    @Nullable
    private Course entrycourse;
    @Nullable
    private SwimTime entrytime;
    /**
     * Nonnull
     */
    private int eventid;
    /**
     * Nullable
     */
    private int heatid;
    /**
     * Nullable
     */
    private int lane;
    @Nullable
    private MeetInfo meetinfo;
    @Nullable
    private RelayPositions relayPositions;
    @Nullable
    private Status status;

    public enum Status {
        EXH,
        RJC,
        SICK,
        WDR
    }
}
