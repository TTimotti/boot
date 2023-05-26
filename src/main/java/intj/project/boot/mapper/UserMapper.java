package intj.project.boot.mapper;

import intj.project.boot.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(UserEntity user);
    UserEntity selectUserByUserId(String userId);
}
