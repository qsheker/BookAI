package org.qsheker.book.web.mappers.book;

import org.mapstruct.Mapper;
import org.qsheker.book.models.entity.Book;
import org.qsheker.book.web.dto.book.BookResponseDto;

@Mapper(componentModel = "spring")
public interface BookResponseMapper {
    BookResponseDto toDto(Book book);

    Book toEntity(BookResponseDto dto);
}
