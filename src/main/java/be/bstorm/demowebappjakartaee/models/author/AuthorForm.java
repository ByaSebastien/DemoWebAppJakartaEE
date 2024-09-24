package be.bstorm.demowebappjakartaee.models.author;

import be.bstorm.demowebappjakartaee.entities.Author;

import java.time.LocalDate;
import java.util.Objects;

public class AuthorForm {

    private String firstName;
    private String lastName;
    private String surname;
    private LocalDate birthDate;

    public AuthorForm(String firstName, String lastName, String surname, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Author toEntity(){
        return new Author(firstName, lastName, surname, birthDate);
    }

    @Override
    public String toString() {
        return "AuthorForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorForm that = (AuthorForm) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(surname, that.surname) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, surname, birthDate);
    }
}
