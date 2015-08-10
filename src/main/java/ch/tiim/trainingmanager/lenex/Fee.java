package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;

public class Fee {
    @Nullable
    private Currency currency;
    @Nullable
    private Type type;
    /**
     * \@Nonnull
     */
    private int value;

    private enum Type {
        CLUB, ATHLETE, RELAY, TEAM, LATEENTRY_INDIVIDUAL, LATEENTRY_RELAY
    }
}
