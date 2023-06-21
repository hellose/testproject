package study.testproject.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.testproject.entity.BizGroup;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizGroupDto {

	private Long bizGroupId;
	private String name;
	private BizGroup parentBizGroup;
	private List<BizGroup> childBizGroups;
}
