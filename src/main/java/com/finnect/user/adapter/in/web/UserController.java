package com.finnect.user.adapter.in.web;

import com.finnect.common.Response;
import com.finnect.user.adapter.in.web.request.SignupRequest;
import com.finnect.user.application.port.in.SignupUseCase;
import com.finnect.user.application.port.in.ReissueUseCase;
import com.finnect.user.application.port.in.command.CreateAccessTokenCommand;
import com.finnect.user.application.port.in.command.CreateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final SignupUseCase signupUseCase;

    private final ReissueUseCase reissueUseCase;

    @Autowired
    public UserController(
            SignupUseCase signupUseCase,
            ReissueUseCase reissueUseCase
    ) {
        this.signupUseCase = signupUseCase;
        this.reissueUseCase = reissueUseCase;
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

    @PreAuthorize("permitAll()")
    @GetMapping("/reissue")
    public ResponseEntity<Response> reissue(@CookieValue("Refresh") String refreshToken) {
        return ResponseEntity.ok(new Response(
                201,
                reissueUseCase.reissue(
                        CreateAccessTokenCommand.builder()
                                .refreshToken(refreshToken)
                                .build()
                )
        ));
    }
}
