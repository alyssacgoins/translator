package com.multi_input.translator.service.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 * Generate Excel file containing word/translation pairs.
 */
@Slf4j
@Service
public class ExcelGenerator {

  private static final int ZERO = 0;
  private static final int ONE = 1;

  private static final String OUTPUT_EXCEL_FILE_NAME = "output.xlsx";

  private final XSSFWorkbook workbook = new XSSFWorkbook();
  private int currentSheet = 0;

  /**
   * Add a new sheet to Excel workbook filled with data from input map.
   *
   * @param map Map
   */
  public void addExcelSheetToWorkbookFromMap(Map<String, String> map) {
    XSSFSheet sheet = workbook.getSheetAt(currentSheet);
    currentSheet ++;

    int rowNum= 0;
    for (Map.Entry<String, String> entry : map.entrySet()) {

      XSSFRow row = sheet.createRow(rowNum);

      XSSFCell cellTranslation = row.createCell(ZERO);
      cellTranslation.setCellValue(entry.getKey());

      XSSFCell cellOriginal = row.createCell(ONE);
      cellOriginal.setCellValue(entry.getValue());

      rowNum ++;
    }
  }

  /**
   * Return true if Excel workbook is successfully written to Excel file.
   *
   * @return boolean
   */
  public boolean writeWorkbookToExcelFile() {
    boolean wroteToFile = false;

    //todo
    try (FileOutputStream out = new FileOutputStream(new File(OUTPUT_EXCEL_FILE_NAME))) {
      workbook.write(out);
      workbook.close();

      wroteToFile = true;
    } catch (IOException e) {
      //todo
      log.error("EXCEL_GENERATOR_ERROR_001", e);
    }

    return wroteToFile;
  }

  //todo remove.
  public void generateExcelFromMap(Map<String, String> map) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet();

    int rowNum = 0;
    for (Map.Entry<String, String> entry : map.entrySet()) {

      XSSFRow row = sheet.createRow(rowNum);

      XSSFCell cellEN = row.createCell(ZERO);
      cellEN.setCellValue(entry.getKey());

      XSSFCell cellDE = row.createCell(ONE);
      cellDE.setCellValue(entry.getValue());

      rowNum ++;
    }

    try (FileOutputStream out = new FileOutputStream(new File(OUTPUT_EXCEL_FILE_NAME))) {
      workbook.write(out);
      workbook.close();
    } catch (IOException e) {
      //todo
      log.error("EXCEL_GENERATOR_ERROR_001", e);
    }
  }
}
