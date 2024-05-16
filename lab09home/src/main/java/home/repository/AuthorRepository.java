package home.repository;

import home.entities.Author;

import javax.persistence.EntityManager;

public class AuthorRepository extends AbstractRepository<Author> {
    public AuthorRepository(){
        name="Author";
    }
}
