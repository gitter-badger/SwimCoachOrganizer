package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;

public class Pool {
    @Nullable
    private String name;
    /**
     * \@Nullable
     */
    private int lanemax;
    /**
     * \@Nullable
     */
    private int lanemin;
    /**
     * \@Nullable
     */
    private int temperature;
    @Nullable
    private Type type;

    private enum Type {
        INDOOR, OUTDOOR, LAKE, OCEAN
    }
}
