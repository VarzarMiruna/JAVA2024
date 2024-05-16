package home;

import home.entities.*;
import home.repository.*;
import home.util.LoggingUtility;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = LoggingUtility.getLogger();

    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();
        GenreRepository genreRepository = new GenreRepository();
        BookRepository bookRepository = new BookRepository();
        PublishingHouseRepository publishingHouseRepository = new PublishingHouseRepository();

        try {
            long startTime = System.currentTimeMillis();
            Author author = new Author("Liviu Popes");
            authorRepository.create(author);
            Author author1 = new Author("Miruna");
            authorRepository.create(author1);
            Author author2 = new Author("Abel");
            authorRepository.create(author2);
            long endTime = System.currentTimeMillis();
            logger.info("Author creation took " + (endTime - startTime) + " milliseconds");

            startTime = System.currentTimeMillis();
            Author foundAuthor = authorRepository.findByName("Miruna").get(0);
            endTime = System.currentTimeMillis();
            logger.info("Finding author by name took " + (endTime - startTime) + " milliseconds");


            startTime = System.currentTimeMillis();
            Genre genre = new Genre("Romantic");
            genreRepository.create(genre);
            Genre genre1 = new Genre("Fiction");
            genreRepository.create(genre1);
            Genre genre2 = new Genre("Non-Fictional");
            genreRepository.create(genre2);
            endTime = System.currentTimeMillis();
            logger.info("Genre creation took " + (endTime - startTime) + " milliseconds");

            startTime = System.currentTimeMillis();
            Genre foundGenre = genreRepository.findByName("Fiction").get(0);
            endTime = System.currentTimeMillis();
            logger.info("Finding author by name took " + (endTime - startTime) + " milliseconds");


            startTime = System.currentTimeMillis();
            PublishingHouse publishingHouse = new PublishingHouse("Nr1");
            publishingHouseRepository.create(publishingHouse);
            endTime = System.currentTimeMillis();
            logger.info("PublishingHouse creation took " + (endTime - startTime) + " milliseconds");

            startTime = System.currentTimeMillis();
            Book book = new Book("Title1", 1949, author, publishingHouse);
            bookRepository.create(book);
            endTime = System.currentTimeMillis();
            logger.info("Book creation took " + (endTime - startTime) + " milliseconds");

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred in JPA operations", ex);
        }
    }
}
