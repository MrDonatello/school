package net.thumbtack.school.spring;

import net.thumbtack.school.spring.models.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import net.thumbtack.school.spring.service.BookService;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

@ComponentScan()
public class Main {

    public static void main(String[] args) {

        Book book1 = new Book("1", new Author("Фёдор", "Достоевский"));
        Book book2 = new Book("2", new Author("Николай", "Гоголь"));
        Book book3 = new Book("3", new Author("Лев", "Толстой"));
        Book test1 = new Book(null, new Author("Лев", "Толстой"));

        try {
            Image image = ImageIO.read(new File("1.png"));
            System.out.println("start");
            ApplicationContext context = new AnnotationConfigApplicationContext(Configure.class);
            BookService bookService = context.getBean(BookService.class);

            bookService.addBook(book1, image);
            bookService.addBook(book2, image);
            bookService.addBook(book3, image);
            bookService.addBook(test1, image);
            bookService.addComment(book1, "comment1");
            String commentId2 = bookService.addComment(book2, "comment2");
            String commentId3 = bookService.addComment(book3, "comment3");
            bookService.removeBook(book1);
            bookService.deleteComment(book2, commentId2);
            bookService.deleteComment(book3, commentId3);
            bookService.addAssessment(Assessment.FIVE_STARS, "123", book2);
            bookService.addAssessment(Assessment.ONE_STAR, "123", book2);

            bookService.addBook(new Book("4", new Author("Николай", "Гоголь")), image);
            List<Book> books = bookService.getAuthorBooks(new Author("Николай", "Гоголь"));
            System.out.println(bookService.checkExistence(books.get(0)));
            System.out.println("Stop application");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
