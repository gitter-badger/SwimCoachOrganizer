/**
 * This class is generated by jOOQ
 */
package ch.tiim.sco.database.jooq.tables.records;


import ch.tiim.sco.database.jooq.tables.SetFocus;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class SetFocusRecord extends UpdatableRecordImpl<SetFocusRecord> implements Record4<Integer, String, String, String> {

	private static final long serialVersionUID = -554881977;

	/**
	 * Setter for <code>set_focus.focus_id</code>.
	 */
	public void setFocusId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>set_focus.focus_id</code>.
	 */
	public Integer getFocusId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>set_focus.name</code>.
	 */
	public void setName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>set_focus.name</code>.
	 */
	public String getName() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>set_focus.abbr</code>.
	 */
	public void setAbbr(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>set_focus.abbr</code>.
	 */
	public String getAbbr() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>set_focus.notes</code>.
	 */
	public void setNotes(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>set_focus.notes</code>.
	 */
	public String getNotes() {
		return (String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, String, String, String> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, String, String, String> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return SetFocus.SET_FOCUS.FOCUS_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return SetFocus.SET_FOCUS.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return SetFocus.SET_FOCUS.ABBR;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return SetFocus.SET_FOCUS.NOTES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getFocusId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getAbbr();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getNotes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SetFocusRecord value1(Integer value) {
		setFocusId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SetFocusRecord value2(String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SetFocusRecord value3(String value) {
		setAbbr(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SetFocusRecord value4(String value) {
		setNotes(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SetFocusRecord values(Integer value1, String value2, String value3, String value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached SetFocusRecord
	 */
	public SetFocusRecord() {
		super(SetFocus.SET_FOCUS);
	}

	/**
	 * Create a detached, initialised SetFocusRecord
	 */
	public SetFocusRecord(Integer focusId, String name, String abbr, String notes) {
		super(SetFocus.SET_FOCUS);

		setValue(0, focusId);
		setValue(1, name);
		setValue(2, abbr);
		setValue(3, notes);
	}
}