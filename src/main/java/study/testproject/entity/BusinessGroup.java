package study.testproject.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
//@SequenceGenerator(name = "BusinessGroupGenerator", sequenceName = "bussiness_group_seq")
public class BusinessGroup extends DateAudit {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BusinessGroupGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long businessGroupSeq;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "businessGroup")
	private List<TaskInfo> taskInfos;
}