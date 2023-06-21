package study.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.testproject.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	// sql: select * from menu m where parent_menu_id is null;
	// jpql: select m from menu m where m.parentMenu is null;
	Menu findByParentMenuIsNull();

}