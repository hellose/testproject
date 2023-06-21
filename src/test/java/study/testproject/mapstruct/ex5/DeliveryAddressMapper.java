package study.testproject.mapstruct.ex5;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/*
 * source가 두개이상인 경우
 */

//@Mapper(componentModel = "spring")
public interface DeliveryAddressMapper {

	DeliveryAddressMapper INSTANCE = Mappers.getMapper(DeliveryAddressMapper.class);

	// source: Customer, Address
	// target: DeliveryAddress
	
	@Mapping(source = "customer.firstName", target = "forename")
	@Mapping(source = "customer.lastName", target = "surname")
	
	//밑의 세개 @Mapping은 주석처리해도 소스와 타겟의 필드명이 같아서 암묵적 맵핑이됨
//	@Mapping(source = "address.street", target = "street")
//	@Mapping(source = "address.postalcode", target = "postalcode")
//	@Mapping(source = "address.county", target = "county")
	DeliveryAddress from(Customer customer, Address address);

}