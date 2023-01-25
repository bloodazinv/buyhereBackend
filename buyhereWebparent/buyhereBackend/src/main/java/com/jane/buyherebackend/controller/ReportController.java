package com.jane.buyherebackend.controller;


import com.jane.buyherebackend.service.SettingService;
import com.jane.buyherebackend.util.ReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReportController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired 
	private SettingService settingService;
	
	
	@GetMapping("/reports")
	public String viewSalesReportHome(HttpServletRequest request) {
		
		LOGGER.info("ReportController | viewSalesReportHome is called");
		ReportUtil.loadCurrencySetting(request,settingService);
		
		return "reports/reports";
	}
	
	
}
