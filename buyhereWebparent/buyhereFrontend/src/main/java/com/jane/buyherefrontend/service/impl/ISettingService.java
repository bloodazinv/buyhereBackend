/**
 * FileName: ISettingService
 * Author: jane
 * Date: 2023/1/17 13:52
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service.impl;

import com.jane.buyherecommon.entity.EmailSettingBag;
import com.jane.buyherecommon.entity.setting.Setting;
import com.jane.buyherefrontend.setting.CurrencySettingBag;
import com.jane.buyherefrontend.setting.PaymentSettingBag;

import java.util.List;

public interface ISettingService {

    public List<Setting> getGeneralSettings();
    public EmailSettingBag getEmailSettings();
    public CurrencySettingBag getCurrencySettings();
    public String getCurrencyCode();
    public PaymentSettingBag getPaymentSettings();
}
