package net.thumbtack.school.spring;

import net.thumbtack.school.spring.daoImpl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.thumbtack.school.spring.service.BookService;
import net.thumbtack.school.spring.utils.*;

@Configuration
public class Configure {

    @Bean
    public BookService bookService(BookWarehouse warehouse, IsbnValidator isbnValidator, ImageConverter imageConverter, ImageDaoImpl imageDao, AuthorDaoImpl authorDao, BookDaoImpl bookDao, CommentDaoImpl commentDao) {
        return new BookService(warehouse, isbnValidator, imageConverter, imageDao, authorDao, bookDao, commentDao);
    }

    @Bean
    public IsbnValidator isbnValidator() {
        return new IsbnValidator();
    }

    @Bean
    public ImageConverter imageConverter() {
        return new ImageConverter();
    }

    @Bean
    public ImageDaoImpl imageDao() {
        return new ImageDaoImpl();
    }

    @Bean
    public AuthorDaoImpl authorDao() {
        return new AuthorDaoImpl();
    }

    @Bean
    public BookDaoImpl bookDao() {
        return new BookDaoImpl();
    }

    @Bean
    public CommentDaoImpl commentDao() {
        return new CommentDaoImpl();
    }

    @Bean
    public BookWarehouse warehouse(BookDaoImpl bookDao) {
        return new BookWarehouse(bookDao);
    }
}
