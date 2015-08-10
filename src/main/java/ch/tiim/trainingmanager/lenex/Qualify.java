package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;

public class Qualify {
    @Nullable
    private Conversion conversion;
    @Nonnull
    private LocalDate from;
    /**
     * \@Nullable
     */
    private int percent;
    @Nullable
    private LocalDate until;

    private enum Conversion {
        NONE, FINA_POINTS, PERCENT_LINEAR, NON_CONFORMING_LAST
    }
}
