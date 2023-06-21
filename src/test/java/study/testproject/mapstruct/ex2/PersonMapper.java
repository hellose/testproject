package study.testproject.mapstruct.ex2;

import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/*
 * Custom Mapping Method 
 */

//@Mapper(componentModel = "spring")
public interface PersonMapper {

	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	@Mapping(target = "name", qualifiedByName = "one")
	@Mapping(target = "fatherName", qualifiedByName = "two")
	@Mapping(target = "motherName", qualifiedByName = "three")
	Person dtoToEntity(PersonDto personDto);

	@Named("one")
	default String nameSetterOne(String str) {
		return "이름1";
	}

	@Named("two")
	default String nameSetterTwo(String str) {
		return "이름2";
	}

	@Named("three")
	default String nameSetterThree(String str) {
		return "이름3";
	}

}
