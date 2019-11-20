package net.thumbtack.school.spring.daoImpl;

import net.thumbtack.school.spring.dao.BookDao;
import net.thumbtack.school.spring.database.DataBase;
import net.thumbtack.school.spring.models.Assessment;
import net.thumbtack.school.spring.models.Author;
import net.thumbtack.school.spring.models.Book;

import java.util.LinkedList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    public void insertBook(Book book) {

        DataBase.bookMap.put(book.getIsbn(), book);
    }

    public void deleteBook(String isbn) {

        DataBase.bookMap.remove(isbn);
    }

    public List<Book> getBooksAuthor(Author author) {

        List<Book> books = new LinkedList<>();

        DataBase.bookMap.values().forEach(book -> {
            if (book.getAuthor().getLastName().equals(author.getLastName()) && book.getAuthor().getName().equals(author.getName())) {
                books.add(book);
            }
        });
        return books;
    }

    public boolean checkExistence(String isbn) {

        return DataBase.bookMap.get(isbn) != null;
    }

    public void addAssessment(Assessment assessment, String userId, String isbn) {

        DataBase.bookMap.get(isbn).getAssessment().putIfAbsent(userId, assessment);
    }


}
