package com.jane.buyherecommon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reviews_votes")
@Getter
@Setter
@NoArgsConstructor
public class ReviewVote extends IdBasedEntity {

	private static final int VOTE_UP_POINT = 1;
	private static final int VOTE_DOWN_POINT = -1;

	private int votes;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;
	
	public void voteUp() {
		this.votes = VOTE_UP_POINT;
	}

	public void voteDown() {
		this.votes = VOTE_DOWN_POINT;
	}
	
	@Transient
	public boolean isUpvoted() {
		return this.votes == VOTE_UP_POINT;
	}

	@Transient
	public boolean isDownvoted() {
		return this.votes == VOTE_DOWN_POINT;
	}
	
	@Override
	public String toString() {
		return "ReviewVote [votes=" + votes + ", customer=" + customer.getFullName() + 
				", review=" + review.getId() + "]";
	}
}
