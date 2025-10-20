package org.qsheker.user.web.mappers.user;

import org.mapstruct.Mapper;
import org.qsheker.user.models.entity.User;
import org.qsheker.user.web.dto.user.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponseDto toDto(User user);

    User toEntity(UserResponseDto dto);
}
