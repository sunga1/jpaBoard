package board.JPAboard.dto;

import board.JPAboard.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String nowPassword;
    private String newPassword;
    private String nickname;
    private String loginId;
    private String newPasswordCheck;

    public static UserDto of(User user){
        return UserDto.builder()
                .loginId(user.getLoginId())
                .nickname(user.getNickname())
                .build();
    }
}
