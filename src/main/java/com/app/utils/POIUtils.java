package com.app.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIUtils {

    /**
     * 
     * @param isIE
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getDownLoadFileNameByBrowserType(boolean isIE, String fileName) {
        String r = fileName;
        if (isIE) {
            try {
                r = new String(fileName.getBytes("GBK"), "ISO8859_1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                r = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    /**
     * 判断浏览器 是否是IE
     * 
     * @param request
     * @return
     */
    public static boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 ? true : false;
    }

    /**
     * 设置
     * ContentType/Header/Encoding...
     * 
     * @param request
     * @param response
     * @param fileName
     */
    public static void downPrepare(HttpServletRequest request, HttpServletResponse response, String fileName) {

        boolean isIE = POIUtils.isIE(request);
        fileName = POIUtils.getDownLoadFileNameByBrowserType(isIE, fileName);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("application/x-msdownload");
        
    }
    
    public static void output(HttpServletRequest request,
            HttpServletResponse response, String fileName, XSSFWorkbook wb) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO logger
            e1.printStackTrace();
        }
        response.setContentType("application/x-msdownload");
        boolean isIE = POIUtils.isIE(request);
        try {
            fileName = POIUtils.getDownLoadFileNameByBrowserType(isIE, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;filename="
                + fileName);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            wb.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void setRegionStyle(Sheet sheet, CellRangeAddress region, CellStyle cs) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            Row row = CellUtil.getRow(i, sheet);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                Cell cell = CellUtil.getCell(row, (short) j);
                cell.setCellStyle(cs);
            }
        }
    }

    public static void colSpan(Sheet sheet, short rownum) {
        short fromIndex = 0, toIndex = 0;
        Row row = sheet.getRow(rownum);
        String v = "";
        for (short i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null
                    && (cell.getCellType() != HSSFCell.CELL_TYPE_STRING || !cell.getStringCellValue().equals(v))) {
                if (toIndex > fromIndex) {
                    CellRangeAddress region = new CellRangeAddress(rownum, rownum, fromIndex, toIndex);
                    sheet.addMergedRegion(region);
                }
                fromIndex = i;
                v = cell.getStringCellValue();
            } else {
                toIndex = i;
            }
        }
        if (toIndex > fromIndex) {
            CellRangeAddress region = new CellRangeAddress(rownum, rownum, fromIndex, toIndex);
            sheet.addMergedRegion(region);
        }
    }

    @SuppressWarnings("deprecation")
    public static void rowSpan(HSSFSheet sheet, short column) {
        short fromIndex = 0, toIndex = 0;
        String v = "";
        for (short i = 0; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            HSSFCell cell = row != null ? row.getCell(column) : null;
            if (cell != null
                    && (cell.getCellType() != HSSFCell.CELL_TYPE_STRING || !cell.getStringCellValue().equals(v))) {
                if (toIndex > fromIndex) {
                    Region region = new Region(fromIndex, column, toIndex, column);
                    sheet.addMergedRegion(region);
                }
                fromIndex = i;
                v = cell.getStringCellValue();
            } else {
                toIndex = i;
            }
        }
        if (toIndex > fromIndex) {
            Region region = new Region(fromIndex, column, toIndex, column);
            sheet.addMergedRegion(region);
        }
    }

    // toColumn设置为255则表示到最后一列
    @SuppressWarnings("deprecation")
    public static void colSpan(HSSFSheet sheet, int rownum, short startColumn, short toColumn) {
        short fromIndex = 0, toIndex = 0;
        HSSFRow row = sheet.getRow(rownum);
        if (toColumn > row.getLastCellNum()) {
            toColumn = row.getLastCellNum();
        }
        String v = "";
        for (short i = 0; i < toColumn; i++) {
            HSSFCell cell = row.getCell(i);
            if (cell != null
                    && (cell.getCellType() != HSSFCell.CELL_TYPE_STRING || !cell.getStringCellValue().equals(v))) {
                if (toIndex > fromIndex) {
                    Region region = new Region(rownum, fromIndex, rownum, toIndex);
                    sheet.addMergedRegion(region);
                }
                fromIndex = i;
                v = cell.getStringCellValue();
            } else {
                toIndex = i;
            }
        }
        if (toIndex > fromIndex) {
            Region region = new Region(rownum, fromIndex, rownum, toIndex);
            sheet.addMergedRegion(region);
        }
    }

    public static void rowSpan(Sheet sheet, short column, int startRow, int toRow) {
        int fromIndex = 0, toIndex = 0;
        if (toRow > sheet.getLastRowNum()) {
            toRow = sheet.getLastRowNum();
        }
        String v = "";
        for (int i = startRow; i <= toRow; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row != null ? row.getCell(column) : null;
            if (cell != null
                    && (cell.getCellType() != HSSFCell.CELL_TYPE_STRING || !cell.getStringCellValue().equals(v))) {
                if (toIndex > fromIndex) {
                    CellRangeAddress region = new CellRangeAddress(fromIndex, toIndex, column, column);
                    sheet.addMergedRegion(region);
                }
                fromIndex = i;
                v = cell.getStringCellValue();
            } else {
                toIndex = i;
            }
        }
        if (toIndex > fromIndex) {
            CellRangeAddress region = new CellRangeAddress(fromIndex, toIndex, column, column);
            sheet.addMergedRegion(region);
        }
    }

    public static void clearSheetBody(Sheet sheet, int fromrow) {
        // 清除模板中的数据
        for (int i = fromrow; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i) != null) {
                sheet.removeRow(sheet.getRow(i));
            }
        }
    }

    public static void clearSheetBody(Sheet sheet, int fromRow, int toRow) {
        // 清除模板中的数据
        for (int i = fromRow; i <= toRow; i++) {
            if (sheet.getRow(i) != null) {
                sheet.removeRow(sheet.getRow(i));
            }
        }
    }

    public static void clearSheetBody(Sheet sheet, int fromRow, int toRow, int fromCell, int toCell) {
        // 清除模板中的数据
        for (int i = fromRow; i <= toRow; i++) {
            for (int j = fromCell; j <= toCell; j++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(j) != null) {
                    row.removeCell(row.getCell(j));
                }
            }
        }
    }

    // 清除所有的区域
    public static void clearAllRegion(Sheet sheet) {
        int n = sheet.getNumMergedRegions();
        for (int i = n - 1; i >= 0; i--) {
            sheet.removeMergedRegion(i);
        }
    }

    /**
     * 
     * @param sheet
     *            区域所在的sheet
     * @param rownum
     *            指定单元格所在行
     * @param column
     *            指定单元格所在列 删除指定单元格所在的区域
     */
    @SuppressWarnings("deprecation")
    public static void clearRegionAssign(HSSFSheet sheet, int rownum, int column) {
        int n = sheet.getNumMergedRegions();
        for (int i = n; i >= 0; i--) {
            Region region = sheet.getMergedRegionAt(i);
            if (region.getRowFrom() <= rownum && region.getRowTo() >= rownum && region.getColumnFrom() <= column
                    && region.getColumnTo() >= column) {
                sheet.removeMergedRegion(i);
                break;
            }
        }
    }

    public static void clearAllComment(Sheet sheet) {
        /*
         * int rowIndex = sheet.getLastRowNum(); int colIndex =
         * sheet.getRow(0).getLastCellNum(); for(int i=0;i<=rowIndex;i++){
         * for(int j=0;j<=colIndex;j++){ HSSFComment comment =
         * sheet.getCellComment(i, j); if(comment!=null){
         * sheet.getRow(i).getCell(j).removeCellComment(); } } }
         */
        int rowIndex = sheet.getLastRowNum();
        int colIndex = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = 0; j <= colIndex; j++) {
                Comment comment = sheet.getCellComment(i, j);
                if (comment != null) {
                    sheet.getRow(i).getCell(j).removeCellComment();
                }
            }
        }
    }

    public static String getCellNumLetterView(int cellnum) {
        if (cellnum < 0) {
            throw new RuntimeException("不存在的列的编号: " + cellnum);
        }
        if (cellnum == 0) {
            return "A";
        }
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String letterView = "";
        while (cellnum > 0) {
            int c = cellnum % letters.length();
            if (letterView.length() > 0) {
                c--;
            }
            letterView = letters.charAt(c) + letterView;
            cellnum = cellnum / letters.length();
        }
        return letterView;
    }

    @SuppressWarnings("deprecation")
    public static void copyRows(HSSFSheet sourceSheet, HSSFSheet targetSheet, int pStartRow, int pEndRow, int pPosition) {
        HSSFRow sourceRow = null;
        HSSFRow targetRow = null;
        HSSFCell sourceCell = null;
        HSSFCell targetCell = null;
        // HSSFSheet sourceSheet = null;
        // HSSFSheet targetSheet = null;
        Region region = null;

        if ((pStartRow == -1) || (pEndRow == -1)) {
            return;
        }
        // sourceSheet = wb.getSheetAt(pSourceSheetId);
        // targetSheet = wb.getSheetAt(pTargetSheetId);
        // 拷贝合并的单元格
        for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
            region = sourceSheet.getMergedRegionAt(i);
            if ((region.getRowFrom() >= pStartRow) && (region.getRowTo() <= pEndRow)) {
                int targetRowFrom = region.getRowFrom() - pStartRow + pPosition;
                int targetRowTo = region.getRowTo() - pStartRow + pPosition;
                region.setRowFrom(targetRowFrom);
                region.setRowTo(targetRowTo);
                targetSheet.addMergedRegion(region);
            }
        }
        // 拷贝行并填充数据
        for (int i = pStartRow; i <= pEndRow; i++) {
            sourceRow = sourceSheet.getRow(i);
            if (sourceRow == null) {
                continue;
            }
            targetRow = targetSheet.createRow(i - pStartRow + pPosition);
            targetRow.setHeight(sourceRow.getHeight());
            for (short j = sourceRow.getFirstCellNum(); j < sourceRow.getPhysicalNumberOfCells(); j++) {
                sourceCell = sourceRow.getCell(j);
                if (sourceCell == null) {
                    continue;
                }
                targetCell = targetRow.createCell(j);
                targetCell.setCellStyle(sourceCell.getCellStyle());
                int cType = sourceCell.getCellType();
                targetCell.setCellType(cType);
                switch (cType) {
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    targetCell.setCellValue(sourceCell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    targetCell.setCellFormula(sourceCell.getCellFormula());
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    targetCell.setCellValue(sourceCell.getStringCellValue());
                    break;
                }
            }
        }
    }
}
