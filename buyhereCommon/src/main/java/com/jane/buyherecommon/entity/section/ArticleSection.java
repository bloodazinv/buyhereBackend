package com.jane.buyherecommon.entity.section;


import com.jane.buyherecommon.entity.IdBasedEntity;
import com.jane.buyherecommon.entity.article.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sections_articles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleSection extends IdBasedEntity {

	@Column(name = "article_order")
	private int articleOrder;

	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;

}
