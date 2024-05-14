package home;

import home.csv.DataImport;
import home.dao.BookDAO;
import home.model.Book;
import home.dao.AuthorDAO;
import home.dao.GenreDAO;
import home.model.Author;
import home.model.Genre;
import home.util.ConnectionDB;


import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            ConnectionDB.getConnection().setAutoCommit(false);

            var authors = new AuthorDAO();
            var genres = new GenreDAO();
            var books = new BookDAO();

            authors.create(new Author("Miruna"));
            authors.create(new Author("Abel"));
            authors.create(new Author("Alina"));

            genres.create(new Genre("Fiction"));
            genres.create(new Genre("Non-Fiction"));
            genres.create(new Genre("Romantic"));
            genres.create(new Genre("Education"));

            books.create(new Book(2003, "Gradina Minunata", "Miruna", List.of("Romantic"), 203, "romana"));
            books.create(new Book(2010, "Gradina lui Ion", "Abel", List.of("Non-Fiction", "Romantic"), 1020, "engleza"));
            books.create(new Book(1990, "Zece", "Alina", List.of("Non-Fiction", "Education"), 12, "franceza"));

            ConnectionDB.getConnection().commit();

           Integer author = authors.findByName("Miruna");
            if (author != null) {
                System.out.println("\n-----Author=>find by NAME--------");
                System.out.println("Author: " + author);
            } else {
                System.out.println("\n----erorr: no author by name--");
            }

            Genre genre1 = genres.findById(1);
            if (genre1 != null) {
                System.out.println("\n-------Genre=> find by ID------");
                System.out.println("Genre: " + genre1);
            } else {
                System.out.println("\n---erorr: no genre find by id---");
            }

            List<Book> allBooks = books.findAll();
            System.out.println("\n----------------Books=> ALL ------");
            for (Book book : allBooks) {
                System.out.println(book);
            }


            /*String databasePath = "D:\sem_2\java\LAB\lab08\src\main\java\home\files\booklist.csv";
            var inserter = new DataImport();
            inserter.insert(databasePath);
            System.out.println(books.findAll());
            ConnectionDB.getConnection().commit();*/
        } catch (SQLException e) {
            System.err.println(e);
            ConnectionDB.rollback();
        }
    }
}

