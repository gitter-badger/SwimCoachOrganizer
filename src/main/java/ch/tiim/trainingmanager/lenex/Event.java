package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalTime;

public class Event {
    @Nullable
    private AgeGroups ageGroups;
    @Nullable
    private LocalTime daytime;
    /**
     * Nonnull
     */
    private int eventid;
    @Nullable
    private Fee fee;
    @Nullable
    private Gender gender;
    @Nullable
    private Heats heats;
    /**
     * Nullable
     */
    private int maxentries;
    /**
     * Nonnull
     */
    private int number;
    /**
     * Nullable
     */
    private int order;
    /**
     * Nullable
     */
    private int preveventid;
    @Nullable
    private Round round;
    /**
     * Nullable
     */
    private int run;
    @Nonnull
    private SwimStyle swimstyle;
    @Nullable
    private TimeStandardRefs timeStandardRefs;
    @Nullable
    private Timing timing;
    @Nullable
    private Type type;


    public enum Round {
        TIM,
        FHT,
        FIN,
        SEM,
        QUA,
        PRE,
        SOP,
        SOS,
        SOQ
    }

    public enum Type {
        EMPTY,
        MASTERS
    }
}
