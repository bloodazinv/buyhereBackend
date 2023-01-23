/**
 * FileName: SectionService
 * Author: jane
 * Date: 2023/1/14 16:11
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.service;

import com.jane.buyherecommon.entity.section.Section;
import com.jane.buyherefrontend.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SectionService {

    @Autowired
    private SectionRepository repo;

    public List<Section> listEnabledSections() {
        return repo.findAllByEnabledOrderBySectionOrderAsc(true);
    }


}
