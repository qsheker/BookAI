package org.qsheker.user.controller;

import org.qsheker.user.service.UserBookService;
import org.qsheker.user.web.dto.userBooks.UserBookRequestDto;
import org.qsheker.user.web.dto.userBooks.UserBookResponseDto;
import org.qsheker.user.web.mappers.userBooks.UserBookRequestMapper;
import org.qsheker.user.web.mappers.userBooks.UserBookResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/books")
public class UserBookController {
    private final UserBookService userBookService;
    private final UserBookResponseMapper userBookResponseMapper;
    private final UserBookRequestMapper userBookRequestMapper;

    public UserBookController(UserBookService userBookService, UserBookResponseMapper userBookResponseMapper, UserBookRequestMapper userBookRequestMapper) {
        this.userBookService = userBookService;
        this.userBookResponseMapper = userBookResponseMapper;
        this.userBookRequestMapper = userBookRequestMapper;
    }

    @GetMapping
    public List<UserBookResponseDto> getAllUserBooks() {
        return userBookService.findAll().stream()
                .map(userBook -> userBookResponseMapper.toDto(userBook))
                .toList()
                ;
    }

    @PostMapping
    public UserBookResponseDto addUserBook(@RequestBody UserBookRequestDto dto) {
        var userBook = userBookRequestMapper.toEntity(dto);
        return userBookResponseMapper.toDto(userBookService.create(userBook));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserBook(
            @RequestParam Long userId,
            @RequestParam Long bookId
    ) {
        userBookService.delete(userId, bookId);
        return ResponseEntity.ok("UserBook relation deleted successfully");
    }
}
