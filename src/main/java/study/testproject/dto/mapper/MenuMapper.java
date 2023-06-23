package study.testproject.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import study.testproject.dto.MenuDto;
import study.testproject.entity.Menu;

@Component
public class MenuMapper {

	public MenuDto toDtoExcludeEntity(Menu menu) {
		if (menu == null) {
			return null;
		}

		MenuDto menuDto = new MenuDto();
		menuDto.setMenuId(menu.getMenuId());
		menuDto.setMenuName(menu.getMenuName());
		menuDto.setMenuUrl(menu.getMenuUrl());
		menuDto.setMenuOrder(menu.getMenuOrder());
		menuDto.setUseState(menu.getUseState());

		return menuDto;
	}

	public MenuDto toDtoWithParent(Menu menu) {
		MenuDto menuDto = toDtoExcludeEntity(menu);
		if (menuDto == null) {
			return null;
		}

		menuDto.setParentMenu(toDtoExcludeEntity(menu));

		return menuDto;
	}

	public MenuDto toDtoWithChild(Menu menu) {
		MenuDto menuDto = toDtoExcludeEntity(menu);
		if (menuDto == null) {
			return null;
		}

		menuDto.setChildMenus(toDtoWitChild(menu.getChildMenus()));
		return menuDto;
	}

	public List<MenuDto> toDtoWitChild(List<Menu> list) {
		if (list == null) {
			return null;
		}

		List<MenuDto> menuDtoList = new ArrayList<>(list.size());
		for (Menu menu : list) {
			menuDtoList.add(toDtoWithChild(menu));
		}
		return menuDtoList;
	}
	
	public Menu toEntityExcludeEntity(MenuDto dto) {
		if (dto == null) {
			return null;
		}
		
		Menu menu = new Menu();
		
		menu.setMenuId(dto.getMenuId());
		menu.setMenuName(dto.getMenuName());
		menu.setMenuUrl(dto.getMenuUrl());
		menu.setMenuOrder(dto.getMenuOrder());
		menu.setUseState(dto.getUseState());
		
		return menu;
	}

}
