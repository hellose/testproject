package study.testproject.repository;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import study.testproject.dto.MenuDto;
import study.testproject.dto.mapper.MenuMapper;
import study.testproject.entity.Menu;
import study.testproject.type.UseState;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback(false)
@Slf4j
public class MenuRepositoryTest {

	@Autowired
	private MenuRepository repo;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private ObjectMapper objectMapper; 
	
	// ddl: create
	@Test
	@DisplayName("실제 Menu insert")
	void insertRealMenu() {
		//root 메뉴
		Menu root = Menu.builder()
						.menuName("Root")
						.menuUrl(null)
						.menuOrder(1)
						.useState(UseState.Y)
						.build();
		repo.save(root);
		
		//quartz 메뉴
		Menu quartz = Menu.builder()
							.menuName("quartz")
							.menuUrl("/quartz")
							.menuOrder(2)
							.useState(UseState.Y)
							.parentMenu(root)
							.build();
		repo.save(quartz);
		
		//etl 메뉴
		Menu etl = Menu.builder()
				.menuName("etl")
				.menuUrl("/etl")
				.menuOrder(2)
				.useState(UseState.Y)
				.parentMenu(root)
				.build();
		repo.save(etl);
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
	@DisplayName("테스트 루트 메뉴 json 변환")
	void rootMenuJson() throws Exception {
		Menu rootMenu = null;
		try {
			rootMenu = repo.findByParentMenuIsNull();
		} catch (Exception e) {
			new RuntimeException(e);
		}

		if (rootMenu == null) {
			fail("루트 메뉴가 존재하지 않음");
		}
		MenuDto rootMenuDto = menuMapper.toDtoWithChild(rootMenu);
		log.debug("{}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootMenuDto));
	}
	
	
	
	
}
