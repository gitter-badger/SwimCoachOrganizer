package ch.tiim.sco.database.model;


public class Training implements Model {

    private Integer id;
    private String name;

    public Training(int id, String name) {
        this(name);
        this.id = id;
    }

    public Training(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Training training = (Training) o;

        if (id != training.id) return false;
        return !(name != null ? !name.equals(training.name) : training.name != null);

    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean hasId() {
        return id != null;
    }

    @Override
    public String uiString() {
        return toString();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
