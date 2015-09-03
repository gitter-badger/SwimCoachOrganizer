package ch.tiim.sco.lenex;

import ch.tiim.sco.lenex.model.Club;
import ch.tiim.sco.lenex.model.Lenex;
import ch.tiim.sco.lenex.model.Meet;

import java.util.List;

public class ResultsData {
    private List<Meet> meets;

    private ResultsData(Lenex l, int meet) throws LenexException {
        this.meets = l.meets.meets;

        String error;
        int i = 1;
        for(Meet m : meets) {
            i += 1;
            if (!(error = validateData(m)).equals("")) {
                throw new LenexException(String.format("Meet nr %d %s", i, error));
            }
        }
    }

    private String validateData(Meet meet) {
        if (meet == null) return "Meet is null";
        if (meet.clubs == null) return "Meet has no clubs";
        if (meet.clubs.clubs.size() < 1) return "Meet has no clubs";
        for (Club c : meet.clubs.clubs) {
            if (c.athletes == null) return "Club has no athlete";

        }
        return "";
    }
}
