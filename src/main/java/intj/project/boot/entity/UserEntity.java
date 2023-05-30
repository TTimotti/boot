package intj.project.boot.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@ToString
@Builder
@Slf4j
public class UserEntity implements UserDetails {
    private int seq;
    private String userId;
    private String userName;
    private String password;
    private String createDt;
    private String lastLoginDt;
    private int roleId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        log.info("혹시 여기서 에러가 나는 걸까?");
        if (roleId==9) {
            authorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
        } else if (roleId==1) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        log.info("authorities ={};", authorities);
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
