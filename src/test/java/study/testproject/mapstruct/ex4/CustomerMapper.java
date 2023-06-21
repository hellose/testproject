package study.testproject.mapstruct.ex4;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/*
 * source와 target의 필드명이 다른 경우 
 */

//@Mapper(componentModel = "spring")
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	//맵핑 소스와 타겟의 필드명이 다른 경우
	@Mapping(source = "firstName", target = "forename")
	@Mapping(source = "lastName", target = "surname")
	CustomerDto from(Customer customer);

}