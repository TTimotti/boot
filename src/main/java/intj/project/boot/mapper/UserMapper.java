package intj.project.boot.mapper;

import intj.project.boot.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(UserEntity user);
}
