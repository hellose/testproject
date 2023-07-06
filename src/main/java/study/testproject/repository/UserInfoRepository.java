package study.testproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import study.testproject.entity.UserInfo;
import study.testproject.type.UseState;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	List<UserInfo> findByEnable(UseState useState);
}