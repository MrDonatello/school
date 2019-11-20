package net.thumbtack.school.spring.utils;

import net.thumbtack.school.spring.daoImpl.BookDaoImpl;

public class BookWarehouse {

    private BookDaoImpl bookDao;

    public BookWarehouse(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }

    public boolean checkExistence(String isbn) {

        return bookDao.checkExistence(isbn);
    }
}
