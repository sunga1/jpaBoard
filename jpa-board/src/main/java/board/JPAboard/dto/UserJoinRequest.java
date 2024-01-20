package board.JPAboard.dto;

import board.JPAboard.domain.User;
import board.JPAboard.domain.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserJoinRequest {
    private String loginId;
    private String password;
    private String passwordCheck;
    private String nickname;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .nickname(nickname)
                .userRole(UserRole.BRONZE)
                .createdAt(LocalDateTime.now())
                .receivedLikeCnt(0)
                .build();
    }
}
