package study.testproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.testproject.type.UseState;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoDto {

	private Long id;
	private String userId;
	private String userName;
	private String password;
	private String email;
	private UseState enable;
}
