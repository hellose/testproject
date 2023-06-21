package study.testproject.mapstruct.ex1;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/*
 * @BeanMapping 어노테이션 테스트
 */

//@Mapper(componentModel = "spring")
public interface PersonMapper {

	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
	
	
	@BeanMapping(ignoreByDefault = true) //-> @Mapping어노테이션을 명시하지 않은 필드들은 모두 무시 
	Person dtoToEntityNoField(PersonDto personDto); 
	
	@BeanMapping(ignoreByDefault = true)
	@Mapping(target="age") //-> @Mapping어노테이션을 명시한 필드만 셋팅됨 (ignore 기본값 false)
	Person dtoToEntityOnlyAgeField(PersonDto personDto);
	
	Person dtoToEntityAllField(PersonDto personDto);
	
	@BeanMapping(ignoreByDefault = true)
	@Mapping(target="name", ignore = false)
	@Mapping(target="age", ignore = false)
	@Mapping(target="motherName", ignore = true) //-> 명시했지만 ignore true라 미셋팅
	Person dtoToEntityNameAndAge(PersonDto personDto);
}
