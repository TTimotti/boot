package intj.project.boot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserInsertDto {
    private String userId;
    private String password;
}
