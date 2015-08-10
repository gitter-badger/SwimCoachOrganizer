package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Record {
    @Nullable
    private Athlete athlete;
    @Nullable
    private String comment;
    @Nullable
    private MeetInfo meetInfo;
    @Nullable
    private RecordRelay relay;
    @Nullable
    private Splits splits;
    @Nonnull
    private SwimStyle swimStyle;
    @Nonnull
    private SwimTime swimtime;
    @Nullable
    private String status;
}
