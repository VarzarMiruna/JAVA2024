package home.repository;

import home.entities.BookGenre;

import javax.persistence.EntityManager;

public class BookGenreRepository extends AbstractRepository<BookGenre> {
    public BookGenreRepository(){
        name="BookGenre";
    }
}
