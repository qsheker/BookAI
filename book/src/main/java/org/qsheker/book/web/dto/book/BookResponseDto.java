package org.qsheker.book.web.dto.book;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
