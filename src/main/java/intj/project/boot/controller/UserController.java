package intj.project.boot.controller;

import intj.project.boot.dto.CheckIdDto;
import intj.project.boot.dto.UserInsertDto;
import intj.project.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/insert")
    public ResponseEntity<String> userInsert(@RequestBody UserInsertDto dto) {
        log.info("dto.userId = {}", dto.getUserId());
        return ResponseEntity.ok(userService.userInsert(dto));
    }
    @PostMapping(value = "/check")
    public ResponseEntity<String> userIdCheck(@RequestBody CheckIdDto dto) {
        String userId = dto.getUserId();
        log.info("userId = {}", userId);
        return ResponseEntity.ok(userService.userSelectByUserId(userId));
    }
}
