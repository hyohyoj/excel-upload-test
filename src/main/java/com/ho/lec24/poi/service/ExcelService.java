package com.ho.lec24.poi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ho.lec24.poi.ExcelData;
import com.ho.lec24.poi.dao.ExcelDao;

@Service
public class ExcelService {

	@Autowired
	ExcelDao excelDao;

	public void xlsxExcelReader(MultipartHttpServletRequest request) {
		List<ExcelData> list = new ArrayList<>();

		MultipartFile file = request.getFile("excel");
		XSSFWorkbook workbook = null;

		try {
			// XSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
			workbook = new XSSFWorkbook(file.getInputStream());

			// 탐색에 사용할 Sheet, Row, Cell 객체
			XSSFSheet curSheet;
			XSSFRow curRow;
			XSSFCell curCell;
			ExcelData excelData;

			// Sheet 탐색 for문
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {

				// 현재 sheet 반환
				curSheet = workbook.getSheetAt(sheetIndex);

				// row 탐색 for문
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {

					// row 0은 헤더정보이기 때문에 무시
					if (rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						excelData = new ExcelData();
						String value;

						// row의 첫번째 cell값이 비어있지 않는 경우만 cell탐색
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {

								// cell 탐색 for문
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);

									if (true) {
										value = "";

										// cell 스타일이 다르더라도 String으로 반환 받음
										switch (curCell.getCellType()) {
										case XSSFCell.CELL_TYPE_FORMULA:
											value = curCell.getCellFormula();
											break;
										case XSSFCell.CELL_TYPE_NUMERIC:
											value = curCell.getNumericCellValue() + "";
											break;
										case XSSFCell.CELL_TYPE_STRING:
											value = curCell.getStringCellValue() + "";
											break;
										case XSSFCell.CELL_TYPE_BLANK:
											value = curCell.getBooleanCellValue() + "";
											break;
										case XSSFCell.CELL_TYPE_ERROR:
											value = curCell.getErrorCellValue() + "";
											break;
										default:
											value = new String();
											break;
										} // end switch

										// 현재 column index에 따라서 data입력
										switch (cellIndex) {
										case 0: // 이름
											excelData.setName(value);
											break;
										case 1: // 전화번호
											excelData.setPhone(value);
											break;
										case 2: // 이메일
											excelData.setEmail(value);
											break;
										default:
											break;
										}

									} // end if

								} // end for

								// cell 탐색 이후 vo 추가
								list.add(excelData);

							} // end

						} // end if

					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 디비에 insert
		excelDao.excelInsert(list);

	}

	public void xlsExcelReader(MultipartHttpServletRequest request) {
		List<ExcelData> list = new ArrayList<>();

		MultipartFile file = request.getFile("excel");
		HSSFWorkbook workbook = null;
		
		try {
			// HSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
			workbook = new HSSFWorkbook(file.getInputStream());
			
			// 탐색에 사용할 Sheet, Row, Cell 객체
			HSSFSheet curSheet;
			HSSFRow curRow;
			HSSFCell curCell;
			ExcelData excelData;
	
			// Sheet 탐색
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				//현재 sheet 반환
				curSheet = workbook.getSheetAt(sheetIndex);
				
				// row 탐색
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row = 0은 보통 헤더 정보이기 때문에 넘김
					if(rowIndex != 0) {
						curRow = curSheet.getRow(rowIndex);
						excelData = new ExcelData();
						String value;

						// row의 첫번째 cell 값이 비어있지 않은 경우만 cell 탐색
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).getStringCellValue())) {
								// cell 탐색
								for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
									curCell = curRow.getCell(cellIndex);

									if (true) {
										value = "";

										// cell 스타일이 다르더라도 String으로 반환 받음
										switch (curCell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA:
											value = curCell.getCellFormula();
											break;
										case HSSFCell.CELL_TYPE_NUMERIC:
											value = curCell.getNumericCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_STRING:
											value = curCell.getStringCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK:
											value = curCell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_ERROR:
											value = curCell.getErrorCellValue() + "";
											break;
										default:
											value = new String();
											break;
										} // end switch

										// 현재 column index에 따라서 data 입력
										switch (cellIndex) {
										case 0: // 이름
											excelData.setName(value);
											break;
										case 1: // 전화번호
											excelData.setPhone(value);
											break;
										case 2: // 이메일
											excelData.setEmail(value);
											break;
										default:
											break;
										} // end switch

									} // end if

								} // end for

								// cell 탐색 이후 data 추가
								list.add(excelData);

							} // end if
						} // end if
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		// DB에 insert
		excelDao.excelInsert(list);

	}
}
