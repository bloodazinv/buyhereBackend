/**
 * FileName: SettingController
 * Author: jane
 * Date: 2023/1/16 11:19
 * Description:
 * Version:
 */

package com.jane.buyherebackend.controller;

import com.jane.buyherebackend.helper.SettingHelper;
import com.jane.buyherebackend.repository.CurrencyRepository;
import com.jane.buyherebackend.service.SettingService;
import com.jane.buyherebackend.util.GeneralSettingBag;
import com.jane.buyherecommon.constants.Constants;
import com.jane.buyherecommon.entity.Currency;
import com.jane.buyherecommon.entity.setting.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class SettingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private SettingService service;

    private CurrencyRepository currencyRepo;

    @Autowired
    public SettingController(SettingService service, CurrencyRepository currencyRepo) {
        super();
        this.service = service;
        this.currencyRepo = currencyRepo;
    }

    @GetMapping("/settings")
    public String listAll(Model model) {

        LOGGER.info("SettingController | listAll is called");

        List<Setting> listSettings = service.listAllSettings();
        List<Currency> listCurrencies = currencyRepo.findAllByOrderByNameAsc();

        LOGGER.info("SettingController | listAll | listSettings : " + listSettings.toString());
        LOGGER.info("SettingController | listAll | listCurrencies : " + listCurrencies.toString());

        model.addAttribute("listCurrencies", listCurrencies);

        for (Setting setting : listSettings) {
            LOGGER.info("SettingController | listAll | setting.getKey() : " + setting.getKey() + " | setting.getValue() : " + setting.getValue());
            model.addAttribute(setting.getKey(), setting.getValue());
        }

        model.addAttribute("S3_BASE_URI", Constants.S3_BASE_URI);

        LOGGER.info("SettingController | listAll | S3_BASE_URI : " + Constants.S3_BASE_URI);

        return "settings/settings";
    }

    @PostMapping("/settings/save_general")
    public String saveGeneralSettings(@RequestParam("fileImage") MultipartFile multipartFile,
                                      HttpServletRequest request, RedirectAttributes ra) throws IOException {

        LOGGER.info("SettingController | saveGeneralSettings is called");

        GeneralSettingBag settingBag = service.getGeneralSettings();

        LOGGER.info("SettingController | saveGeneralSettings | settingBag : " + settingBag.toString());

        SettingHelper.saveSiteLogo(multipartFile, settingBag);
        SettingHelper.saveCurrencySymbol(request, settingBag,currencyRepo);

        SettingHelper.updateSettingValuesFromForm(request, settingBag.list(),service);

        ra.addFlashAttribute("messageSuccess", "General settings have been saved.");

        return "redirect:/settings";
    }

    @PostMapping("/settings/save_mail_server")
    public String saveMailServerSetttings(HttpServletRequest request, RedirectAttributes ra) {

        LOGGER.info("SettingController | saveMailServerSetttings is called");

        List<Setting> mailServerSettings = service.getMailServerSettings();

        LOGGER.info("SettingController | saveMailServerSetttings | mailServerSettings : " + mailServerSettings.toString());

        SettingHelper.updateSettingValuesFromForm(request, mailServerSettings,service);

        ra.addFlashAttribute("messageSuccess", "Mail server settings have been saved");

        return "redirect:/settings#mailServer";
    }

    @PostMapping("/settings/save_mail_templates")
    public String saveMailTemplateSetttings(HttpServletRequest request, RedirectAttributes ra) {

        LOGGER.info("SettingController | saveMailTemplateSetttings is called");

        List<Setting> mailTemplateSettings = service.getMailTemplateSettings();

        LOGGER.info("SettingController | saveMailTemplateSetttings | mailTemplateSettings : " + mailTemplateSettings.toString());

        SettingHelper.updateSettingValuesFromForm(request, mailTemplateSettings,service);

        ra.addFlashAttribute("messageSuccess", "Mail template settings have been saved");

        return "redirect:/settings#mailTemplates";
    }

    @PostMapping("/settings/save_payment")
    public String savePaymentSetttings(HttpServletRequest request, RedirectAttributes ra) {

        LOGGER.info("SettingController | savePaymentSetttings is called");

        List<Setting> paymentSettings = service.getPaymentSettings();

        LOGGER.info("SettingController | savePaymentSetttings | paymentSettings : " + paymentSettings.toString());

        SettingHelper.updateSettingValuesFromForm(request, paymentSettings,service);

        ra.addFlashAttribute("messageSuccess", "Payment settings have been saved");

        return "redirect:/settings#payment";
    }
}
