/**
 * FileName: GeneralSettingBag
 * Author: jane
 * Date: 2023/1/16 11:22
 * Description:
 * Version:
 */

package com.jane.buyherebackend.util;

import com.jane.buyherecommon.entity.setting.Setting;
import com.jane.buyherecommon.entity.setting.SettingBag;

import java.util.List;

public class GeneralSettingBag extends SettingBag {

    public GeneralSettingBag(List<Setting> listSettings) {
        super(listSettings);
    }

    public void updateCurrencySymbol(String value) {
        super.update("CURRENCY_SYMBOL", value);
    }

    public void updateSiteLogo(String value) {
        super.update("SITE_LOGO", value);
    }
}
