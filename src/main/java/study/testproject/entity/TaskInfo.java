package study.testproject.entity;

import javax.persistence.SequenceGenerator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
@SequenceGenerator(name = "TaskInfoGenerator", sequenceName = "task_info_seq")
public class TaskInfo extends DateAudit {
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TaskInfoGenerator")
////	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	private Long taskInfoId;
//
//	@Column(nullable = false)
//	private String name;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "biz_group_id")
//	private BizGroupInfo bizGroup;
	
}
