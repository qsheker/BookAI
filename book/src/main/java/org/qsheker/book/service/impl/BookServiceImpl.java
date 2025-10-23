package org.qsheker.book.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.qsheker.book.service.BookService;
import org.qsheker.book.models.entity.Book;
import org.qsheker.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooksByIds(List<Long> bookIds) {
        return bookRepository.findAllById(bookIds);
    }

    @Override
    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("Book with id: "+id+" not found!")
        );
        return book;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(Book book) {
        if(!bookRepository.existsById(book.getId())){
            throw new EntityNotFoundException("Book with id: "+book.getId()+" not found!");
        }
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("Book with id: "+id+" not found!")
        );
        bookRepository.delete(book);
    }
}
