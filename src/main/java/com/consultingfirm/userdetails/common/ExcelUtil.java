package com.consultingfirm.userdetails.common;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDate;
import java.util.stream.IntStream;

public class ExcelUtil {

    public static String getStringCellValue(Row row, int cellIndex) {
        var cell = row.getCell(cellIndex);
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            double numericValue = cell.getNumericCellValue();
            if (numericValue == (int) numericValue) {
                return String.valueOf((int) numericValue).trim();
            } else {
                return String.valueOf(numericValue).trim();
            }
        } else {
            return null;
        }
    }

    public static Integer getIntegerCellValue(Row row, int cellIndex) {
        var cell = row.getCell(cellIndex);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else {
            try {
                return Integer.parseInt(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    public static LocalDate getDateCellValue(Row row, int cellIndex) {
        var cell = row.getCell(cellIndex);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } else {
            try {
                return LocalDate.parse(cell.getStringCellValue().trim());
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static boolean isEmptyRow(Row row) {
        return row == null ||
                IntStream.range(0, row.getLastCellNum())
                        .mapToObj(row::getCell)
                        .allMatch(cell -> cell == null || cell.getCellType() == CellType.BLANK);
    }

}
