package home.entities;

import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
        @NamedQuery(name = "Author.findById", query = "SELECT a FROM Author a WHERE a.id = ?1"),
        @NamedQuery(name = "Author.findByName", query = "SELECT a FROM Author a WHERE a.name = ?1")})
@Table(name = "authors")
public class Author extends AbstractEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author() { }

    public Author(String name) {
        this.name = name;
    }

    public List<Book> getbooks() {
        return books;
    }

    public void setbooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{"+ " name='" + name + '\'' + '}';
    }
}
