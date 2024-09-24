package be.bstorm.demowebappjakartaee.models.book;

import be.bstorm.demowebappjakartaee.entities.Book;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class BookShortDTO {

    private String isbn;
    private String title;
    private String authorName;
    private LocalDate publicationDate;

    public BookShortDTO(String isbn, String title, String authorName, LocalDate publicationDate) {
        this.isbn = isbn;
        this.title = title;
        this.authorName = authorName;
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Date getPublicationDateAsUtilDate() {
        return Date.from(this.publicationDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static BookShortDTO fromEntity(Book book){
        return new BookShortDTO(
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor().getFirstName() + ' ' + book.getAuthor().getLastName(),
                book.getPublicationDate()
        );
    }

    @Override
    public String toString() {
        return "BookShortDTO{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookShortDTO that = (BookShortDTO) o;
        return Objects.equals(isbn, that.isbn) && Objects.equals(title, that.title) && Objects.equals(authorName, that.authorName) && Objects.equals(publicationDate, that.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, authorName, publicationDate);
    }
}
