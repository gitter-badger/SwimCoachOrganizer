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
    @Column(name = "birth_day")
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

    public TeamMember() {
    }

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
        return firstName + " " + lastName + " [" + (isFemale ? "f" : "m") + "]";
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", address='" + address + '\'' +
                ", phonePrivate='" + phonePrivate + '\'' +
                ", phoneWork='" + phoneWork + '\'' +
                ", phoneMobile='" + phoneMobile + '\'' +
                ", email='" + email + '\'' +
                ", license='" + license + '\'' +
                ", isFemale=" + isFemale +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMember that = (TeamMember) o;

        if (isFemale != that.isFemale) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (birthDay != null ? !birthDay.equals(that.birthDay) : that.birthDay != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phonePrivate != null ? !phonePrivate.equals(that.phonePrivate) : that.phonePrivate != null) return false;
        if (phoneWork != null ? !phoneWork.equals(that.phoneWork) : that.phoneWork != null) return false;
        if (phoneMobile != null ? !phoneMobile.equals(that.phoneMobile) : that.phoneMobile != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (license != null ? !license.equals(that.license) : that.license != null) return false;
        return !(notes != null ? !notes.equals(that.notes) : that.notes != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phonePrivate != null ? phonePrivate.hashCode() : 0);
        result = 31 * result + (phoneWork != null ? phoneWork.hashCode() : 0);
        result = 31 * result + (phoneMobile != null ? phoneMobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (license != null ? license.hashCode() : 0);
        result = 31 * result + (isFemale ? 1 : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
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
