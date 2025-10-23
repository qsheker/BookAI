package org.qsheker.aiservice.clients;

import org.qsheker.aiservice.web.dto.Book.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "book-service", url = "http://localhost:8082")
public interface BookServiceClient {
    @GetMapping("/api/v1/books/{id}")
    Book getBookById(@PathVariable Long id);

    @GetMapping("/api/v1/books/batch")
    List<Book> getBooksBatch(@RequestParam("ids") List<Long> bookIds);

    @GetMapping("/api/v1/books")
    List<Book> getAllBooks();
}
