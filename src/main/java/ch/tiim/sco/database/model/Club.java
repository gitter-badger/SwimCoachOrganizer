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
    public boolean hasId() {
        return id != null;
    }

    @Override
    public String uiString() {
        return toString();
    }

    @Override
    public String toString() {
        return name;
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
