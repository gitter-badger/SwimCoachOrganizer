package ch.tiim.sco.database.model;

import javax.persistence.Column;
import java.time.LocalDate;

public class TeamMember implements Model {
    @Column(name = "member_id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birthday")
    private LocalDate birthDay;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_private")
    private String phonePrivate;
    @Column(name = "phone_work")
    private String phoneWork;
    @Column(name = "phone_mobile")
    private String phoneMobile;
    @Column(name = "email")
    private String email;
    @Column(name = "license")
    private String license;
    @Column(name = "is_female")
    private boolean isFemale;
    @Column(name = "notes")
    private String notes;

    public TeamMember(int id, String firstName, String lastName, LocalDate birthDay, String address, String phonePrivate, String phoneWork, String phoneMobile, String email, String license, boolean isFemale, String notes) {
        this(firstName, lastName, birthDay, address, phonePrivate, phoneWork, phoneMobile, email, license, isFemale, notes);
        this.id = id;
    }

    public TeamMember(String firstName, String lastName, LocalDate birthDay, String address, String phonePrivate, String phoneWork, String phoneMobile, String email, String license, boolean isFemale, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.address = address;
        this.phonePrivate = phonePrivate;
        this.phoneWork = phoneWork;
        this.phoneMobile = phoneMobile;
        this.email = email;
        this.license = license;
        this.isFemale = isFemale;
        this.notes = notes;
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
        return firstName + " " + lastName + " [" + (isFemale ? "f" : "m") + "]";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhonePrivate() {
        return phonePrivate;
    }

    public void setPhonePrivate(String phonePrivate) {
        this.phonePrivate = phonePrivate;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public void setPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setIsFemale(boolean isFemale) {
        this.isFemale = isFemale;
    }
}
