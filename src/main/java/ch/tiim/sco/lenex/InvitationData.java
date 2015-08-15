package ch.tiim.sco.lenex;

import ch.tiim.sco.lenex.model.Event;
import ch.tiim.sco.lenex.model.Lenex;
import ch.tiim.sco.lenex.model.Meet;
import ch.tiim.sco.lenex.model.Session;

import java.util.ArrayList;
import java.util.List;

public class InvitationData {
    private Meet meet;

    public InvitationData(Lenex l, int meet) throws LenexException {
        this.meet = l.meets.meets.get(meet);
        String error;
        if (!(error = validateData()).equals("")) throw new LenexException(error);
    }

    private String validateData() {
        if (meet == null) return "No meet object";
        if (meet.sessions == null) return "Meet has no sessions";
        if (meet.sessions.sessions.size() < 1) return "Meet has no sessions";
        for (Session s : meet.sessions.sessions) {
            if (s.events == null
                    || s.events.events.size() < 1) return "Session has no events";
            for (Event e : s.events.events) {
                if (e.swimstyle == null) return "Event has no swim style";
            }
        }
        return "";
    }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        for (Session s : meet.sessions.sessions) {
            events.addAll(s.events.events);
        }
        return events;
    }
}
