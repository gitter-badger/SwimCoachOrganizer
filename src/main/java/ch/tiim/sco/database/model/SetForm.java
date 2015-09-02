package ch.tiim.sco.database.model;


public class SetForm implements Model {

    private Integer id;
    private String name;
    private String abbr;
    private String notes;

    public SetForm(int id, String name, String abbr, String notes) {
        this(name, abbr, notes);
        this.id = id;
    }

    public SetForm(String name, String abbr, String notes) {
        this.name = name;
        this.abbr = abbr;
        this.notes = notes;
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
    public String toString() {
        return name + " [" + abbr + "]";
    }

    @Override
    public boolean hasId() {
        return id != null;
    }

    @Override
    public String uiString() {
        return toString();
    }

    public String getAbbr() {
        return abbr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
