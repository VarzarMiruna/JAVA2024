package home.repository;
import home.entities.AbstractEntity;
import home.singleton.EntityManagerFactorySingleton;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class AbstractRepository <T extends AbstractEntity>{
    public String name;
    public EntityManagerFactory emf = EntityManagerFactorySingleton.getEntityManagerFactory();
    public EntityManager em = emf.createEntityManager();
    public void create(T line) throws SQLException{
        try{
            em.getTransaction().begin();
            em.persist(line);
            em.getTransaction().commit();
        } catch (Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }
    }
    public List<T> findAll() throws SQLException{
        return (List<T>)em.createNamedQuery(name+".findAll").getResultList();
    }
    public List<T> findByName(String name) throws SQLException{
        return (List<T>)em.createNamedQuery(this.name+".findByName").setParameter(1,name).getResultList();
    }
    public T findById(int id) throws SQLException{
        return (T)em.createNamedQuery(name+".findById").setParameter(1,id).getSingleResult();
    }
}