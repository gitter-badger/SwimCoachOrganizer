package ch.tiim.trainingmanager.database.model;

public class SetForm {

    private final int id;
    private final String name;
    private final String abbr;
    private final String notes;

    public SetForm(int id, String name, String abbr, String notes) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetForm form = (SetForm) o;

        if (id != form.id) return false;
        if (name != null ? !name.equals(form.name) : form.name != null) return false;
        if (abbr != null ? !abbr.equals(form.abbr) : form.abbr != null) return false;
        return !(notes != null ? !notes.equals(form.notes) : form.notes != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (abbr != null ? abbr.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}
