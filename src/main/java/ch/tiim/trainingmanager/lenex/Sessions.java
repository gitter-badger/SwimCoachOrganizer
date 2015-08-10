package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Sessions {
    @Nullable
    private Course course;
    @Nonnull
    private LocalDate date;
    @Nullable
    private LocalTime daytime;
    @Nonnull
    private Events events;
    @Nullable
    private Fees fees;
    @Nullable
    private Judges judges;
    @Nullable
    private String name;
    /**
     * \@Nonnull
     */
    private int number;
    @Nullable
    private LocalTime officialmeeting;
    @Nullable
    private Pool pool;
    @Nullable
    private LocalTime teamleadermeeting;
    @Nullable
    private LocalTime warmupfrom;
    @Nullable
    private LocalTime warmupuntil;

}
