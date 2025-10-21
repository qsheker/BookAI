package org.qsheker.book.web.mappers.book;

import org.mapstruct.Mapper;
import org.qsheker.book.models.entity.Book;
import org.qsheker.book.web.dto.book.BookRequestDto;

@Mapper(componentModel = "spring")
public interface BookRequestMapper {
    BookRequestDto toDto(Book book);

    Book toEntity(BookRequestDto dto);
}
