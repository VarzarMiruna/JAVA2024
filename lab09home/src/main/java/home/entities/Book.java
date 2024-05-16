package home.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "select e from Book e"),
        @NamedQuery(name = "Book.findById", query = "select e from Book e where e.id = ?1"),
        @NamedQuery(name = "Book.findByName", query = "select e from Book e where e.title = ?1")})

public class Book extends AbstractEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publishing_house_id", referencedColumnName = "id")
    private PublishingHouse publishingHouse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Book(){}
    public Book(String title, int releaseYear, Author author,  PublishingHouse publishingHouse){
        this.title=title;
        this.releaseYear=releaseYear;
        this.author=author;
        this.publishingHouse=publishingHouse;
    }

    @Override
    public String toString() {
        return "Book{"+ " title='" + title + '\'' + '}';
    }
}
