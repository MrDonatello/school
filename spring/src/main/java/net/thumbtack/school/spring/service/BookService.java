package net.thumbtack.school.spring.service;

import net.thumbtack.school.spring.daoImpl.*;
import net.thumbtack.school.spring.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.thumbtack.school.spring.utils.*;

import java.awt.Image;
import java.util.List;

@Service
public class BookService {

    private final BookWarehouse warehouse;
    private IsbnValidator isbnValidator;
    private ImageConverter imageConverter;
    private ImageDaoImpl imageDao;
    private AuthorDaoImpl authorDao;
    private BookDaoImpl bookDao;
    private CommentDaoImpl commentDao;

    @Autowired
    public BookService(BookWarehouse warehouse, IsbnValidator isbnValidator, ImageConverter imageConverter, ImageDaoImpl imageDao, AuthorDaoImpl authorDao, BookDaoImpl bookDao, CommentDaoImpl commentDao) {
        this.warehouse = warehouse;
        this.isbnValidator = isbnValidator;
        this.imageConverter = imageConverter;
        this.imageDao = imageDao;
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.commentDao = commentDao;
    }

    public void addBook(Book book, Image image) {
        if (book.getAuthor().getName() != null && book.getAuthor().getLastName() != null && image != null && isbnValidator.ISBN_Validation(book.getIsbn())) {
            Image image1 = imageConverter.convert(image);
            imageDao.addImage(book.getIsbn(), image1);
            bookDao.insertBook(book);
            authorDao.addAuthor(new Author(book.getAuthor().getName(), book.getAuthor().getLastName()));
        }
    }

    public void removeBook(Book book) {
        bookDao.deleteBook(book.getIsbn());
        imageDao.deleteImage(book.getIsbn());
    }

    public String addComment(Book book, String comment) {

        return commentDao.addComment(book.getIsbn(), comment);
    }

    public void deleteComment(Book book, String commentId) {

        commentDao.deleteComment(book.getIsbn(), commentId);
    }

    public List<Book> getAuthorBooks(Author author) {

        return bookDao.getBooksAuthor(author);
    }

    public boolean checkExistence(Book book) {

        return warehouse.checkExistence(book.getIsbn());
    }

    public void addAssessment(Assessment assessment, String userId, Book book) {

        bookDao.addAssessment(assessment, userId, book.getIsbn());
    }
}
