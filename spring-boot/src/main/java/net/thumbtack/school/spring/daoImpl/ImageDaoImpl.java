package net.thumbtack.school.spring.daoImpl;

import net.thumbtack.school.spring.dao.ImageDao;
import net.thumbtack.school.spring.database.DataBase;

import java.awt.*;

public class ImageDaoImpl implements ImageDao {

    public void addImage(String isbn, Image image) {

        DataBase.imageMap.put(isbn, image);
    }

    public void deleteImage(String isbn) {
        DataBase.imageMap.remove(isbn);
    }
}
