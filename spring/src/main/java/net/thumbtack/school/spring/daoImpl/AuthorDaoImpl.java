package net.thumbtack.school.spring.daoImpl;

import net.thumbtack.school.spring.dao.AuthorDao;
import net.thumbtack.school.spring.database.DataBase;
import net.thumbtack.school.spring.models.Author;

public class AuthorDaoImpl implements AuthorDao {

    public void addAuthor(Author author) {

        DataBase.authorMap.putIfAbsent(author.getLastName().concat(author.getName()), author);
    }
}
