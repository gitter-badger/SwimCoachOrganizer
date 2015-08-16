package ch.tiim.sco.database.model;

public class SetFocus implements Model {

    private int id;
    private String name;
    private String abbr;
    private String notes;

    public SetFocus(int id, String name, String abbr, String notes) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
        this.notes = notes;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return name + " [" + abbr + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetFocus setFocus = (SetFocus) o;

        if (id != setFocus.id) return false;
        if (name != null ? !name.equals(setFocus.name) : setFocus.name != null) return false;
        if (abbr != null ? !abbr.equals(setFocus.abbr) : setFocus.abbr != null) return false;
        return !(notes != null ? !notes.equals(setFocus.notes) : setFocus.notes != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (abbr != null ? abbr.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @Override
    public String uiString() {
        return toString();
    }
}
