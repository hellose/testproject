package study.testproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import study.testproject.dto.MenuIdName;
import study.testproject.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	/**
	 * Menu가 null인 경우 m.parent_menu_id is null인 JPQL<br>
	 * Menu의 pk가 지정된 경우 m.parent_menu_id = pk인 JPQL
	 */
	// 미정렬
	List<Menu> findByParentMenu(Menu menu);
	// 정렬
	List<Menu> findByParentMenuOrderByMenuOrderDesc(Menu menu);
//	List<Menu> findByParentMenuOrderByMenuOrderAsc(Menu menu);

	// m.parent_menu_id를 직접 사용할 수 없음 -> Exception
//	@Query("select m from Menu m where m.parent_menu_id = :menuId")
//	List<Menu> test(@Param("menuId") Long menuId);
	
	//모든 menuId 리스트
//	@Query("SELECT m.menuId FROM Menu m")
//	List<Long> getMenuIdList();

	//모든 menuId,menuName 리스트
	@Query("SELECT new study.testproject.dto.MenuIdName(m.menuId, m.menuName) FROM Menu m")
	List<MenuIdName> getMenuIdNameList();

}