package study.testproject.controller;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.RequiredArgsConstructor;
import study.testproject.dto.MenuDto;
import study.testproject.service.MenuService;

@ControllerAdvice(assignableTypes = DefaultController.class)
@RequiredArgsConstructor
public class DefaultControllerAdvice {

	private final MenuService menuService;

	@ModelAttribute("rootMenu")
	@Order(1)
	public MenuDto getRootMenu() {
		return menuService.getRootMenu();
	}
}
