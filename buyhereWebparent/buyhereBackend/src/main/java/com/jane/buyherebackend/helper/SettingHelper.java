/**
 * FileName: SettingHelper
 * Author: jane
 * Date: 2023/1/16 11:25
 * Description:
 * Version:
 */

package com.jane.buyherebackend.helper;

import com.jane.buyherebackend.repository.CurrencyRepository;
import com.jane.buyherebackend.service.SettingService;
import com.jane.buyherebackend.util.AmazonS3Util;
import com.jane.buyherebackend.util.FileUploadUtil;
import com.jane.buyherebackend.util.GeneralSettingBag;
import com.jane.buyherecommon.entity.Currency;
import com.jane.buyherecommon.entity.setting.Setting;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SettingHelper {

    public static void saveSiteLogo(MultipartFile multipartFile, GeneralSettingBag settingBag) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String value = "/site-logo/" + fileName;
            settingBag.updateSiteLogo(value);

			// Image Folder
			// String uploadDir = "buyhereWebparent/site-logo/";
			// FileUploadUtil.cleanDir(uploadDir);
			// FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


            // Amazon S3 Image Storage
            String uploadDir = "site-logo";
            AmazonS3Util.removeFolder(uploadDir);
            AmazonS3Util.uploadFile(uploadDir, fileName, multipartFile.getInputStream());
        }
    }

    public static void saveCurrencySymbol(HttpServletRequest request, GeneralSettingBag settingBag, CurrencyRepository currencyRepo) {
        Integer currencyId = Integer.parseInt(request.getParameter("CURRENCY_ID"));
        Optional<Currency> findByIdResult = currencyRepo.findById(currencyId);

        if (findByIdResult.isPresent()) {
            Currency currency = findByIdResult.get();
            settingBag.updateCurrencySymbol(currency.getSymbol());
        }
    }

    public static void updateSettingValuesFromForm(HttpServletRequest request, List<Setting> listSettings, SettingService service) {
        for (Setting setting : listSettings) {
            String value = request.getParameter(setting.getKey());
            if (value != null) {
                setting.setValue(value);
            }
        }

        service.saveAll(listSettings);
    }
}
