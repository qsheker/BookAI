package org.qsheker.user.controller;

import org.qsheker.user.models.entity.User;
import org.qsheker.user.service.UserService;
import org.qsheker.user.web.dto.user.UserRequestDto;
import org.qsheker.user.web.dto.user.UserResponseDto;
import org.qsheker.user.web.mappers.user.UserRequestMapper;
import org.qsheker.user.web.mappers.user.UserResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;

    public UserController(UserService userService, UserResponseMapper userResponseMapper, UserRequestMapper userRequestMapper) {
        this.userService = userService;
        this.userResponseMapper = userResponseMapper;
        this.userRequestMapper = userRequestMapper;
    }

    @GetMapping
    public List<UserResponseDto> findAll(){
        return userService.findAll().stream()
                .map(user -> userResponseMapper.toDto(user))
                .toList();
    }
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto){
        User user = userRequestMapper.toEntity(userRequestDto);
        User created = userService.create(user);
        return userResponseMapper.toDto(created);
    }
    @GetMapping("/{userId}")
    public UserResponseDto findUser(@PathVariable("userId") Long id){
        return userResponseMapper.toDto(userService.findById(id));
    }
    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable("userId") Long id, @RequestBody UserRequestDto userRequestDto){
        User user = userRequestMapper.toEntity(userRequestDto);
        user.setId(id);
        User updated = userService.update(user);
        return userResponseMapper.toDto(updated);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId){
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
