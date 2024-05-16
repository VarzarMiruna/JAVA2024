package home.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "genres")
@NamedQueries({
        @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
        @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.id = ?1"),
        @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name = ?1")})

public class Genre extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

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
    public Genre (){}
    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{"+ " name='" + name + '\'' + '}';
    }
}

