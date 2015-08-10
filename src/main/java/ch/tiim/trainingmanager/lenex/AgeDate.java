package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import java.time.LocalDate;

public class AgeDate {
    @Nonnull
    private Type type;
    @Nonnull
    private LocalDate date;

    private enum Type {
        YEAR, DATE, POR, CAN_FNQ, LUX
    }
}
