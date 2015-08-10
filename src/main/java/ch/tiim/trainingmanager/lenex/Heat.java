package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;
import java.time.LocalTime;

public class Heat {
    /**
     * Nullable
     */
    private String free;
    /**
     * Nullable
     */
    private int agegroupid;
    @Nullable
    private LocalTime daytime;
    /**
     * Nullable
     */
    private Final final_;
    /**
     * Nonnull
     */
    private int heatid;
    /**
     * Nonnull
     */
    private int number;
    /**
     * Nullable
     */
    private int order;
    @Nullable
    private Status status;

    private enum Final {
        A, B, C, D
    }

    private enum Status {
        SEEDED, INOFFICIAL, OFFICIAL
    }
}
