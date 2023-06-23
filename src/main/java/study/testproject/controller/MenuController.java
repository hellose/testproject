package study.testproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.testproject.dto.ApiResponse;
import study.testproject.dto.MenuDto;
import study.testproject.exception.NotValidDataException;
import study.testproject.service.MenuService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MenuController {

	private final MenuService menuService;

	/**
	 * 존재하는 모든 메뉴를 포함한 최상위 메뉴 데이터를 전송
	 */
	@GetMapping("/menus")
	public ResponseEntity<ApiResponse> menus() {
		try {
			return ResponseEntity
					.ok()
					.body(ApiResponse.dataWithTrue(menuService.getMenus()));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity
					.badRequest()
					.body(ApiResponse.messageWithFalse(e.getMessage()));
		}
	}

	/**
	 * 메뉴의 기본정보 또는 상위메뉴를 변경
	 */
	@PutMapping("/menu")
	public ResponseEntity<ApiResponse> updateMenu(@RequestBody MenuDto dto) {
		try {
			menuService.updateMenu(dto);
			return ResponseEntity
					.ok()
					.body(ApiResponse.dataWithTrue(ApiResponse.dataWithTrue(null)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity
					.badRequest()
					.body(ApiResponse.messageWithFalse(e.getMessage()));
		}
	}

	/**
	 * 메뉴 추가
	 */
	@PostMapping("/menu")
	public ResponseEntity<ApiResponse> insertMenu(@RequestBody MenuDto dto) {
		try {
			// 상위 메뉴 객체 validation
			MenuDto pMenuDto = dto.getParentMenu();
			if ((pMenuDto == null) || (pMenuDto.getMenuId() == null)) {
				throw new NotValidDataException();
			}
			menuService.insertMenu(dto);
			return ResponseEntity
					.ok()
					.body(ApiResponse.dataWithTrue(ApiResponse.dataWithTrue(null)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity
					.badRequest()
					.body(ApiResponse.messageWithFalse(e.getMessage()));
		}
	}

	/**
	 * 메뉴 삭제
	 */
	@DeleteMapping("/menu")
	public ResponseEntity<ApiResponse> deleteMenu(@RequestBody MenuDto dto) {
		try {
			// 삭제 메뉴 id validation
			if (dto.getMenuId() == null) {
				throw new NotValidDataException();
			}
			menuService.deleteMenu(dto);
			return ResponseEntity
						.ok()
						.body(ApiResponse.dataWithTrue(null));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity
						.badRequest()
						.body(ApiResponse.messageWithFalse(e.getMessage()));
		}
	}
	
//	@GetMapping("/resbody")
//	public ApiResponse test() {
//		return ApiResponse.dataWithTrue(new ArrayList<String>());
//	}
//	
//	@GetMapping("/resentity")
//	public ResponseEntity<ApiResponse> test2() {
//		ApiResponse apiRes = ApiResponse.dataWithTrue(new ArrayList<String>());
//		return ResponseEntity.badRequest().body(apiRes);
////		return ResponseEntity.ok(apiRes);
//	}
	
}
