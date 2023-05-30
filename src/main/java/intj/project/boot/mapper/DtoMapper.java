package intj.project.boot.mapper;

import intj.project.boot.dto.UserInsertDto;
import intj.project.boot.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoMapper {
    private final BCryptPasswordEncoder passwordEncoder;

    public UserEntity userInsertDtoToUserEntiy(UserInsertDto dto) {
        String pw = passwordEncoder.encode(dto.getPassword());
        String name = StringUtils.substringBeforeLast(dto.getUserId(), "@");

        return UserEntity.builder()
                .userId(dto.getUserId())
                .userName(name)
                .password(pw)
                .roleId(1)
                .build();
    }
}
