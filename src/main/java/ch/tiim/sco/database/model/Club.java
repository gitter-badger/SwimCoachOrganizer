package ch.tiim.sco.database.model;

public class Club {

    private int id;
    private String name;
    private String nameShort;
    private String nameEnglish;
    private String nameEnglishShort;
    private String code;
    private String nationality;
    private int idExtern;

    public Club(int id, String name, String nameShort, String nameEnglish, String nameEnglishShort, String code, String nationality, int idExtern) {
        this.id = id;
        this.name = name;
        this.nameShort = nameShort;
        this.nameEnglish = nameEnglish;
        this.nameEnglishShort = nameEnglishShort;
        this.code = code;
        this.nationality = nationality;
        this.idExtern = idExtern;
    }

    public int getId() {
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

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getIdExtern() {
        return idExtern;
    }

    public void setIdExtern(int idExtern) {
        this.idExtern = idExtern;
    }

    @Override
    public String toString() {
        return name;
    }
}
