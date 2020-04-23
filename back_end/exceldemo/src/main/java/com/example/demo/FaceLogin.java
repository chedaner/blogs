package com.example.demo;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tt")
public class FaceLogin {

	private static final Logger logger = LoggerFactory.getLogger(FaceLogin.class);

	/**
	 * 用于excel下载
	 *
	 * @return
	 */
	@RequestMapping(value = "/tt")
	public JSONObject getAmountAndOrderNumExcel( HttpServletResponse response) {
		List<creditRate> creditRateList = new ArrayList<creditRate>();
		creditRate creditRateTemp1 = new creditRate("rate1","year1");
		creditRateList.add(creditRateTemp1);
		creditRate creditRateTemp2 = new creditRate("rate2","year2");
		creditRateList.add(creditRateTemp2);
		EasyPoiUtil.exportExcel(creditRateList,"测试","sheet1",creditRate.class,"excel.xls",response);
		return null;
	}
}
