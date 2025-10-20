package org.qsheker.user.web.dto.userBooks;

import lombok.Data;

@Data
public class UserBookResponseDto {
    private Long id;
    private Long userId;
    private Long bookId;
}
