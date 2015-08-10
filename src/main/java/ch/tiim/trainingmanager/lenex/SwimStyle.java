package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SwimStyle {
    @Nullable
    private String code;
    /**
     * \@Nonnull
     */
    private int distance;
    @Nullable
    private String name;
    /**
     * \@Nonnull
     */
    private int relaycount;
    @Nonnull
    private Stroke stroke;
    /**
     * \@Nullable
     */
    private int swimstyleid;
    @Nullable
    private Technique technique;

    public enum Technique {
        DIVE,
        GLIDE,
        KICK,
        PULL,
        START,
        TURN
    }
}
