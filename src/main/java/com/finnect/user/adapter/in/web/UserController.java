package com.finnect.user.adapter.in.web;

import com.finnect.common.Response;
import com.finnect.user.adapter.in.web.request.SignupRequest;
import com.finnect.user.application.port.in.SignupUseCase;
import com.finnect.user.application.port.in.command.CreateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final SignupUseCase signupUseCase;

    @Autowired
    public UserController(SignupUseCase signupUseCase) {
        this.signupUseCase = signupUseCase;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(new Response(
                201,
                signupUseCase.signup(
                        CreateUserCommand.builder()
                                .username(request.username())
                                .password(request.password())
                                .email(request.email())
                                .firstName(request.firstName())
                                .lastName(request.lastName())
                                .build()
                )
        ));
    }
}
