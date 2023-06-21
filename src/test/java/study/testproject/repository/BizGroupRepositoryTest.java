package study.testproject.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import study.testproject.entity.BizGroup;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback(false)
@Slf4j
public class BizGroupRepositoryTest {

	@Autowired
	private BizGroupRepository repo;
	
	// ddl: create
	@Test
	@DisplayName("BizGroup 다건데이터 insert")
	void insertDataset() {
		// root 1개
		BizGroup root = BizGroup.builder().name("SBTGlobal").build();
		BizGroup savedRoot = repo.save(root);
		
		for (int i = 1; i <= 3; i++) {
			// level1 3개
			BizGroup level1 = BizGroup.builder()
										.name("group1-" + i)
										.parentBizGroup(savedRoot)
										.build();
			BizGroup savedLevel1 = repo.save(level1);
			
			// level2 9개
			for (int j = 1; j <= 3; j++) {
				BizGroup level2 = BizGroup.builder()
											.name("group2-" + j)
											.parentBizGroup(savedLevel1)
											.build();
				BizGroup savedLevel2 = repo.save(level2);
			}
		}
		
		repo.flush();
	}
	
	// ddl: update
	@Test
	@DisplayName("루트 BizGroup 조회")
	void rootBizGroupJpql() {
		log.debug("===> select root BizGroup");
		
		// 하단 세개의 메서드 모두 is null로 들어감
		BizGroup find = repo.findByParentBizGroupIsNull();
//		BizGroup find = repo.findRootBizGroup();
//		BizGroup find = repo.findRootBizGroup2();
		
		log.debug("===> bizGroupId: {}, name: {}", find.getBizGroupId(), find.getName());
	}
	
}
