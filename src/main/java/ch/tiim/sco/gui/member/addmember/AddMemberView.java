package ch.tiim.sco.gui.member.addmember;

import ch.tiim.javafx.View;
import ch.tiim.sco.database.model.Team;
import com.google.common.collect.ImmutableMap;

public class AddMemberView extends View<AddMemberPresenter> {

    public AddMemberView(Team t) {
        super(ImmutableMap.of("team",t));
    }

}
