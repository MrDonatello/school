package net.thumbtack.school.spring.dao;

import net.thumbtack.school.spring.models.Author;
import net.thumbtack.school.spring.models.Assessment;
import net.thumbtack.school.spring.models.Book;

import java.util.List;

public interface BookDao {

    void insertBook(Book book);

    void deleteBook(String isbn);

    List<Book> getBooksAuthor(Author author);

    boolean checkExistence(String isbn);

    void addAssessment(Assessment assessment, String userId, String isbn);
}
