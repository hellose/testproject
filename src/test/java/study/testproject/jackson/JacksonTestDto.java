package study.testproject.jackson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JacksonTestDto {

	@JsonProperty(access = Access.READ_ONLY)
	private String readOnly;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String writeOnly;

	private String readAndWrite;

	private List<String> strList;

	@JsonIgnore
	private String ignore;

}