package org.qsheker.book.service;

import org.qsheker.book.models.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book save(Book book);

    Book getBookById(Long id);

    List<Book> findAll();

    Book update(Book book);

    void delete(Long id);
}
