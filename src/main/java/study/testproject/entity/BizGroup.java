package study.testproject.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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
@SequenceGenerator(name = "BizGroupGenerator", sequenceName = "biz_group_seq")
public class BizGroup extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BizGroupGenerator")
	private Long bizGroupId;

	@Column(nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentBizGroupId")
	private BizGroup parentBizGroup;

	@OneToMany(mappedBy = "parentBizGroup")
	private List<BizGroup> childBizGroups;

}