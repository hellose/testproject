package study.testproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.testproject.dto.MenuDto;
import study.testproject.dto.MenuIdName;
import study.testproject.dto.mapper.MenuMapper;
import study.testproject.entity.Menu;
import study.testproject.exception.NotFoundException;
import study.testproject.exception.NotValidDataException;
import study.testproject.exception.RuleViolationException;
import study.testproject.repository.MenuRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuService {

//	private static MenuDto rootMenuDto;

	private final MenuRepository menuRepo;
	private final MenuMapper menuMapper;

	/**
	 * @return 모든 메뉴를 포함하는 최상위 메뉴
	 */
	@Transactional(readOnly = true)
	public MenuDto getRootIncludeAllChildMenu() {
		List<Menu> list = menuRepo.findByParentMenu(null);
		if (list == null) {
			return null;
		}

		int count = list.size();
		if (count > 1) {
			throw new RuleViolationException("루트 메뉴는 하나만 있어야합니다.");
		}
		if (count == 0) {
			throw new RuleViolationException("DB에서 루트 메뉴를 등록해주세요.");
		}

		Menu root = list.get(0);
		return menuMapper.toDtoWithChild(root);
	}

	/**
	 * @return 모든 메뉴 id,name 리스트
	 */
	@Transactional(readOnly = true)
	public List<MenuIdName> getAllMenuIdNameList() {
		return menuRepo.getMenuIdNameList();
	}

	@Transactional(readOnly = true)
	public MenuDto getMenus() {
		MenuDto menuDto = getRootIncludeAllChildMenu();

		List<MenuIdName> allMenuIdNameList = getAllMenuIdNameList();
		menuDto.setMenuIdNames(allMenuIdNameList);

		return menuDto;
	}

	/**
	 * @param dto 기본정보 또는 상위메뉴가 변경된 메뉴
	 */
	@Transactional
	public void updateMenu(MenuDto dto) {
		log.debug("===> <updateMenu>");
		
		Optional<Menu> modifyMenuOptional = menuRepo.findById(dto.getMenuId());
		if (modifyMenuOptional.isEmpty()) {
			throw new NotFoundException("존재하지 않는 메뉴입니다.");
		}
		Menu modifyMenu = modifyMenuOptional.get();

		List<Menu> list = menuRepo.findByParentMenu(null);
		Menu findRoot = list.get(0);

		// 변경 메뉴가 루트 메뉴인 경우
		if (modifyMenu.getMenuId() == findRoot.getMenuId()) {
			// 기본 정보
			findRoot.setMenuName(dto.getMenuName());
			findRoot.setMenuUrl(dto.getMenuUrl());
			findRoot.setUseState(dto.getUseState());
			findRoot.setMenuOrder(dto.getMenuOrder());
		}

		// 변경 메뉴가 루트 메뉴가 아닌 경우

		// 기본 정보
		modifyMenu.setMenuName(dto.getMenuName());
		modifyMenu.setMenuUrl(dto.getMenuUrl());
		modifyMenu.setMenuUrl(dto.getMenuUrl());
		modifyMenu.setMenuOrder(dto.getMenuOrder());

		// 상위 메뉴
		if ((dto.getParentMenu() == null) || (dto.getParentMenu().getMenuId() == null)) {
			throw new NotValidDataException();
		}
		Optional<Menu> modifyParentOptional = menuRepo.findById(dto.getParentMenu().getMenuId());
		if (modifyMenuOptional.isEmpty()) {
			throw new NotFoundException("상위 메뉴가 존재하지 않습니다.");
		}
		Menu parentMenu = modifyParentOptional.get();
		modifyMenu.setParentMenu(parentMenu);
	}

	/**
	 * @param dto 상위메뉴 객체가 포함된 메뉴
	 */
	@Transactional
	public void insertMenu(MenuDto dto) {
		log.debug("===> <insertMenu>");
		
		Optional<Menu> opt = menuRepo.findById(dto.getParentMenu().getMenuId());
		if (opt.isEmpty()) {
			throw new NotFoundException("추가하시는 메뉴의 상위 메뉴가 존재하지 않습니다.");
		}
		Menu parentMenu = opt.get();

		Menu addMenu = new Menu();
		addMenu.setParentMenu(parentMenu);
		addMenu.setMenuName(dto.getMenuName());
		addMenu.setMenuUrl(dto.getMenuUrl());
		addMenu.setMenuOrder(dto.getMenuOrder());
		addMenu.setUseState(dto.getUseState());
		
		menuRepo.save(addMenu);
	}

	/**
	 * @param dto 부모, 자식을 포함하지 않은 메뉴 객체
	 */
	@Transactional
	public void deleteMenu(MenuDto dto) {
		log.debug("===> <deleteMenu>");
		try {
			menuRepo.deleteById(dto.getMenuId());
		}catch(EmptyResultDataAccessException e) {
			throw new NotFoundException("존재하지 않는 메뉴입니다.");
		}
	}

}
