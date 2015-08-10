package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Result {
    @Nullable
    private String comment;
    /**
     * \@Nonnull
     */
    private int eventid;
    /**
     * \@Nullable
     */
    private int heatid;
    /**
     * \@Nullable
     */
    private int lane;
    /**
     * \@Nullable
     */
    private int points;
    @Nullable
    private ReactionTime reactionTime;
    @Nullable
    private RelayPositions relayPositions;
    /**
     * \@Nonnull
     */
    private int resultid;
    @Nullable
    private Status status;
    @Nullable
    private Splits splits;
    @Nonnull
    private SwimTime swimTime;

    private enum Status {
        EXH,
        DSQ,
        DNS,
        DNF,
        SICK,
        WDR
    }
}
