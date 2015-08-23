package ch.tiim.sco.database.model;

import javax.persistence.Column;

public class IndexedSet implements Model {
    @Column(name = "indx")
    private int index;
    @Column(name = "sets")
    private Set set;

    public IndexedSet(int index, Set set) {
        this.index = index;
        this.set = set;
    }

    @Override
    public boolean hasId() {
        return false;
    }

    @Override
    public String uiString() {
        return toString();
    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + set.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexedSet that = (IndexedSet) o;

        return index == that.index && set.equals(that.set);

    }

    @Override
    public String toString() {
        return "IndexedSet{" +
                "index=" + index +
                ", set=" + set +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }
}
