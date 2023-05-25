package intj.project.boot.service;

import intj.project.boot.dto.UserInsertDto;
import intj.project.boot.entity.UserEntity;
import intj.project.boot.exception.ServiceException;
import intj.project.boot.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public String userInsert(UserInsertDto dto) {
        String pw = passwordEncoder.encode(dto.getPassword());
        String name = StringUtils.substringBeforeLast(dto.getUserId(), "@");

        UserEntity user = UserEntity.builder()
                .userId(dto.getUserId())
                .userName(name)
                .password(pw)
                .roleId(1)
                .build();

        try {
            userMapper.insertUser(user);
        } catch (DataIntegrityViolationException e) {
            log.error("ERROR={}", e);
            throw new ServiceException("UserInsert Query Error :: DB INSERT 실패");
        }

        return user.getUsername();
    }
}
