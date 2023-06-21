package study.testproject.mapstruct.ex2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PersonDtoToPersonTest {

	PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

	PersonDto personDto;
	Person person;

	@BeforeEach
	void init() {
		personDto = new PersonDto();
		personDto.setName("name");
		personDto.setFatherName("father");
		personDto.setMotherName("mother");
		personDto.setAge(30);

		log.debug("PersonDto: {}", personDto);
	}

	@Test
	void test() {
		person = personMapper.dtoToEntity(personDto);
	}

	@AfterEach
	void printPerson() {
		log.debug("Person: {}", person);
	}

}
