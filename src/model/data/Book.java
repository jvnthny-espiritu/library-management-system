package model.data;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String year;
    private String genre;

    public Book(String title, String isbn, String author, String year, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }

    public String getISBN() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }
}

