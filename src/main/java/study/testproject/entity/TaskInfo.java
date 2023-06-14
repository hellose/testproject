package study.testproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@SequenceGenerator(name = "TaskInfoGenerator", sequenceName = "task_info_seq")
public class TaskInfo extends DateAudit {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TaskInfoGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long taskInfoSeq;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_group_seq")
	private BusinessGroup businessGroup;
}
