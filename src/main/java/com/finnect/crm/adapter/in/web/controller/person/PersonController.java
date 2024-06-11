package com.finnect.crm.adapter.in.web.controller.person;

import com.finnect.common.ApiUtils;
import com.finnect.crm.adapter.in.web.req.person.CreatePersonRequest;
import com.finnect.crm.adapter.in.web.req.person.UpdatePersonRequest;
import com.finnect.crm.adapter.in.web.res.person.*;
import com.finnect.crm.application.port.in.person.*;
import com.finnect.crm.domain.person.PersonState;
import com.finnect.crm.domain.person.PersonWithCompanyState;
import com.finnect.user.vo.WorkspaceAuthority;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final CreatePersonUsecase createPersonUsecase;
    private final UpdatePersonUsecase updatePersonUsecase;
    private final FindPeopleUsecase findPeopleUsecase;
    private final DeletePersonUsecase deletePersonUsecase;

    @Operation(
            summary = "Person 생성 API",
            description = "주어진 Person에 대한 정보로 새로운 Person을 생성합니다."
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/workspaces/people")
    public ResponseEntity<ApiUtils.ApiResult<CreatePersonResponse>> createPerson(@RequestBody CreatePersonRequest request) {
        PersonState state = createPersonUsecase.createPerson(
                new CreatePersonCommand(
                        request.getCompanyId(),
                        request.getPersonName(),
                        request.getPersonRole(),
                        request.getPersonEmail(),
                        request.getPersonPhone()
                )
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiUtils.success(HttpStatus.CREATED, CreatePersonResponse.of(PersonDto.from(state)))
        );
    }

    @Operation(
            summary = "Person 정보 변경 API",
            description = """
                    주어진 Person에 대한 정보로 Person의 정보를 수정합니다.
                    personId는 반드시 필요하며, 이 외에는 수정할 정보만 포함해 요청합니다."""
    )
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/workspaces/people")
    public ResponseEntity<ApiUtils.ApiResult<UpdatePersonResponse>> updatePerson(@RequestBody UpdatePersonRequest request) {
        PersonState state = updatePersonUsecase.update(
                new UpdatePersonCommand(
                        request.getPersonId(),
                        request.getPersonName(),
                        request.getPersonRole(),
                        request.getPersonEmail(),
                        request.getPersonPhone()
                )
        );

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiUtils.success(HttpStatus.OK, UpdatePersonResponse.of(PersonDto.from(state)))
        );
    }

    @Operation(
            summary = "Person 배열 조회 API",
            description = "주어진 companyId로 Company에 속한 Person의 배열을 반환합니다."
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/workspaces/people")
    public ResponseEntity<ApiUtils.ApiResult<FindPeopleResponse>> findPeople(@RequestParam("companyId") Long companyId) {
        List<PersonState> people = findPeopleUsecase.findPeopleByCompany(companyId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiUtils.success(HttpStatus.OK, FindPeopleResponse.of(
                        people.stream().map(PersonDto::from).collect(Collectors.toList()))
                ));
    }

    @Operation(
            summary = "Person 전체 조회 API",
            description = """
                    주어진 access token의 workspace id로 Person의 배열을 반환합니다.
                    Company와 상관 없이, 해당 Workspace에 속한 모든 Person을 반환합니다."""
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/workspaces/people/all")
    public ResponseEntity<ApiUtils.ApiResult<FindAllPeopleResponse>> findAllPeople() {
        Long workspaceId;
        try {
            workspaceId = WorkspaceAuthority.from(
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities()
            ).workspaceId().value();
        } catch (Exception e) {
            throw new RuntimeException("워크스페이스 ID가 누락되었습니다.");
        }

        List<PersonWithCompanyState> people = findPeopleUsecase.findPeopleByWorkspace(workspaceId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiUtils.success(HttpStatus.OK, FindAllPeopleResponse.of(
                        people.stream().map(PersonWithCompanyDto::from).collect(Collectors.toList()))
                ));
    }

    @Operation(
            summary = "Person 삭제 API",
            description = "주어진 personId로 Person을 삭제합니다."
    )
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/workspaces/people/{personId}")
    public ResponseEntity<ApiUtils.ApiResult<?>> deletePerson(@PathVariable("personId") Long personId) {

        deletePersonUsecase.delete(personId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiUtils.success(HttpStatus.OK, null)
        );
    }
}
