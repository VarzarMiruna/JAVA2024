package home;

import home.csv.DataImport;
import home.dao.BookDAO;
import home.model.Book;
import home.dao.AuthorDAO;
import home.dao.GenreDAO;
import home.model.Author;
import home.model.Genre;
import home.util.ConnectionDB;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Connection conn = ConnectionDB.getConnection();
        if (conn != null)
            System.out.println("\n-----Am facut conexiunea------");
        else
            System.out.println("Nu am facut conexiunea");

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

        /*ConnectionDB.getConnection().commit();

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
        }*/

        DataImport newData = new DataImport();
        System.out.println("\n-----------Din book.csv: ------------\n");
        newData.readData();

    }
}

