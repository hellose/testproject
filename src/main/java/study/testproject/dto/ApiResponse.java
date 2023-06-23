package study.testproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	private Boolean success;
	private Object data;
	private String message;

	public static ApiResponse dataWithTrue(Object data) {
		return new ApiResponse(true, data, null);
	}

	public static ApiResponse messageWithFalse(String message) {
		return new ApiResponse(false, null, message);
	}
}
