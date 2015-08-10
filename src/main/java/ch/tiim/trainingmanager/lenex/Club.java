package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Club {
    @Nullable
    private Athletes athletes;
    @Nullable
    private String code;
    @Nullable
    private Contact contact;
    @Nonnull
    private String name;
    @Nullable
    private String nameEn;
    @Nullable
    private Nation nation;
    @Nullable
    private int number;
    @Nullable
    private Officials officials;
    @Nullable
    private String region;
    @Nullable
    private RelaysTeam relays;
    @Nullable
    private String shortname;
    @Nullable
    private String shortnameEn;
    @Nullable
    private String swrid;
    @Nullable
    private Type type ;

    private enum Type {
        CLUB, NATIONALTEAM, REGIONALTEAM, UNATTACHED
    }
}
