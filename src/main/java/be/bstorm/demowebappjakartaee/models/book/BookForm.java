package be.bstorm.demowebappjakartaee.models.book;

import be.bstorm.demowebappjakartaee.entities.Book;
import be.bstorm.demowebappjakartaee.models.author.AuthorForm;

import java.time.LocalDate;
import java.util.Objects;

public class BookForm {

    private String isbn;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private AuthorForm author;

    public BookForm(String isbn, String title, String description, LocalDate publicationDate, AuthorForm author) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
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
        return description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public AuthorForm getAuthor() {
        return author;
    }

    public Book toEntity(){
        return new Book(isbn, title, description, publicationDate, author.toEntity());
    }

    @Override
    public String toString() {
        return "BookForm{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate=" + publicationDate +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookForm bookForm = (BookForm) o;
        return Objects.equals(isbn, bookForm.isbn) && Objects.equals(title, bookForm.title) && Objects.equals(description, bookForm.description) && Objects.equals(publicationDate, bookForm.publicationDate) && Objects.equals(author, bookForm.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, description, publicationDate, author);
    }
}
