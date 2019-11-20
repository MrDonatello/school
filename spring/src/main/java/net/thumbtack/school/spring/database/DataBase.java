package net.thumbtack.school.spring.database;

import net.thumbtack.school.spring.models.Author;
import net.thumbtack.school.spring.models.Book;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    public static Map<String, Book> bookMap = new HashMap<>();
    public static Map<String, Author> authorMap = new HashMap<>();
    public static Map<String, Image> imageMap = new HashMap<>();


}
