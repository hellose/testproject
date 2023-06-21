package study.testproject.mapstruct.ex3;

import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/*
 * TODO
 */

//@Mapper(componentModel = "spring")
public interface PersonMapper {

	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

//	@Mapping(target = "father", qualifiedByName = "custom")
	Person dtoToEntityWithoutName(PersonDto personDto);

	@Named("custom")
	default Person custom(PersonDto personDto) {
		// 생략
		return null;
	}

}