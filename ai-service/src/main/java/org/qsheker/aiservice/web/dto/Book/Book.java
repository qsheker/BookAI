package org.qsheker.aiservice.web.dto.Book;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
