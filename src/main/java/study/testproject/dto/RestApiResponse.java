package study.testproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestApiResponse {

	private Boolean success;
	private Object data;
	private String message;

	public static RestApiResponse dataWithTrue(Object data) {
		return new RestApiResponse(true, data, null);
	}

	public static RestApiResponse messageWithFalse(String message) {
		return new RestApiResponse(false, null, message);
	}
}
