package org.qsheker.user.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.qsheker.user.models.entity.User;
import org.qsheker.user.repository.UserRepository;
import org.qsheker.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                ()->new EntityNotFoundException("User with id: "+userId+" not found!")
        );
    }

    @Override
    public User update(User user) {
        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        return userRepository.save(existing);
    }

    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new EntityNotFoundException("User with id: "+userId+" not found!")
        );
        userRepository.delete(user);
    }
}
