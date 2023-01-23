/**
 * FileName: DirectUtil
 * Author: jane
 * Date: 2023/1/10 10:34
 * Description:
 * Version:
 */

package com.jane.buyherebackend.util;

import com.jane.buyherecommon.entity.User;

public class DirectUtil {
    public static String getRedirectURLtoAffectedUser(User user) {
        //String firstPartOfEmail = user.getEmail().split("@")[0];
        String firstPartOfEmail = user.getEmail();
        return "redirect:/users/page/1?sortField=id&sortDir=asc&keyword=" + firstPartOfEmail;
    }
}
