package home.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import home.model.Book;
import home.model.Genre;
import home.dao.AuthorDAO;
import home.dao.BookDAO;
import home.dao.GenreDAO;
import home.model.Author;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataImport {
    String filePath = "D:\\sem_2\\java\\LAB\\lab08\\src\\main\\java\\home\\files\\books.csv";

    public void readData() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));

        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

        for(CSVRecord csvRecord : csvParser) {
            String title = csvRecord.get("title");
            String author = csvRecord.get("authors");
            String year = csvRecord.get("publication_date");
            String pages = csvRecord.get("num_pages");

            System.out.println("Title: " + title + ", Author: " + author + ", Year: " + year + ", Nr pages: " + pages);
        }
    }
}