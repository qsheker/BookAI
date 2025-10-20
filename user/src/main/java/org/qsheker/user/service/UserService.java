package org.qsheker.user.service;

import org.qsheker.user.models.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    User create(User user);

    User findById(Long userId);

    User update(User user);

    void delete(Long userId);
}
