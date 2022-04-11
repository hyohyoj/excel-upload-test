package com.ho.lec24.poi.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ho.lec24.poi.ExcelData;
import com.ho.lec24.poi.service.ExcelService;

@Controller
@RequestMapping("/excel")
public class ExcelController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ExcelService excelService;

	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void excelUpload(MultipartHttpServletRequest request, ExcelData excelData) {
		String excelType = request.getParameter("excelType");

		// 엑셀 파일이 xls, xlsx일 때 서비스 라우팅
		if (excelType.equals("xlsx")) {
			excelService.xlsxExcelReader(request);
		} else if (excelType.equals("xls")) {
			excelService.xlsExcelReader(request);
		}
		
	}

	
}
