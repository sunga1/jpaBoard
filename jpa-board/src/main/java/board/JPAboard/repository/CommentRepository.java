package board.JPAboard.repository;

import board.JPAboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByUserLoginId(String loginId);
    List<Comment> findAllByBoardId(long boardId);
}
