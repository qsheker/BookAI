package org.qsheker.user.web.mappers.userBooks;

import org.mapstruct.Mapper;
import org.qsheker.user.models.entity.UserBook;
import org.qsheker.user.web.dto.userBooks.UserBookResponseDto;

@Mapper(componentModel = "spring")
public interface UserBookResponseMapper {
    UserBookResponseDto toDto(UserBook userBook);

    UserBook toEntity(UserBookResponseDto dto);
}
