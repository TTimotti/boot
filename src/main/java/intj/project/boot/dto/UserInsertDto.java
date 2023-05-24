package intj.project.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserInsertDto {
    private String userId;
    private String password;
}
