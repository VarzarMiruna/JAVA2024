package home.repository;

import home.entities.Genre;

import javax.persistence.EntityManager;

public class GenreRepository extends AbstractRepository<Genre> {

    public GenreRepository(){
        name="Genre";
    }
}
