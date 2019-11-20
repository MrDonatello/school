package net.thumbtack.school.spring.dao;

public interface CommentDao {

    String addComment(String isbn, String comment);

    void deleteComment(String isbn, String commentId);
}
