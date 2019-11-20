package net.thumbtack.school.spring.daoImpl;

import net.thumbtack.school.spring.dao.CommentDao;
import net.thumbtack.school.spring.database.DataBase;

import java.util.UUID;

public class CommentDaoImpl implements CommentDao {

    public String addComment(String isbn, String comment) {

        UUID idComment = UUID.randomUUID();
        DataBase.bookMap.get(isbn).getComment().put(idComment.toString(), comment);
        return idComment.toString();
    }

    public void deleteComment(String isbn, String commentId) {

        DataBase.bookMap.get(isbn).getComment().remove(commentId);
    }
}
