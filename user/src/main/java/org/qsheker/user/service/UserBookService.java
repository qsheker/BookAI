package org.qsheker.user.service;

import org.qsheker.user.models.entity.UserBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserBookService {
    UserBook create(UserBook userBook);

    List<UserBook> findAll();

    void delete(Long userId, Long bookId);

}
