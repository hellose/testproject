package study.testproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import study.testproject.dto.MenuDto;
import study.testproject.dto.mapper.MenuMapper;
import study.testproject.entity.Menu;
import study.testproject.repository.MenuRepository;

@Service
@RequiredArgsConstructor
public class MenuService {

	private static MenuDto rootMenuDto;

	private final MenuRepository menuRepo;
	private final MenuMapper menuMapper;

	@Transactional
	public void setRootMenu() {
		Menu rootMenu = menuRepo.findByParentMenuIsNull();
		rootMenuDto = menuMapper.toDtoWithChild(rootMenu);
	}

	public MenuDto getRootMenu() {
		if (rootMenuDto == null) {
			setRootMenu();
		}
		return rootMenuDto;
	}
	

}
