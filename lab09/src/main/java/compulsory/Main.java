package compulsory;

import compulsory.repository.AuthorRepository;
import compulsory.entities.Author;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamplePU");

        AuthorRepository authorRepo = new AuthorRepository();

        Author author1 = new Author("Liviu Rebreanu");
        Author author2 = new Author("Mircea Eliade");

        authorRepo.create(author1);
        authorRepo.create(author2);

        Author foundAuthor = authorRepo.findById(author1.getId());
        System.out.println("\nAuthor with ID " + author1.getId() + ": " + foundAuthor);

        System.out.println("\nDupa Nume: " + authorRepo.findByName("Miruna"));


    }
}
