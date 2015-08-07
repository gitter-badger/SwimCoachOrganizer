package ch.tiim.trainingmanager.database.model;

import java.time.LocalDate;

public class TeamMember {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private String address;
    private String phonePrivate;
    private String phoneWork;
    private String phoneMobile;
    private String email;
    private String license;
    private boolean isFemale;
    private String notes;


    public TeamMember(int id, String firstName, String lastName, LocalDate birthDay, String address, String phonePrivate, String phoneWork, String phoneMobile, String email, String license, boolean isFemale, String notes) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setIsFemale(boolean isFemale) {
        this.isFemale = isFemale;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " [" + (isFemale?"f":"m") + "]";
    }
}
