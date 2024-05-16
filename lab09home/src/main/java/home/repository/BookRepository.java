package home.repository;

import home.entities.Book;

import javax.persistence.EntityManager;

public class BookRepository extends AbstractRepository<Book>{
    public BookRepository(){
        name="Book";
    }
}
