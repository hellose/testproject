package study.testproject.repository;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import study.testproject.dto.MenuDto;
import study.testproject.dto.MenuIdName;
import study.testproject.dto.mapper.MenuMapper;
import study.testproject.entity.Menu;
import study.testproject.type.UseState;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
//@Rollback(false)
@Slf4j
public class MenuRepositoryTest {

	@Autowired
	private MenuRepository repo;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private ObjectMapper objectMapper; 
	
	@Test
	void insertRootMenu() {
		Menu root = Menu.builder()
				.menuName("Demo")
				.menuUrl("/")
				.menuOrder(1)
				.useState(UseState.Y)
				.build();
		repo.save(root);
	}
	
	@Test
	@DisplayName("테스트 Menu insert")
	void insertTestMenus() {
		//root
		Menu root = Menu.builder()
						.menuName("메뉴")
						.menuUrl(null)
						.menuOrder(1)
						.useState(UseState.Y)
						.build();
		repo.save(root);
		
		//first level
		for(int i=1; i<=3; i++) {
			Menu level1 = Menu.builder()
								.menuName("레벨1-" + i)
								.menuUrl("/level1-" + i)
								.menuOrder(i)
								.useState(UseState.Y)
								.parentMenu(root)
								.build();
			repo.save(level1);
			
			if(i==3) {
				break;
			}
			
			//second level
			for(int j=1; j<=3; j++) {
				Menu level2 = Menu.builder()
									.menuName("레벨2-" + j)
									.menuUrl(level1.getMenuUrl() + "/level2-" + j)
									.menuOrder(j)
									.useState(UseState.Y)
									.parentMenu(level1)
									.build();
				repo.save(level2);
			}
		}
		
		repo.flush();
	}
	
	@Test
	@DisplayName("부모 Menu가 존재하는 Menu select")
	void selectMenuWhichParentExist() {
		Optional<Menu> optional  = repo.findById(53L);
		if(optional.isEmpty()) {
			fail();
		}
		
		Menu menu = optional.get();
		log.debug("===> Menu.getParentMenu()");
		menu.getParentMenu();
		
		log.debug("===> Menu.getParentMenu().getmenuName()");
		menu.getParentMenu().getMenuName();
	}
	
	
	@Test
	@DisplayName("모든 Menu id,name 리스트")
	void menuIdAndNameListTest() {
		List<MenuIdName> list = repo.getMenuIdNameList();
		list.forEach(m -> log.debug("===> {}", m));
	}
	
	
	@Test
	@DisplayName("parent_menu_id 컬럼을 통해 루트 메뉴 조회")
	void rootMenuTest() {
		// 미정렬
		List<Menu> list = repo.findByParentMenu(null);
		// 정렬
//		List<Menu> list = repo.findByParentMenuOrderByMenuOrderDesc(null);
		
		if (list == null) {
			fail("list가 null임");
		}

		int count = list.size();
		if ((count == 0) || (count > 1)) {
			fail("루트 메뉴가 하나가 아님");
		}
		log.debug("===> JPQL result count: {}", count);
	}

	@Test
	@DisplayName("parent_menu_id 컬럼을 통해 루트 메뉴 조회 -> JSON")
	void rootMenuTest2() throws Exception {
		// 미정렬
		List<Menu> list = repo.findByParentMenu(null);
		// 정렬
//		List<Menu> list = repo.findByParentMenuOrderByMenuOrderDesc(null);

		if (list == null) {
			fail("list가 null임");
		}

		int count = list.size();
		if ((count == 0) || (count > 1)) {
			fail("루트 메뉴가 하나가 아님");
		}
		
		Menu rootMenu = list.get(0);
		MenuDto rootMenuDto = menuMapper.toDtoWithChild(rootMenu);
		log.debug("{}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootMenuDto));
	}
	
	@Test
	@DisplayName("루트 메뉴의 부모 메뉴 필드 테스트")
	void rootMenuTest3() {
		List<Menu> list = repo.findByParentMenu(null);
		
		if (list == null) {
			fail("list가 null임");
		}
		int count = list.size();
		if ((count == 0) || (count > 1)) {
			fail("루트 메뉴가 하나가 아님");
		}
		
		Menu root = list.get(0);
		Menu rootParent = root.getParentMenu();
		if (rootParent == null) {
			log.debug("===> parent null");
		}else {
			log.debug("===> {}", rootParent.getClass().getSimpleName());
		}
	}

	@Test
	@DisplayName("명속성 컨텍스트에 존재하는 메뉴의 동일설 테스트")
	void menuPersistenceContextTest() {
		log.debug("===> findById()");
		Menu findRoot = repo.findById(1L).get();
		
		log.debug("===> findByParentMenu()");
		Menu jpqlRoot = repo.findByParentMenu(null).get(0);
		
		log.debug("===> findById객체 = jpqlRoot객체: {}", findRoot == jpqlRoot);
	}
	
	@Test
	@DisplayName("시스템 내에 루트 메뉴가 존재하지 않는 경우 findByParentMenu 테스트")
	void whenRootMenuNotExists() {
		List<Menu> list = repo.findByParentMenu(null);
		if (list == null) {
			fail("list가 null임");
		}
		log.debug("===> count: {}", list.size()); // 0
	}
	
	
}
