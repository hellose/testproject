package study.testproject.jackson;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@JsonTest
@Slf4j
@ActiveProfiles("test")
public class JacksonTest {

	@Autowired
	private ObjectMapper mapper;
	
	@Test
	@DisplayName("직렬화 테스트")
	public void testSeriatlize() throws IOException {
		JacksonTestDto dto = JacksonTestDto.builder()
//											.readAndWrite("직렬화 역직렬화 모두 포함됨")
											.readAndWrite(null)
											.writeOnly("역직렬화만 포함됨")
											.readOnly("직렬화만 포함됨")
											.ignore("직렬화 역직렬화 모두 무시됨")
											//List가 null인 경우 -> null
//											.strList(null)
											//List객체만 생성된 경우 -> []
											.strList(new ArrayList<>())
											.build();
		
		String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
		log.debug(result);
	}
	
	@Test
	@DisplayName("역직렬화 테스트")
	public void testDeserialize() throws IOException{
		String json = "{\"readOnly\":\"값\",\"writeOnly\":\"값\",\"readAndWrite\":\"값\",\"ignore\":\"값\",\"strList\":[\"test1\"]}";
		System.out.println("json: " + json);
		
		JacksonTestDto dto = mapper.readValue(json, JacksonTestDto.class);
		System.out.println(dto);
	}
	
	@Test
	@DisplayName("JSON 생성 테스트")
	public void testDeserialize2() {
		ObjectNode obj = mapper.createObjectNode();
		obj.put("id", 1);
		obj.put("name", "hyun");
		System.out.println(obj.toPrettyString());
	}

}