package net.thumbtack.school.spring.dao;

import java.awt.*;

public interface ImageDao {

    void addImage(String isbn, Image image);

    void deleteImage(String isbn);
}
