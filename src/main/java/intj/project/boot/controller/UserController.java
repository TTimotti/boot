package intj.project.boot.controller;

import intj.project.boot.dto.UserInsertDto;
import intj.project.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/insert")
    public ResponseEntity<String> userInsert(UserInsertDto dto) {
        log.info("dto = {}", dto);
        userService.getClass();

        return ResponseEntity.ok(dto.getUserId());
    }
}
