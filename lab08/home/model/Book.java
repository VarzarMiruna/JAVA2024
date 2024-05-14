package home.model;

/*
 *
 * @author Varzar Alina-Miruna
 */
import java.util.List;

public class Book extends AbstractEntity {

    private int publicationDate;
    private String title;
    private String language;
    private int numberOfPages;
    private String author;
    private List<String> genres;

    public Book(int publicationDate, String title, String author, List<String> genres, int numberOfPages, String language) {
        this.publicationDate = publicationDate;
        this.title = title;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.genres = genres;
    }

    public int getNumberOfPages() {

        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {

        this.numberOfPages = numberOfPages;
    }
    public int getPublicationDate() {

        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {

        this.publicationDate = publicationDate;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }


    public List<String> getGenres() {

        return genres;
    }

    public void setGenres(List<String> genres) {

        this.genres = genres;
    }

    public String getLanguage() {

        return language;
    }

    public void setLanguage(String language) {

        this.language =language;
    }

    @Override
    public String toString() {
        return " Book{" +
                "publicationDate=" + publicationDate +
                ", title='" + title +
                ", author='" + author +
                ", genres=" + genres +
                ", language=" + language +
                ", numberOfPages=" + numberOfPages +  '\'' +
                '}';
    }
}





