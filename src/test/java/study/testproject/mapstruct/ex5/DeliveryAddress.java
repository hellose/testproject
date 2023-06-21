package study.testproject.mapstruct.ex5;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class DeliveryAddress {

	private String forename;
	private String surname;
	private String street;
	private String postalcode;
	private String county;
}