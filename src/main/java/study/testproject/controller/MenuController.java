package study.testproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.testproject.dto.RestApiResponse;
import study.testproject.service.MenuService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MenuController {

	private final MenuService menuService;

	@GetMapping("/rootMenu")
	public RestApiResponse menu() {
		try {
			return RestApiResponse.dataWithTrue(menuService.getRootMenu());
		} catch (Exception e) {
			return RestApiResponse.messageWithFalse("메뉴 조회 에러");
		}
	}

}
