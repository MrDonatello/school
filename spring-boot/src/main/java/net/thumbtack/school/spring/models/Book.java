package net.thumbtack.school.spring.models;

import java.util.HashMap;
import java.util.Map;

public class Book {
    private String isbn;
    private Author author;
    private Map<String, String> comment;
    private Map<String, Assessment> assessment;

    public Book(String isbn, Author author) {
        this.isbn = isbn;
        this.author = author;
        this.comment = new HashMap<>();
        this.assessment = new HashMap<>();
    }

    public String getIsbn() {
        return isbn;
    }

    public Map<String, String> getComment() {
        return comment;
    }

    public Author getAuthor() {
        return author;
    }

    public Map<String, Assessment> getAssessment() {
        return assessment;
    }
}
