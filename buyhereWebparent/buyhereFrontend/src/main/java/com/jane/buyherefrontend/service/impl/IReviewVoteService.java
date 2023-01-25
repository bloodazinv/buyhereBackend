/**
 * FileName: IReviewVoteService
 * Author: jane
 * Date: 2023/1/25 12:55
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service.impl;

import com.jane.buyherecommon.entity.Customer;
import com.jane.buyherecommon.entity.ReviewVote;
import com.jane.buyherecommon.entity.VoteResultDTO;
import com.jane.buyherecommon.entity.VoteType;

public interface IReviewVoteService {

    public VoteResultDTO undoVote(ReviewVote vote, Integer reviewId, VoteType voteType);
    public VoteResultDTO doVote(Integer reviewId, Customer customer, VoteType voteType);

}
