package study.testproject.mapstruct.ex5;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/*
 * Merge하는 경우(@MappingTarget)
 */

//@Mapper(componentModel = "spring")
public interface DeliveryAddressMapperTwo {

	DeliveryAddressMapperTwo INSTANCE = Mappers.getMapper(DeliveryAddressMapperTwo.class);

	// source: DeliveryAddress, Address
	// target: DeliveryAddress
	// source와 target의 DeliveryAddress는 같은 객체 

	@Mapping(source = "address.postalcode", target = "postalcode")
	@Mapping(source = "address.county", target = "county")
	DeliveryAddress updateAddress(@MappingTarget DeliveryAddress deliveryAddress, Address address);

}