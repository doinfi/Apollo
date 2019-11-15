package com.yf.coros.common.utils.excel;

import com.yf.coros.common.enums.MessageKey;
import com.yf.coros.common.exception.YfException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel 操作类
 */
@Slf4j
public class ExcelUtils {

    /**
     * EXCEL第一行索引
     */
    public static final int EXCEL_FIRST_ROW_INDEX = 0;

    public static final String TRUE_VALUE = "Y";

    public static final String FALSE_VALUE = "N";

    public static final String FILE_ERROR = "文件异常！";

    public static final String INVALID_FILE_FORMAT = "文件格式错误！";

    public static final String ENCRYPTED_FILE = "文件被加密，无法读取！";

    /**
     * 获取单元格字符串内容
     *
     * @param cell
     * @return
     */
    private static String getCellValue(XSSFCell cell) {
        String value = "";
        // 注意：一定要设成这个，否则可能会出现乱码
        //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    if (date != null) {
                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    } else {
                        value = "";
                    }
                } else {
                    Double v = cell.getNumericCellValue();
                    if (v == (v.longValue())) {
                        return Long.toString(v.longValue());
                    }
                    return Double.toString(v);
                }
                break;
            case FORMULA:
                // 导入时如果为公式生成的数据则无值
                if (!StringUtils.equals(cell.getCellFormula(), "")) {
                    try {
                        value = String.valueOf(cell.getNumericCellValue());
                    } catch (Exception ex) {
                        value = cell.getStringCellValue();
                    }
                } else {
                    value = cell.getStringCellValue() + "";
                }
                break;
            case BLANK:
                break;
            case ERROR:
                break;
            case BOOLEAN:
                value = (cell.getBooleanCellValue() ? TRUE_VALUE : FALSE_VALUE);
                break;
            default:
                break;
        }
        return value;
    }


    /**
     * 去掉字符串右边的空格
     *
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    private static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }

    /**
     * 将xls文件解析成字符串list，只支持单sheet不超过7万行
     * @param fileInputStream 文件输入流
     * @return 解析后的结果，结果集中的类型包括：String/DateTime/double/boolean/null <br/>
     *         当文件格式无误，但是内容为空时，返回可能为null
     */
    public static List<LinkedList<Object>> readXlsFiles(InputStream fileInputStream) throws YfException {

        List<LinkedList<Object>> fileInfo = null;
        try {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            // 目前先只支持单sheet
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            DataFormatter dataFormatter = new DataFormatter();
            if (iterator.hasNext()) {
                fileInfo = new LinkedList<>();
                for (Row row : sheet) {
                    // 如果为空行，跳过
                    if (!row.iterator().hasNext()) {
                        continue;
                    }
                    LinkedList<Object> rowList = new LinkedList<>();
                    for (Cell cell : row) {
                        cell.setCellType(CellType.STRING);
                        switch (cell.getCellTypeEnum()) {
                            case STRING: {
                                // String
                                rowList.add(cell.getRichStringCellValue().getString());
                                break;
                            }
                            case NUMERIC: {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    // DateTime
                                    rowList.add(new DateTime(cell.getDateCellValue()));
                                } else {
                                    // double
                                    Double numericCellValue = cell.getNumericCellValue();
                                    if (numericCellValue == numericCellValue.longValue()) {
                                        rowList.add(numericCellValue.longValue());
                                    }
                                    else {
                                        rowList.add(numericCellValue);
                                    }
                                }
                                break;
                            }
                            case FORMULA: {
                                // String
                                rowList.add(dataFormatter.formatCellValue(cell));
                                break;
                            }
                            case BOOLEAN: {
                                // boolean
                                rowList.add(cell.getBooleanCellValue());
                                break;
                            }
                            // 为 _NONE或ERROR时，记为null
                            default: {
                                // null
                                rowList.add(null);
                            }
                        }
                    }
                    fileInfo.add(rowList);
                }
            }
        }
        catch (IOException e) {
            log.warn(FILE_ERROR);
            throw new YfException(e, MessageKey.PARAMETER_ERROR, FILE_ERROR);
        }
        catch (IllegalStateException e) {
            log.warn(ENCRYPTED_FILE);
            throw new YfException(e, MessageKey.PARAMETER_ERROR, ENCRYPTED_FILE);
        }
        catch (InvalidFormatException | IllegalArgumentException e) {
            log.warn(INVALID_FILE_FORMAT);
            throw new YfException(e, MessageKey.PARAMETER_ERROR, INVALID_FILE_FORMAT);
        }
        return fileInfo;
    }

    /**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     *
     * @param inputStream 读取数据的源Excel
     * @param headerIndex 表单头所在的索引
     * @return 读出的Excel中数据的内容
     * @throws IOException
     */
    public static List<Map<String, String>> parse(InputStream inputStream, int headerIndex) throws IOException {
        List<Map<String, String>> result = new ArrayList<>();
        int maxcolumnIndex = 0;
//        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            XSSFSheet st = wb.getSheetAt(sheetIndex);
            List<String> headers = new ArrayList<>();
            for (int rowIndex = 0; rowIndex <= st.getLastRowNum(); rowIndex++) {
                XSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int currentcolumnIndex = row.getLastCellNum() + 1;
                if (currentcolumnIndex > maxcolumnIndex) {
                    maxcolumnIndex = currentcolumnIndex;
                }
                Map<String, String> rows = new LinkedHashMap<>();

                for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        value = getCellValue(cell);
                    }
                    if (rowIndex == headerIndex && value != null && !StringUtils.equals(value.trim(), "")) {
                        headers.add(value.trim());
                    } else {
                        if (columnIndex < headers.size()) {
                            rows.put(headers.get(columnIndex), value);
                        }
                    }
                }

                if (!isEmptyMap(rows)) {
                    result.add(rows);
                }
            }
        }
        return result;
    }


    private static boolean isEmptyMap(Map map) {
        boolean empty = map.isEmpty();
        if (!empty) {
            Collection values = map.values();
            for (Object value : values) {
                if (value != null && !StringUtils.equals(value.toString(), "")) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

//    public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {
//        // 告诉浏览器用什么软件可以打开此文件
//        response.setHeader("content-Type", "application/vnd.ms-excel");
//        // 下载文件的默认名称
//        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
//        exportExcel(data, response.getOutputStream());
//    }
//
//    public static void exportExcel(ExcelDataEntity data, OutputStream out) throws Exception {
//
//        XSSFWorkbook wb = new XSSFWorkbook();
//        try {
//            String sheetName = data.getName();
//            if (null == sheetName) {
//                sheetName = "Sheet1";
//            }
//            XSSFSheet sheet = wb.createSheet(sheetName);
//            writeExcel(wb, sheet, data);
//
//            wb.write(out);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //此处需要关闭 wb 变量
//            out.close();
//        }
//    }

    public static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelDataEntity data) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitleList());
        writeRowsToExcel(wb, sheet, data.getRowList(), rowIndex);
        autoSizeColumns(sheet, data.getTitleList().size() + 1);

    }

    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;

        Font titleFont = wb.createFont();
        titleFont.setFontName("simsun");
        //titleFont.setBoldweight(Short.MAX_VALUE);
        // titleFont.setFontHeightInPoints((short) 14);
        titleFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.white));
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(java.awt.Color.BLACK));

        Row titleRow = sheet.createRow(rowIndex);
        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
        rowIndex++;
        return rowIndex;
    }


    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex = 0;

        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        // dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(java.awt.Color.BLACK));

        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            // dataRow.setHeightInPoints(25);
            colIndex = 0;

            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }

                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    private static void autoSizeColumns(Sheet sheet, int columnNumber) {

        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }

}
