package com.jane.buyherecommon.entity.section;


import com.jane.buyherecommon.entity.Brand;
import com.jane.buyherecommon.entity.IdBasedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sections_brands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandSection extends IdBasedEntity {

	@Column(name = "brand_order")
	private int brandOrder;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

}
