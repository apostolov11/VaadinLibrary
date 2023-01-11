package example.demo.backend.entity;

import example.demo.backend.entity.enums.Genre;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    private String bookName;

    @Column
    private String authorName;

    @Enumerated(EnumType.STRING)
    @Column
    private Genre genre;

    @Column(precision = 5)
    private Integer copies;

    @ManyToOne
    private Person person;

    public Book(String bookName, String authorName, Genre genre, Integer copies, Person person) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.genre = genre;
        this.copies = copies;
        this.person = person;
    }

    public Book() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
