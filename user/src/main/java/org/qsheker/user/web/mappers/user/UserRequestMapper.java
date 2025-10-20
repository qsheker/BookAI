package org.qsheker.user.web.mappers.user;

import org.mapstruct.Mapper;
import org.qsheker.user.models.entity.User;
import org.qsheker.user.web.dto.user.UserRequestDto;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    UserRequestDto toDto(User user);

    User toEntity(UserRequestDto dto);
}
