package org.qsheker.aiservice.clients;

import org.qsheker.aiservice.web.dto.User.User;
import org.qsheker.aiservice.web.dto.User.UserBookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserServiceClient {
    @GetMapping("/api/v1/users/{userId}")
    User getUserById(@PathVariable("userId") Long id);

    @GetMapping("/api/v1/users/books/{userId}")
    List<UserBookResponse> getUserBooks(@PathVariable("userId") Long userId);
}
