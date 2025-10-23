package org.qsheker.user.repository;

import org.qsheker.user.models.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    Optional<UserBook> findByUserIdAndBookId(Long userId, Long bookId);

    boolean existsUserBookByBookIdAndUserId(Long bookId, Long userId);

    List<UserBook> findAllByUserId(Long userId);
}
