package study.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.testproject.entity.BizGroup;

public interface BizGroupRepository extends JpaRepository<BizGroup, Long> {

	/*
	 * SQL
	 * select * from biz_group b where b.parent_biz_group_id is null;
	 * 
	 * JPQL
	 * select b from BizGroup b where b.parentBizGroup = null;
	 * select b from BizGroup b where b.parentBizGroup is null;
	 */
	BizGroup findByParentBizGroupIsNull();
	
//	@Query("select b from BizGroup b where b.parentBizGroup is null")
//	BizGroup findRoot();
	
//	@Query("select b from BizGroup b where b.parentBizGroup = null")
//	BizGroup findRoot();
	
	

}
