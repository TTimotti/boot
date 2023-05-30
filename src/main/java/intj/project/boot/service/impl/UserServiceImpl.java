package intj.project.boot.service.impl;

import intj.project.boot.dto.UserInsertDto;
import intj.project.boot.entity.UserEntity;
import intj.project.boot.exception.ServiceException;
import intj.project.boot.mapper.DtoMapper;
import intj.project.boot.mapper.UserMapper;
import intj.project.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final DtoMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("오버라이드 메서드 실행됨 username = {};", username);
        UserEntity entity = userMapper.selectUserByUserId(username);
        if (entity == null) {
            throw new UsernameNotFoundException("유저를 찾을 수 없음");
        }
        return entity;
    }

    @Override
    public String userInsert(UserInsertDto dto) {
        UserEntity user = mapper.userInsertDtoToUserEntiy(dto);
        try {
            userMapper.insertUser(user);
        } catch (DataIntegrityViolationException e) {
            log.error("ERROR={}", e.getMessage());
            throw new ServiceException("UserInsert Query Error :: DB INSERT 실패");
        }
        return user.getUsername();
    }

    @Override
    public String userSelectByUserId(String userId) {
        UserEntity user = userMapper.selectUserByUserId(userId);
        if (user == null) {
            return null;
        }
        return user.getUsername();
    }
}
