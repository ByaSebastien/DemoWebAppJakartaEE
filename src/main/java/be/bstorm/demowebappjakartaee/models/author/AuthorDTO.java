package be.bstorm.demowebappjakartaee.models.author;

import be.bstorm.demowebappjakartaee.entities.Author;

import java.time.LocalDate;
import java.util.Objects;

public class AuthorDTO {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;

    public AuthorDTO(Long id, String name, String surname, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public static AuthorDTO fromEntity(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getFirstName() + ' ' + author.getLastName(),
                author.getSurname(),
                author.getBirthDate()
        );
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(id, authorDTO.id) && Objects.equals(name, authorDTO.name) && Objects.equals(surname, authorDTO.surname) && Objects.equals(birthDate, authorDTO.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthDate);
    }
}
