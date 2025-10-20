package org.qsheker.user.web.dto.userBooks;

import lombok.Data;

@Data
public class UserBookRequestDto {
    private Long userId;
    private Long bookId;
}
