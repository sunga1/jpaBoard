package board.JPAboard.repository;

import board.JPAboard.domain.UploadImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadImageRepository extends JpaRepository<UploadImage,Long> {
}
