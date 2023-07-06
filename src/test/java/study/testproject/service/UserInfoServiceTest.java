package study.testproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
//@Rollback(false)
@Slf4j
public class UserInfoServiceTest {

	@Autowired
	private UserInfoService serivce;

//	@Test
	void insertUser() {
	}
}
