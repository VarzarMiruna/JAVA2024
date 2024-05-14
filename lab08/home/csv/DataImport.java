package home.csv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import home.model.Book;
import home.model.Genre;
import home.dao.AuthorDAO;
import home.dao.BookDAO;
import home.dao.GenreDAO;
import homework.model.Author;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataImport {
    public void insert(String filePath) throws SQLException {
        try (BufferedReader lineReader = new BufferedReader(new FileReader(filePath))) {
            lineReader.readLine(); // Skip header line
            String line;
            var authors = new AuthorDAO();
            var genres = new GenreDAO();
            var books = new BookDAO();
            int i,j;
            while ((line = lineReader.readLine()) != null) {
                try {
                    String[] columns = line.split(",");
                    int releaseYear = Integer.parseInt(columns[1].trim());
                    for(i=3;i<columns.length;i++){
                        if(columns[i].charAt(0)==' ')
                            columns[2]=columns[2]+columns[i];
                        else break;
                    }
                    String title = columns[2].trim();
                    for(j=i+1;j<columns.length;j++){
                        if(columns[j].charAt(0)==' ')
                            columns[i]=columns[i]+columns[j];
                        else break;
                    }
                    String authorName = columns[3].trim();
                    String language = columns[4].trim();
                    int numberOfPages = Integer.parseInt(columns[5].trim());
                    String genreName = columns[6].trim().replace("\"", "");

                    Book book = new Book(releaseYear, title, authorName, List.of(genreName), numberOfPages, language);
                    books.create(book);
                } catch (NumberFormatException e) {
                    Logger.getLogger(DataImport.class.getName()).log(Level.SEVERE, "Skipping invalid data line: " + line, e);
                    continue;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DataImport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
