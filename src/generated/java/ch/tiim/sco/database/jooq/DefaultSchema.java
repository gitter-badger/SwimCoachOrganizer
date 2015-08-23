/**
 * This class is generated by jOOQ
 */
package ch.tiim.sco.database.jooq;


import ch.tiim.sco.database.jooq.tables.Club;
import ch.tiim.sco.database.jooq.tables.ClubContent;
import ch.tiim.sco.database.jooq.tables.SetFocus;
import ch.tiim.sco.database.jooq.tables.SetForm;
import ch.tiim.sco.database.jooq.tables.Sets;
import ch.tiim.sco.database.jooq.tables.Team;
import ch.tiim.sco.database.jooq.tables.TeamContent;
import ch.tiim.sco.database.jooq.tables.TeamMember;
import ch.tiim.sco.database.jooq.tables.Training;
import ch.tiim.sco.database.jooq.tables.TrainingContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

	private static final long serialVersionUID = -1879466264;

	/**
	 * The reference instance of <code></code>
	 */
	public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

	/**
	 * No further instances allowed
	 */
	private DefaultSchema() {
		super("");
	}

	@Override
	public final List<Table<?>> getTables() {
		List result = new ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final List<Table<?>> getTables0() {
		return Arrays.<Table<?>>asList(
			Club.CLUB,
			ClubContent.CLUB_CONTENT,
			SetFocus.SET_FOCUS,
			SetForm.SET_FORM,
			Sets.SETS,
			Team.TEAM,
			TeamContent.TEAM_CONTENT,
			TeamMember.TEAM_MEMBER,
			Training.TRAINING,
			TrainingContent.TRAINING_CONTENT);
	}
}