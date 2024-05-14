package home.model;

import home.model.AbstractEntity;
/*
 *
 * @author Varzar Alina-Miruna
 */

public class Author extends AbstractEntity {
    private int id;
    private String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }@Override
    public String toString() {
        return "Author{" +
                "authorid=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

