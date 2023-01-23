/**
 * FileName: SettingRepository
 * Author: jane
 * Date: 2023/1/16 11:22
 * Description:
 * Version:
 */
package com.jane.buyherebackend.repository;

import com.jane.buyherecommon.entity.setting.Setting;
import com.jane.buyherecommon.entity.setting.SettingCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SettingRepository extends CrudRepository<Setting, String> {

    public List<Setting> findByCategory(SettingCategory category);
}
