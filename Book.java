package model;

public class Book {
    private int id;
    private String title;
    private String isbn;
    private String author;
    private boolean available;

    public Book(int id, String title, String isbn, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.available = available;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
