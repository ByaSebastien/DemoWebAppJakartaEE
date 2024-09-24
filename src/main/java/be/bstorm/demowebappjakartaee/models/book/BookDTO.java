package be.bstorm.demowebappjakartaee.models.book;

import be.bstorm.demowebappjakartaee.entities.Book;
import be.bstorm.demowebappjakartaee.models.author.AuthorDTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class BookDTO {

    private String isbn;
    private String title;
    private String Description;
    private LocalDate publicationDate;
    public AuthorDTO author;

    public BookDTO(String isbn, String title, String description, LocalDate publicationDate, AuthorDTO author) {
        this.isbn = isbn;
        this.title = title;
        Description = description;
        this.publicationDate = publicationDate;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public Date getPublicationDateAsUtilDate() {
        return Date.from(this.publicationDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static BookDTO fromEntity(Book book) {
        return new BookDTO(
                book.getIsbn(),
                book.getTitle(),
                book.getDescription(),
                book.getPublicationDate(),
                AuthorDTO.fromEntity(book.getAuthor())
        );
    }
}
