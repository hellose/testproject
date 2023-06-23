package study.testproject.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UseState {
	
	Y("사용"), N("미사용");
	
	private final String text;
}
