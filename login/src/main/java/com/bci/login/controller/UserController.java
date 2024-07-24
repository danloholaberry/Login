package com.bci.login.controller;

import com.bci.login.dto.UserDetailResponse;
import com.bci.login.dto.UserRequest;
import com.bci.login.dto.UserResponse;
import com.bci.login.model.User;
import com.bci.login.service.UserService;
import com.bci.login.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Api(value = "Login", description = "Api encargada de registrar y consultar usuarios")
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ApiOperation("Registrar un nuevo usuario")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phones(userRequest.getPhones())
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(JwtUtil.generateToken(userRequest.getEmail()))
                .isActive(true).build();

        User savedUser = userService.saveUser(user);

        UserResponse userResponse = new UserResponse(
                savedUser.getId(),
                savedUser.getCreated(),
                savedUser.getLastLogin(),
                savedUser.getToken(),
                savedUser.isActive()
        );

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    @ApiOperation("Consultar usuario mediante su UUID")
    public ResponseEntity<UserDetailResponse> getUserById(@PathVariable UUID id) {
        Optional<User> optionalUser = userService.getUserById(id);

        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();
        UserDetailResponse userResponse = new UserDetailResponse(
                user.getId(),
                user.getCreated(),
                user.getLastLogin(),
                user.getToken(),
                user.isActive(),
                user.getName(),
                user.getEmail(),
                user.getPhones()
        );

        return ResponseEntity.ok(userResponse);
    }
}
