package org.qsheker.user.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.qsheker.user.models.entity.UserBook;
import org.qsheker.user.repository.UserBookRepository;
import org.qsheker.user.service.UserBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBookServiceImpl implements UserBookService {
    private final UserBookRepository userBookRepository;

    public UserBookServiceImpl(UserBookRepository userBookRepository){
        this.userBookRepository = userBookRepository;
    }

    @Override
    public UserBook create(UserBook userBook) {
        if(userBookRepository.existsUserBookByBookIdAndUserId(userBook.getUserId(), userBook.getBookId())){
            throw new RuntimeException("User already has this book!");
        }
        return userBookRepository.save(userBook);
    }

    @Override
    public List<UserBook> findAll() {
        return userBookRepository.findAll();
    }

    @Override
    public void delete(Long userId, Long bookId) {
        UserBook userBook = userBookRepository.findByUserIdAndBookId(userId, bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Relation not found with user_id: " + userId + " and book_id: " + bookId
                ));
        userBookRepository.delete(userBook);
    }

    @Override
    public List<UserBook> getUsersBookById(Long id) {
        return userBookRepository.findAllByUserId(id);
    }
}
