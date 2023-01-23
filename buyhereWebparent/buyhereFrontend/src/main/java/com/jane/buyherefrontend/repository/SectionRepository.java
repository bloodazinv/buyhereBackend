/**
 * FileName: SectionRepository
 * Author: jane
 * Date: 2023/1/14 16:12
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.repository;

import com.jane.buyherecommon.entity.section.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    // list sections by enabled status and sorted by order in ascending order
    public List<Section> findAllByEnabledOrderBySectionOrderAsc(boolean enabled);
}