/**
 * FileName: ISettingService
 * Author: jane
 * Date: 2023/1/16 11:21
 * Description:
 * Version:
 */
package com.jane.buyherebackend.service.impl;

import com.jane.buyherebackend.util.GeneralSettingBag;
import com.jane.buyherecommon.entity.setting.Setting;

import java.util.List;

public interface ISettingService {

    public List<Setting> listAllSettings();

    public GeneralSettingBag getGeneralSettings();

    public void saveAll(Iterable<Setting> settings);

    public List<Setting> getMailServerSettings();

    public List<Setting> getMailTemplateSettings();

    public List<Setting> getCurrencySettings();

    public List<Setting> getPaymentSettings();
}
