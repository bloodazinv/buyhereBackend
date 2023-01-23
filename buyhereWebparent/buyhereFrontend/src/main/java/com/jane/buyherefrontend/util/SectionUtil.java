/**
 * FileName: SectionUtil
 * Author: jane
 * Date: 2023/1/14 16:13
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.util;

import com.jane.buyherecommon.entity.section.Section;
import com.jane.buyherecommon.entity.section.SectionType;

import java.util.List;

public class SectionUtil {

    public static boolean hasAllCategoriesSection(List<Section> listSections) {
        // TODO Auto-generated method stub
        for (Section section : listSections) {
            if (section.getType().equals(SectionType.ALL_CATEGORIES)) {
                return true;
            }
        }

        return false;
    }

}
