package board.JPAboard.repository;

import board.JPAboard.domain.User;
import board.JPAboard.domain.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLoginId(String loginId); //id로 user 찾기 위해
    Page<User> findAllByNicknameContains(String nickname, PageRequest pageRequest); //ADMIN이 유저 검색 시 사용
    Boolean existsByLoginId(String loginId); //회원가입 시 중복 체크용으로 사용
    Boolean existsByNickname(String nickname); //회원가입 시 중복 체크용으로 사용
    Long countAllByUserRole(UserRole userRole); //해당 등급을 가진 유저가 몇명 있는지



}
