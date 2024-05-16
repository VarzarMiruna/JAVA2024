package home.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_genre")
@NamedQueries({
        @NamedQuery(name = "BookGenre.findAll", query = "SELECT a FROM BookGenre a"),
        @NamedQuery(name = "BookGenre.findById", query = "SELECT a FROM BookGenre a WHERE a.id = ?1"),
})
public class BookGenre extends AbstractEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    private Genre genre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BookGenre(){}
    public BookGenre(Integer id){
        this.id=id;
    }

    @Override
    public String toString() {
        return "BookGenre{"+ " book='" + book + ", genre='"+ genre + '\'' + '}';
    }
}