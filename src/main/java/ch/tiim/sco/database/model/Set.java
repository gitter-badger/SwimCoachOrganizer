package ch.tiim.sco.database.model;

import javax.persistence.Column;

public class Set implements Model {
    @Column(name = "set_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;
    @Column(name = "distance_f1")
    private int distance1;
    @Column(name = "distance_f2")
    private int distance2;
    @Column(name = "distance_f3")
    private int distance3;
    @Column(name = "intensity")
    private int intensity;
    @Column(name = "focus_id")
    private SetFocus focus;
    @Column(name = "form_id")
    private SetForm form;
    @Column(name = "notes")
    private String notes;
    @Column(name = "interval")
    private int interval;
    @Column(name = "is_pause")
    private boolean isPause;

    public Set() {

    }

    public Set(int id, String name, String content, int distance1, int distance2, int distance3, int intensity, SetFocus focus, SetForm form, String notes, int interval, boolean isPause) {
        this(name, content, distance1, distance2, distance3, intensity, focus, form, notes, interval, isPause);
        this.id = id;
    }

    public Set(String name, String content, int distance1, int distance2, int distance3, int intensity, SetFocus focus, SetForm form, String notes, int interval, boolean isPause) {
        this.name = name;
        this.content = content;
        this.distance1 = normalize(distance1);
        this.distance2 = normalize(distance2);
        this.distance3 = normalize(distance3);
        this.intensity = intensity;
        this.focus = focus;
        this.form = form;
        this.notes = notes;
        this.interval = interval;
        this.isPause = isPause;
    }

    private static int normalize(int i) {
        if (i <= 0) {
            return 1;
        }
        return i;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + distance1;
        result = 31 * result + distance2;
        result = 31 * result + distance3;
        result = 31 * result + intensity;
        result = 31 * result + (focus != null ? focus.hashCode() : 0);
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + interval;
        result = 31 * result + (isPause ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Set set = (Set) o;

        if (id != set.id) return false;
        if (distance1 != set.distance1) return false;
        if (distance2 != set.distance2) return false;
        if (distance3 != set.distance3) return false;
        if (intensity != set.intensity) return false;
        if (interval != set.interval) return false;
        if (isPause != set.isPause) return false;
        if (name != null ? !name.equals(set.name) : set.name != null) return false;
        if (content != null ? !content.equals(set.content) : set.content != null) return false;
        if (focus != null ? !focus.equals(set.focus) : set.focus != null) return false;
        if (form != null ? !form.equals(set.form) : set.form != null) return false;
        return !(notes != null ? !notes.equals(set.notes) : set.notes != null);

    }

    @Override
    public String toString() {
        return "Set{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", distance1=" + distance1 +
                ", distance2=" + distance2 +
                ", distance3=" + distance3 +
                ", intensity=" + intensity +
                ", focus=" + focus +
                ", form=" + form +
                ", notes='" + notes + '\'' +
                ", interval=" + interval +
                ", isPause=" + isPause +
                '}';
    }

    @Override
    public boolean hasId() {
        return id != null;
    }

    @Override
    public String uiString() {
        return name + " [" + getDistance() + "]";
    }

    public String getDistance() {
        StringBuilder b = new StringBuilder();
        if (getDistance1() > 1) {
            b.append(getDistance1()).append("x");
        }
        if (getDistance2() > 1) {
            b.append(getDistance2()).append("x");
        }
        b.append(getDistance3()).append("m");
        return b.toString();
    }

    public int getDistance1() {
        return distance1;
    }

    public void setDistance1(int distance1) {
        this.distance1 = normalize(distance1);
    }

    public int getDistance2() {
        return distance2;
    }

    public void setDistance2(int distance2) {
        this.distance2 = normalize(distance2);
    }

    public int getDistance3() {
        return distance3;
    }

    public void setDistance3(int distance3) {
        this.distance3 = normalize(distance3);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SetFocus getFocus() {
        return focus;
    }

    public void setFocus(SetFocus focus) {
        this.focus = focus;
    }

    public SetForm getForm() {
        return form;
    }

    public void setForm(SetForm form) {
        this.form = form;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String getIntervalString() {
        if (isPause) {
            return "P: " + getInterval();
        } else {
            return "@: " + getInterval();
        }
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean getIsPause() {
        return isPause;
    }

    public void setIsPause(boolean isPause) {
        this.isPause = isPause;
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

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getTotalDistance() {
        return distance1 * distance2 * distance3;
    }

    public boolean isPause() {
        return isPause;
    }
}
