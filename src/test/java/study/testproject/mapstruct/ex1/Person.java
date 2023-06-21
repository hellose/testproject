package study.testproject.mapstruct.ex1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class Person {
	private String name;
	private String fatherName;
	private String motherName;
	private int age;
}
