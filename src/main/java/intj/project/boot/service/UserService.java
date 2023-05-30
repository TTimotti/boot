package intj.project.boot.service;

import intj.project.boot.dto.UserInsertDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username);

    String userInsert(UserInsertDto dto);

    String userSelectByUserId(String userId);
}