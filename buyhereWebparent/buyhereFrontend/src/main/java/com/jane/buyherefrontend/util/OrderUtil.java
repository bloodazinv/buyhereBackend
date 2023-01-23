/**
 * FileName: OrderUtil
 * Author: jane
 * Date: 2023/1/21 15:51
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.util;

import com.jane.buyherecommon.entity.EmailSettingBag;
import com.jane.buyherecommon.entity.order.Order;
import com.jane.buyherefrontend.service.SettingService;
import com.jane.buyherefrontend.setting.CurrencySettingBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OrderUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderUtil.class);

    public static void sendOrderConfirmationEmail(HttpServletRequest request, Order order, SettingService settingService)
            throws UnsupportedEncodingException, MessagingException {

        LOGGER.info("OrderUtil | sendOrderConfirmationEmail is started");

        EmailSettingBag emailSettings = settingService.getEmailSettings();
        JavaMailSenderImpl mailSender = CustomerRegisterUtil.prepareMailSender(emailSettings);
        mailSender.setDefaultEncoding("utf-8");

        String toAddress = order.getCustomer().getEmail();
        String subject = emailSettings.getOrderConfirmationSubject();
        String content = emailSettings.getOrderConfirmationContent();

        LOGGER.info("OrderUtil | sendOrderConfirmationEmail | toAddress : " + toAddress);
        LOGGER.info("OrderUtil | sendOrderConfirmationEmail | subject : " + subject);
        LOGGER.info("OrderUtil | sendOrderConfirmationEmail | content : " + content);


        subject = subject.replace("[[orderId]]", String.valueOf(order.getId()));

        LOGGER.info("OrderUtil | sendOrderConfirmationEmail | subject after replace : " + subject);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        helper.setFrom(emailSettings.getFromAddress(), emailSettings.getSenderName());
        helper.setTo(toAddress);
        helper.setSubject(subject);

        DateFormat dateFormatter =  new SimpleDateFormat("HH:mm:ss E, dd MMM yyyy");
        String orderTime = dateFormatter.format(order.getOrderTime());

        CurrencySettingBag currencySettings = settingService.getCurrencySettings();
        String totalAmount = FormatCurrencyUtil.formatCurrency(order.getTotal(), currencySettings);

        content = content.replace("[[name]]", order.getCustomer().getFullName());
        content = content.replace("[[orderId]]", String.valueOf(order.getId()));
        content = content.replace("[[orderTime]]", orderTime);
        content = content.replace("[[shippingAddress]]", order.getShippingAddress());
        content = content.replace("[[total]]", totalAmount);
        content = content.replace("[[paymentMethod]]", order.getPaymentMethod().toString());

        LOGGER.info("OrderUtil | sendOrderConfirmationEmail | content after replace : " + content);

        helper.setText(content, true);
        mailSender.send(message);
    }
}
