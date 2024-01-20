package board.JPAboard.dto;

import board.JPAboard.domain.Board;
import board.JPAboard.domain.BoardCategory;
import board.JPAboard.domain.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardCreateRequest {
    private String title;
    private String body;
    private MultipartFile uploadImage;

    public Board toEntity(BoardCategory category, User user){
        return Board.builder()
                .user(user)
                .category(category)
                .title(title)
                .body(body)
                .likeCnt(0)
                .commentCnt(0)
                .build();
    }

}
