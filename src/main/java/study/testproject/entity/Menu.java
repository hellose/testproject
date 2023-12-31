package study.testproject.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.testproject.type.UseState;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "MenuGenerator", sequenceName = "menu_seq")
@Entity
public class Menu extends DateAudit{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MenuGenerator")
	private Long menuId;
	
	@Column(nullable = false)
	private String menuName;
	
	private String menuUrl;
	
	private Integer menuOrder;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UseState useState;

//	@ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentMenuId")
	private Menu parentMenu;

	@OrderBy(value = "menu_order asc")
	@OneToMany(mappedBy = "parentMenu")
	private List<Menu> childMenus;
}
