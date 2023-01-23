package com.jane.buyherecommon.entity.section;


import com.jane.buyherecommon.entity.Category;
import com.jane.buyherecommon.entity.IdBasedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sections_categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategorySection extends IdBasedEntity {

	@Column(name = "category_order")
	private int categoryOrder;

	@ManyToOne
	@JoinColumn(name = "category_id")	
	private Category category;

}