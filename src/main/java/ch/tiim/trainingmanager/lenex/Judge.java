package ch.tiim.trainingmanager.lenex;

public class Judge {

    /**
     * Nullable,
     */
    private int number;
    /**
     * Nonnull,
     */
    private int officialid;
    private Role role;

    private enum Role {
        OTH,
        MDR,
        TDG,
        REF,
        STA,
        ANN,
        JOS,
        CTIK,
        TIK,
        CFIN,
        FIN,
        CIOT,
        IOT,
        FSR,
        COC,
        CREC,
        REC,
        CRS,
        CR,
        MED
    }
}
