package ch.tiim.sco.database.model;

import javax.persistence.Column;

public class Club implements Model {
    @Column(name = "club_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "name_short")
    private String nameShort;
    @Column(name = "name_en")
    private String nameEnglish;
    @Column(name = "name_short_en")
    private String nameEnglishShort;
    @Column(name = "code")
    private String code;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "extern_id")
    private int idExtern;

    public Club(int id, String name, String nameShort, String nameEnglish, String nameEnglishShort, String code, String nationality, int idExtern) {
        this(name, nameShort, nameEnglish, nameEnglishShort, code, nationality, idExtern);
        this.id = id;
    }

    public Club(String name, String nameShort, String nameEnglish, String nameEnglishShort, String code, String nationality, int idExtern) {
        this.name = name;
        this.nameShort = nameShort;
        this.nameEnglish = nameEnglish;
        this.nameEnglishShort = nameEnglishShort;
        this.code = code;
        this.nationality = nationality;
        this.idExtern = idExtern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Club club = (Club) o;

        if (idExtern != club.idExtern) return false;
        if (id != null ? !id.equals(club.id) : club.id != null) return false;
        if (name != null ? !name.equals(club.name) : club.name != null) return false;
        if (nameShort != null ? !nameShort.equals(club.nameShort) : club.nameShort != null) return false;
        if (nameEnglish != null ? !nameEnglish.equals(club.nameEnglish) : club.nameEnglish != null) return false;
        if (nameEnglishShort != null ? !nameEnglishShort.equals(club.nameEnglishShort) : club.nameEnglishShort != null)
            return false;
        if (code != null ? !code.equals(club.code) : club.code != null) return false;
        return !(nationality != null ? !nationality.equals(club.nationality) : club.nationality != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameShort != null ? nameShort.hashCode() : 0);
        result = 31 * result + (nameEnglish != null ? nameEnglish.hashCode() : 0);
        result = 31 * result + (nameEnglishShort != null ? nameEnglishShort.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + idExtern;
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
                ", nameEnglish='" + nameEnglish + '\'' +
                ", nameEnglishShort='" + nameEnglishShort + '\'' +
                ", code='" + code + '\'' +
                ", nationality='" + nationality + '\'' +
                ", idExtern=" + idExtern +
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

    public int getIdExtern() {
        return idExtern;
    }

    public void setIdExtern(int idExtern) {
        this.idExtern = idExtern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getNameEnglishShort() {
        return nameEnglishShort;
    }

    public void setNameEnglishShort(String nameEnglishShort) {
        this.nameEnglishShort = nameEnglishShort;
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
