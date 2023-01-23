/**
 * FileName: SettingService
 * Author: jane
 * Date: 2023/1/17 13:52
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.service;

import com.jane.buyherecommon.entity.Currency;
import com.jane.buyherecommon.entity.EmailSettingBag;
import com.jane.buyherecommon.entity.setting.Setting;
import com.jane.buyherecommon.entity.setting.SettingCategory;
import com.jane.buyherefrontend.repository.CurrencyRepository;
import com.jane.buyherefrontend.repository.SettingRepository;
import com.jane.buyherefrontend.service.impl.ISettingService;
import com.jane.buyherefrontend.setting.CurrencySettingBag;
import com.jane.buyherefrontend.setting.PaymentSettingBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SettingService implements ISettingService {

    private SettingRepository settingRepo;

    private CurrencyRepository currencyRepo;

    @Autowired
    public SettingService(SettingRepository settingRepo, CurrencyRepository currencyRepo) {
        super();
        this.settingRepo = settingRepo;
        this.currencyRepo = currencyRepo;
    }

    @Override
    public List<Setting> getGeneralSettings() {
        // TODO Auto-generated method stub
        return settingRepo.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
    }

    @Override
    public EmailSettingBag getEmailSettings() {
        List<Setting> settings = settingRepo.findByCategory(SettingCategory.MAIL_SERVER);
        settings.addAll(settingRepo.findByCategory(SettingCategory.MAIL_TEMPLATES));

        return new EmailSettingBag(settings);
    }

    @Override
    public CurrencySettingBag getCurrencySettings() {
        List<Setting> settings = settingRepo.findByCategory(SettingCategory.CURRENCY);
        return new CurrencySettingBag(settings);
    }

    @Override
    public PaymentSettingBag getPaymentSettings() {
        List<Setting> settings = settingRepo.findByCategory(SettingCategory.PAYMENT);
        return new PaymentSettingBag(settings);
    }

    @Override
    public String getCurrencyCode() {
        Setting setting = settingRepo.findByKey("CURRENCY_ID");
        Integer currencyId = Integer.parseInt(setting.getValue());
        Currency currency = currencyRepo.findById(currencyId).get();

        return currency.getCode();
    }

}
