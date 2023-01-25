/**
 * FileName: IReviewService
 * Author: jane
 * Date: 2023/1/25 12:46
 * Description:
 * Version:
 */
package com.jane.buyherebackend.service.impl;

import com.jane.buyherecommon.entity.Review;
import com.jane.buyherecommon.exception.ReviewNotFoundException;

public interface IReviewService {

    public Review get(Integer id) throws ReviewNotFoundException;
    public void save(Review reviewInForm);
    public void delete(Integer id) throws ReviewNotFoundException;
}
