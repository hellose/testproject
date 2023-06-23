package study.testproject.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.RequiredArgsConstructor;
import study.testproject.service.MenuService;

@ControllerAdvice(assignableTypes = DefaultController.class)
@RequiredArgsConstructor
public class DefaultControllerAdvice {

	private final MenuService menuService;
}
