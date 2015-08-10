package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;

public class AgeGroup {
    /**
     * Nonnull
     */
    private int agegroup;
    /**
     * Nonnull
     */
    private int agemax;
    /**
     * Nonnull
     */
    private int agemin;
    @Nullable
    private Gender gender;
    @Nullable
    private Calculate calculate;
    @Nullable
    private Handicap handicap;
    @Nullable
    private String levelmax;
    @Nullable
    private String levelmin;
    @Nullable
    private String levels;
    @Nullable
    private String name;
    @Nullable
    private Rankings rankings;

    public enum Calculate {
        SINGLE, TOTAL
    }
}
