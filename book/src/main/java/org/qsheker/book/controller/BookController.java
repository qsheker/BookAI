package org.qsheker.book.controller;

import org.qsheker.book.service.BookService;
import org.qsheker.book.models.entity.Book;
import org.qsheker.book.web.dto.book.BookRequestDto;
import org.qsheker.book.web.dto.book.BookResponseDto;
import org.qsheker.book.web.mappers.book.BookRequestMapper;
import org.qsheker.book.web.mappers.book.BookResponseMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookResponseMapper bookResponseMapper;
    private final BookRequestMapper bookRequestMapper;
    private final BookService bookService;

    public BookController(BookResponseMapper bookResponseMapper, BookRequestMapper bookRequestMapper, BookService bookService) {
        this.bookResponseMapper = bookResponseMapper;
        this.bookRequestMapper = bookRequestMapper;
        this.bookService = bookService;
    }

    @PostMapping
    public BookResponseDto createBook(@RequestBody BookRequestDto bookRequest) {
        Book book = bookRequestMapper.toEntity(bookRequest);
        Book savedBook = bookService.save(book);
        return bookResponseMapper.toDto(savedBook);
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookResponseDto response = bookResponseMapper.toDto(book);
        return response;
    }

    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookService.findAll();
        List<BookResponseDto> response = books.stream()
                .map(bookResponseMapper::toDto)
                .collect(Collectors.toList());
        return response;
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(
            @PathVariable Long id,
            @RequestBody BookRequestDto bookRequest) {
        Book existingBook = bookService.getBookById(id);

        Book bookToUpdate = bookRequestMapper.toEntity(bookRequest);
        bookToUpdate.setId(id);
        bookToUpdate.setCreatedAt(existingBook.getCreatedAt());

        Book updatedBook = bookService.update(bookToUpdate);
        BookResponseDto response = bookResponseMapper.toDto(updatedBook);
        return response;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
    @GetMapping ("/batch")
    public List<BookResponseDto> getBookByBatch(@RequestParam List<Long> ids){
        return bookService.getBooksByIds(ids).stream().
                map(book -> bookResponseMapper.toDto(book))
                .toList();
    }
}
