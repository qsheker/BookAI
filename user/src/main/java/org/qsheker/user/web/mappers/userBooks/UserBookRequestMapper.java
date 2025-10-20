package org.qsheker.user.web.mappers.userBooks;

import org.mapstruct.Mapper;
import org.qsheker.user.models.entity.UserBook;
import org.qsheker.user.web.dto.userBooks.UserBookRequestDto;

@Mapper(componentModel = "spring")
public interface UserBookRequestMapper {
    UserBookRequestDto toDto(UserBook userBook);

    UserBook toEntity(UserBookRequestDto dto);
}
