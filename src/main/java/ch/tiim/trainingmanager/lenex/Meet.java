package ch.tiim.trainingmanager.lenex;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Meet {

    @Nullable
    private AgeDate ageDate;
    /**
     * \@Nullable
     */
    private int altitude;
    @Nullable
    private String city;
    @Nonnull
    private String cityEn;
    @Nullable
    private Clubs clubs;
    @Nullable
    private Contact contact;
    @Nullable
    private Course course;
    @Nullable
    private LocalDate deadline;
    @Nullable
    private LocalTime deadlineTime;
    @Nullable
    private LocalDate entryStartDate;
    @Nullable
    private EntryType entryType;
    @Nullable
    private Fees fees;
    @Nullable
    private String hostclub;
    @Nullable
    private String hostclubUrl;
    /**
     * \@Nullable
     */
    private int maxEntries;
    @Nonnull
    private String name;
    @Nullable
    private String nameEn;
    @Nonnull
    private Nation nation;
    @Nullable
    private String number;
    @Nullable
    private String organizer;
    @Nullable
    private String organizerUrl;
    @Nullable
    private PointTable pointTable;
    @Nullable
    private Pool pool;
    @Nullable
    private Qualify qualify;
    @Nullable
    private String resultUrl;
    @Nonnull
    private Sessions sessions;
    @Nullable
    private String state;
    @Nullable
    private String uid;
    @Nullable
    private Timing timing;
    @Nullable
    private String type;

    private enum EntryType {
        OPEN,INVITATION
    }
}
