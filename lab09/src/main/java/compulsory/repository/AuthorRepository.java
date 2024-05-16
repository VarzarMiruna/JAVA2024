package compulsory.repository;

import compulsory.entities.Book;
import compulsory.entities.Author;
import compulsory.singleton.EntityManagerFactorySingleton;

import javax.persistence.*;
import java.util.List;

public class AuthorRepository {
    private EntityManager em =
            EntityManagerFactorySingleton.getEntityManagerFactory().createEntityManager();


    public Author create(Author author) {
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        return author;
    }


    public Author findById(Integer id) {
        return em.find(Author.class, id);
    }

    public List<Author> findByName(String name) {
        var query = em.createNamedQuery("Author.findByName", Author.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }



    /*public Author findByName(String name){
        return (Author)em.createNamedQuery("Author.findByName", Author.class).setParameter(1,name).getSingleResult();
    }

    public Author findById(int id){
        return (Author)em.createNamedQuery("Author.findById", Author.class).setParameter(1,id).getSingleResult();
    }*/

}
