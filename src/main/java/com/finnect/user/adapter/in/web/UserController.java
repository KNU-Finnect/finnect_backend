package com.finnect.user.adapter.in.web;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.user.adapter.in.web.request.*;
import com.finnect.user.application.port.in.*;
import com.finnect.user.application.port.in.command.*;
import com.finnect.user.state.AccessTokenState;
import com.finnect.user.vo.UserId;
import com.finnect.user.vo.WorkspaceId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final SignupUseCase signupUseCase;
    private final ReissueUseCase reissueUseCase;
    private final SendEmailCodeUseCase sendEmailCodeUseCase;
    private final FindUsernameUseCase findUsernameUseCase;
    private final ResetPasswordUseCase resetPasswordUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    @Autowired
    public UserController(
            SignupUseCase signupUseCase,
            ReissueUseCase reissueUseCase,
            SendEmailCodeUseCase sendEmailCodeUseCase,
            FindUsernameUseCase findUsernameUseCase,
            ResetPasswordUseCase resetPasswordUseCase,
            ChangePasswordUseCase changePasswordUseCase
    ) {
        this.signupUseCase = signupUseCase;
        this.reissueUseCase = reissueUseCase;
        this.sendEmailCodeUseCase = sendEmailCodeUseCase;
        this.findUsernameUseCase = findUsernameUseCase;
        this.resetPasswordUseCase = resetPasswordUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/signup")
    public ResponseEntity<ApiResult<Object>> signup(@RequestBody SignupRequest request) {
        log.info("/users/signup: {}", request);
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
    @PostMapping("/reissue")
    public ResponseEntity<ApiResult<Object>> reissue(@CookieValue("Refresh") String refreshToken) {
        log.info("/users/reissue");

        ReissueCommand command = ReissueCommand.builder()
                .refreshToken(refreshToken)
                .build();

        AccessTokenState accessToken = reissueUseCase.reissue(command);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, accessToken.toBearerString())
                .build();
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/reissue-workspace")
    public ResponseEntity<ApiResult<Object>> reissueWorkspace(
            @CookieValue("Refresh") String refreshToken,
            @RequestBody ReissueWorkspaceRequest request
    ) {
        log.info("/users/reissue-workspace: {}", request);

        ReissueWorkspaceCommand command = ReissueWorkspaceCommand.builder()
                .refreshToken(refreshToken)
                .workspaceId(new WorkspaceId(request.workspaceId()))
                .build();

        AccessTokenState accessToken = reissueUseCase.reissueWorkspace(command);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, accessToken.toBearerString())
                .build();
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/email-auth")
    public ResponseEntity<ApiResult<Object>> emailAuthPost(@RequestBody EmailAuthPostRequest request) {
        log.info("/users/email-auth: {}", request);

        SendEmailCodeCommand command = SendEmailCodeCommand.builder()
                .email(request.email())
                .build();

        sendEmailCodeUseCase.sendEmailCode(command);

        return ResponseEntity.ok(ApiUtils.success(
                HttpStatus.OK,
                null
        ));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/email-auth/signup")
    public ResponseEntity<ApiResult<Object>> emailAuthSignup(@RequestBody EmailAuthVerifyRequest request) {
        log.info("/users/email-auth/signup: {}", request);

        VerifyEmailCodeCommand command = VerifyEmailCodeCommand.builder()
                .email(request.email())
                .codeNumber(request.codeNumber())
                .build();

        boolean isEmailVerified = signupUseCase.verifyEmailCode(command);

        if (isEmailVerified) {
            return ResponseEntity.ok(ApiUtils.success(
                    HttpStatus.OK,
                    null
            ));
        }
        else {
            return ResponseEntity.ok(ApiUtils.fail(HttpStatus.NOT_FOUND));
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/email-auth/username")
    public ResponseEntity<ApiResult<Object>> emailAuthUsername(@RequestBody EmailAuthVerifyRequest request) {
        log.info("/users/email-auth/username: {}", request);

        VerifyEmailCodeCommand command1 = VerifyEmailCodeCommand.builder()
                .email(request.email())
                .codeNumber(request.codeNumber())
                .build();

        boolean isEmailVerified = findUsernameUseCase.verifyEmailCode(command1);

        if (isEmailVerified) {
            FindUsernameCommand command2 = FindUsernameCommand.builder()
                    .email(request.email())
                    .build();

            String username = findUsernameUseCase.findUsername(command2);

            return ResponseEntity.ok(ApiUtils.success(
                    HttpStatus.OK,
                    username
            ));
        } else {
            return ResponseEntity.ok(ApiUtils.fail(HttpStatus.NOT_FOUND));
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/email-auth/password")
    public ResponseEntity<ApiResult<Object>> emailAuthPassword(@RequestBody EmailAuthVerifyRequest request) {
        log.info("/users/email-auth/password: {}", request);

        VerifyEmailCodeCommand command1 = VerifyEmailCodeCommand.builder()
                .email(request.email())
                .codeNumber(request.codeNumber())
                .build();

        boolean isEmailVerified = resetPasswordUseCase.verifyEmailCode(command1);

        if (isEmailVerified) {
            ResetPasswordCommand command2 = ResetPasswordCommand.builder()
                    .email(request.email())
                    .build();

            String password = resetPasswordUseCase.resetPassword(command2);

            return ResponseEntity.ok(ApiUtils.success(
                    HttpStatus.OK,
                    password
            ));
        } else {
            return ResponseEntity.ok(ApiUtils.fail(HttpStatus.NOT_FOUND));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/password")
    public ResponseEntity<ApiResult<String>> password(@RequestBody PasswordRequest request) {
        log.info("/users/password: {}", request);

        UserId userId = UserId.parseOrNull(
                SecurityContextHolder.getContext().getAuthentication().getDetails().toString()
        );
        assert userId != null;

        ChangePasswordCommand command = ChangePasswordCommand.builder()
                .userId(userId)
                .password(request.password())
                .build();

        changePasswordUseCase.changePassword(command);

        return ResponseEntity.ok(ApiUtils.success(
                HttpStatus.OK,
                null
        ));
    }
}
