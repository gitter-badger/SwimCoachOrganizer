package ch.tiim.sco.database.model;

public class Club implements Model {
    private Integer id;
    private String name;
    private String nameShort;
    private String nameEn;
    private String nameShortEn;
    private String code;
    private String nationality;
    private int externId;

    public Club(int id, String name, String nameShort, String nameEn, String nameShortEn, String code, String nationality, int externId) {
        this(name, nameShort, nameEn, nameShortEn, code, nationality, externId);
        this.id = id;
    }

    public Club(String name, String nameShort, String nameEn, String nameShortEn, String code, String nationality, int externId) {
        this.name = name;
        this.nameShort = nameShort;
        this.nameEn = nameEn;
        this.nameShortEn = nameShortEn;
        this.code = code;
        this.nationality = nationality;
        this.externId = externId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Club club = (Club) o;

        if (externId != club.externId) return false;
        if (id != null ? !id.equals(club.id) : club.id != null) return false;
        if (name != null ? !name.equals(club.name) : club.name != null) return false;
        if (nameShort != null ? !nameShort.equals(club.nameShort) : club.nameShort != null) return false;
        if (nameEn != null ? !nameEn.equals(club.nameEn) : club.nameEn != null) return false;
        if (nameShortEn != null ? !nameShortEn.equals(club.nameShortEn) : club.nameShortEn != null)
            return false;
        if (code != null ? !code.equals(club.code) : club.code != null) return false;
        return !(nationality != null ? !nationality.equals(club.nationality) : club.nationality != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameShort != null ? nameShort.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameShortEn != null ? nameShortEn.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + externId;
        return result;
    }

    @Override
    public boolean hasId() {
        return id != null;
    }

    @Override
    public String uiString() {
        return name;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameShort='" + nameShort + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", nameShortEn='" + nameShortEn + '\'' +
                ", code='" + code + '\'' +
                ", nationality='" + nationality + '\'' +
                ", externId=" + externId +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExternId() {
        return externId;
    }

    public void setExternId(int externId) {
        this.externId = externId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameShortEn() {
        return nameShortEn;
    }

    public void setNameShortEn(String nameShortEn) {
        this.nameShortEn = nameShortEn;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
