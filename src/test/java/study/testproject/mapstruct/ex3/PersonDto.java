package study.testproject.mapstruct.ex3;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class PersonDto {
	private String name;
	private PersonDto father;
	private PersonDto mother;
}