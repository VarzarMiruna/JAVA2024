package home.entities;

import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name = "publishing_houses")
@NamedQueries({
        @NamedQuery(name = "PublishingHouse.findAll", query = "SELECT g FROM PublishingHouse g"),
        @NamedQuery(name = "PublishingHouse.findById", query = "SELECT g FROM PublishingHouse g WHERE g.id = ?1"),
        @NamedQuery(name = "PublishingHouse.findByName", query = "SELECT g FROM PublishingHouse g WHERE g.name = ?1")})
public class PublishingHouse extends AbstractEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL)
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public PublishingHouse(){}
    public PublishingHouse(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Publishing House{"+ " name='" + name + '\'' + '}';
    }
}
