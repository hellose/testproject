package study.testproject.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.testproject.type.UseState;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuDto {
	
	private Long menuId;
	
	private String menuName;
	
	private String menuUrl;
	
	private Integer menuOrder;
	
	private UseState useState;
	
//	@JsonProperty(access = Access.WRITE_ONLY)
//	@JsonProperty(access = Access.READ_ONLY)
//	@JsonIgnore
	private MenuDto parentMenu;
	
	private List<MenuDto> childMenus;
	
	@JsonProperty(access = Access.READ_ONLY)
	private List<MenuIdName> menuIdNames;
}
