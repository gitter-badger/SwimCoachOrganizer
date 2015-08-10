package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;

public class Athlete {
    /**
     * Nonnull
     */
    private int athleteid;
    @Nonnull
    private LocalDate birthdate;
    @Nullable
    private Club club;
    @Nullable
    private Entries entries;
    @Nonnull
    private String firstname;
    @Nullable
    private String firstnameEn;
    @Nonnull
    private Gender gender;
    @Nullable
    private Handicap handicap;
    @Nonnull
    private String lastname;
    @Nullable
    private String lastnameEn;
    @Nullable
    private String level;
    @Nullable
    private String license;
    @Nullable
    private String nameprefix;
    @Nullable
    private Nation nation;
    @Nullable
    private String passport;
    @Nullable
    private Results results;
    /**
     * Nullable
     */
    private int swrid;
}
