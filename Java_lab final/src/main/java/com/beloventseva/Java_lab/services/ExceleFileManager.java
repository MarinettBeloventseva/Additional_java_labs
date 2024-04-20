package com.beloventseva.Java_lab.services;

import com.beloventseva.Java_lab.models.Item;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;


public class ExceleFileManager {
    private XSSFWorkbook wb = new XSSFWorkbook();
    private XSSFSheet sheet;

    public void ReportExcelWriter() {
        this.wb = new XSSFWorkbook();
        this.sheet = wb.createSheet();
        createTitle();
    }


    public void createRow() {
        ReportExcelWriter();

        RozetkaRarser rozetkaRarser = new RozetkaRarser();
        List<Item> items = rozetkaRarser.parseByQuery();

        for (int i = 0, itemsSize = items.size(); i < itemsSize; i++) {
            XSSFRow row = sheet.createRow(i+1);
            Item item = items.get(i);
            setCellValue(row.createCell(0), item.getName());
            setCellValue(row.createCell(1), item.getPrice());
        }

        try {
            writeWorkbook();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void writeWorkbook() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(Instant.now().getEpochSecond() + ".xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    private void createTitle() {
        XSSFRow rowTitle = sheet.createRow(0);
        setCellValue(rowTitle.createCell(0), "Name");
        setCellValue(rowTitle.createCell(1), "Price");
    }

    private void setCellValue(XSSFCell cell, String value) {
        cell.setCellValue(value);
    }


}
