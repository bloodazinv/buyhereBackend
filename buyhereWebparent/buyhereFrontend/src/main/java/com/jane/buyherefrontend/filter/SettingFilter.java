package com.jane.buyherefrontend.filter;


import com.jane.buyherecommon.constants.Constants;
import com.jane.buyherecommon.entity.menu.Menu;
import com.jane.buyherecommon.entity.setting.Setting;
import com.jane.buyherefrontend.service.MenuService;
import com.jane.buyherefrontend.service.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

@Component
@Order(-121) // use this value to fix Logout Error of Customer already signed out
// Default value -100 (https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html -> spring.security.filter.order)
// Select any value less than default value
public class SettingFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SettingFilter.class);
	
	private SettingService service;
	
	private MenuService menuService;
	
	@Autowired 
	public SettingFilter(SettingService service, MenuService menuService) {
		super();
		this.service = service;
		this.menuService = menuService;
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		LOGGER.info("SettingFilter | doFilter is called");
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String url = servletRequest.getRequestURL().toString();
		
		LOGGER.info("SettingFilter | doFilter | url : " + url);

		if (url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".png") ||
				url.endsWith(".jpg")) {
			LOGGER.info("SettingFilter | doFilter | .css , .js , .png , . jpg | url : " + url);
			chain.doFilter(request, response);
			return;
		}
		
		loadGeneralSettings(request);
		loadMenuSettings(request);

		chain.doFilter(request, response);

	}
	
	private void loadMenuSettings(ServletRequest request) {
		
		LOGGER.info("SettingFilter | loadMenuSettings is called");
		
		List<Menu> headerMenuItems = menuService.getHeaderMenuItems();
		LOGGER.info("SettingFilter | loadMenuSettings | headerMenuItems size : " + headerMenuItems.size());
		request.setAttribute("headerMenuItems", headerMenuItems);

		List<Menu> footerMenuItems = menuService.getFooterMenuItems();
		LOGGER.info("SettingFilter | loadMenuSettings | footerMenuItems size : " + footerMenuItems.size());
		request.setAttribute("footerMenuItems", footerMenuItems);		
	}
	
	private void loadGeneralSettings(ServletRequest request) {
		
		LOGGER.info("SettingFilter | loadGeneralSettings is called");
		
		List<Setting> generalSettings = service.getGeneralSettings();

		generalSettings.forEach(setting -> {
			LOGGER.info("SettingFilter | loadGeneralSettings | generalSettings : " + generalSettings);
			request.setAttribute(setting.getKey(), setting.getValue());
		});

		request.setAttribute("S3_BASE_URI", Constants.S3_BASE_URI);


	}

}
