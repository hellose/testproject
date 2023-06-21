package study.testproject.mapstruct.ex3;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class Person {
	private String name;
	private Person father;
	private Person mother;
}
