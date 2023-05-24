package intj.project.boot.service;

import intj.project.boot.dto.UserInsertDto;
import intj.project.boot.entity.UserEntity;
import intj.project.boot.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean userInsert(UserInsertDto dto) {
        String pw = passwordEncoder.encode(dto.getPassword());
        String name = StringUtils.substringBeforeLast(dto.getUserId(), "@");

        UserEntity user = UserEntity.builder()
                .userId(dto.getUserId())
                .userName(name)
                .password(pw)
                .build();

        int result = userMapper.insertUser(user);
        if (result == 0) {
            throw new RuntimeException("UserInsert Query Error");
        }
        return true;
    }
}
