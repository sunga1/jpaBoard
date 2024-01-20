package board.JPAboard.dto;

import board.JPAboard.domain.Board;
import board.JPAboard.domain.Comment;
import board.JPAboard.domain.User;
import lombok.Data;

@Data
public class CommentCreateRequest {
    private String body;
    public Comment toEntity(Board board, User user){
        return Comment.builder()
                .user(user)
                .board(board)
                .body(body)
                .build();
    }
}
