package com.finnect.user.adapter.in.web;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.user.adapter.in.web.request.SignupRequest;
import com.finnect.user.application.port.in.SignupUseCase;
import com.finnect.user.application.port.in.ReissueUseCase;
import com.finnect.user.application.port.in.command.ReissueCommand;
import com.finnect.user.application.port.in.command.SignupCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResult<Object>> signup(@RequestBody SignupRequest request) {
        SignupCommand command = SignupCommand.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();

        signupUseCase.signup(command);

        return ResponseEntity.ok(ApiUtils.success(
                HttpStatus.CREATED,
                null
        ));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/reissue")
    public ResponseEntity<ApiResult<String>> reissue(@CookieValue("Refresh") String refreshToken) {
        ReissueCommand command = ReissueCommand.builder()
                .refreshToken(refreshToken)
                .build();

        String accessToken = reissueUseCase.reissue(command);

        return ResponseEntity.ok(ApiUtils.success(
                HttpStatus.CREATED,
                accessToken
        ));
    }
}
