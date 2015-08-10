package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RelayTeam {
    /**
     * \@Nonnull
     */
    private int agemax;
    /**
     * \@Nonnull
     */
    private int agemin;
    /**
     * \@Nonnull
     */
    private int agetotalmax;
    /**
     * \@Nonnull
     */
    private int agetotalmin;
    @Nullable
    private Entries entries;
    @Nonnull
    private Gender gender;
    @Nullable
    private Handicap handicap;
    @Nullable
    private String name;
    /**
     * \@Nullable
     */
    private int number;
    @Nullable
    private Results results;
}
