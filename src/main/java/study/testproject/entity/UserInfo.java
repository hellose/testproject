package study.testproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.testproject.type.UseState;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "UserInfoSeqGen", sequenceName = "user_info_seq")
@Entity
public class UserInfo extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserInfoSeqGen")
	private Long id;

	@Column(nullable = false, unique = true, length = 50)
	private String userId;

	@Column(length = 30)
	private String userName;

	@Column(nullable = false, length = 256)
	private String password;

	@Column(unique = true, length = 50)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	private UseState enable;
}
