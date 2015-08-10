package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;

public class RelayPosition {
    @Nullable
    private Athlete athlete;
    /**
     * \@Nullable
     */
    private int athleteid;
    @Nullable
    private MeetInfo meetinfo;
    /**
     * \@Notnull
     */
    private int number;
    @Nullable
    private ReactionTime reactionTime;
    @Nullable
    private Status status;

    private enum Status {
        DSQ, DNF
    }
}
