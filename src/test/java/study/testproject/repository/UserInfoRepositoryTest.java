package study.testproject.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import study.testproject.dto.mapper.UserInfoMapper;
import study.testproject.entity.UserInfo;
import study.testproject.entity.UserInfo.UserInfoBuilder;
import study.testproject.type.UseState;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@Slf4j
public class UserInfoRepositoryTest {

	@Autowired
	private UserInfoRepository repo;

	@Autowired
	private UserInfoMapper mapper;

	private static boolean isBeforeTransactionExecuted = false;

//	@Test
	void save() {
		UserInfo userInfo = UserInfo.builder()
				.userId("id")
				.userName("name")
				.password("password")
				.email(null)
				.enable(UseState.Y)
				.build();
		repo.save(userInfo);
	}

	@BeforeTransaction
	void insertUsers() {
		if (isBeforeTransactionExecuted) {
			return;
		}
		
		log.debug("===> insert 작업 수행");

		for (int i = 1; i <= 10; i++) {
			UserInfoBuilder userInfoBuilder = UserInfo.builder()
					.userId("id" + i)
					.userName("name" + i)
					.password("password" + i)
					.email(null);

			if (i % 2 == 0) {
				userInfoBuilder.enable(UseState.N);
			} else {
				userInfoBuilder.enable(UseState.Y);
			}

			UserInfo u = userInfoBuilder.build();
			repo.saveAndFlush(u);
		}
	}

	@Test
	@Transactional(readOnly = true)
	void findByEnable() {
		assertDoesNotThrow(() -> {
			List<UserInfo> userInfos = repo.findByEnable(UseState.Y);
			userInfos.forEach(u -> {
				if (u.getEnable() == UseState.N) {
					throw new RuntimeException("반대값이 포함되어있음");
				}
			});
		});
	}

}
