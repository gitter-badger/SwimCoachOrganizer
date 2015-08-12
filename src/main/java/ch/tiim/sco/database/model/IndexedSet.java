package ch.tiim.sco.database.model;

public class IndexedSet {
    private int index;
    private Set set;

    public IndexedSet(int index, Set set) {
        this.index = index;
        this.set = set;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexedSet that = (IndexedSet) o;

        return index == that.index && set.equals(that.set);

    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + set.hashCode();
        return result;
    }
}
