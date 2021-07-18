package beSen.test.excel;


import beSen.excel.model.ExcelData;
import beSen.test.excel.model.People;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

/**
 * @author 康盼Java开发工程师
 */
public class ExcelTest {

    ExcelData<People> testData = new ExcelData<People>();

    @Before
    public void initData() {
        List<String> propertiesName = Lists.newArrayList("手机", "微信", "QQ");
        List<String> propertiesValue = Lists.newArrayList("13728897992", "13728897992", "997241010");
        People people = new People("康盼", "30", "男", propertiesName, propertiesValue, "java开发工程师");
        List<String> headData = Lists.newArrayList("姓名", "性别", "年龄", "联系方式", "号码", "备注");
        testData.setHeadData(headData);
        testData.setData(Lists.newArrayList(people));
        testData.setSheetName("sheet1");
        testData.setFileName("text.xls");
    }

    @Test
    public void makeExcel() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(testData.getSheetName());
        // 默认第一行开始
        HSSFRow headRow = sheet.createRow(0);
        HSSFCellStyle headCellStyle = workbook.createCellStyle();
        // 设置样式
        headCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headCellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.PALE_BLUE.getIndex());
        // 线条
        headCellStyle.setBorderBottom(BorderStyle.THIN);
        headCellStyle.setBorderTop(BorderStyle.THIN);
        headCellStyle.setBorderLeft(BorderStyle.THIN);
        headCellStyle.setBorderRight(BorderStyle.THIN);
        // 对齐
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置字体
        HSSFFont headFont = workbook.createFont();
        headFont.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        headFont.setFontName("微软雅黑");
        headFont.setBold(true);
        headCellStyle.setFont(headFont);
        // 创建表头
        List<String> headData = testData.getHeadData();
        for (int i = 0; i < headData.size(); i++) {
            HSSFCell cell = headRow.createCell(i);
            cell.setCellValue(headData.get(i));
            cell.setCellStyle(headCellStyle);
        }
        // 开始创建数据列
        List<People> data = testData.getData();
        HSSFCellStyle dataCellStyle = workbook.createCellStyle();
        DataValidationHelper dataValidationHelper = sheet.getDataValidationHelper();
        // 设置字体
        HSSFFont dataFont = workbook.createFont();
        dataFont.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        dataFont.setFontName("微软雅黑");
        dataFont.setBold(false);
        dataCellStyle.setFont(dataFont);
        // 设置样式
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);
        dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 赋值
        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            // 年龄
            HSSFCell $0Cell = row.createCell(0);
            $0Cell.setCellValue(data.get(i).getName());
            $0Cell.setCellStyle(dataCellStyle);
            // 性别
            HSSFCell $1Cell = row.createCell(1);
            $1Cell.setCellValue(data.get(i).getSex());
            $1Cell.setCellStyle(dataCellStyle);
            // 年龄
            HSSFCell $2Cell = row.createCell(2);
            $2Cell.setCellValue(data.get(i).getAge());
            $2Cell.setCellStyle(dataCellStyle);
            // 创建下拉选项 联系方式（手机、微信、QQ）
            String[] property = data.get(i).getPropertiesName().toArray(new String[0]);
            DataValidationConstraint dataValidationConstraint = dataValidationHelper.createExplicitListConstraint(property);
            CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(i + 1, i + 1, 3, 3);
            // 数据有效性验证
            DataValidation dataValidation = dataValidationHelper.createValidation(dataValidationConstraint, cellRangeAddressList);
            sheet.addValidationData(dataValidation);
            // 创建下拉选项值
            HSSFCell $4Cell = row.createCell(4);
            List<String> value = data.get(i).getPropertiesValue();
            // 设置表达式
            // IF(H14="A10",10,IF(H14="B20",20))
            // IF(H14="A10",10,IF(H14="B20",20,IF(H14="C30",30)))
            String str = "IF(D" + i + 2 + "=\"%s\",\"%s\"";
            StringBuilder result = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            // 设置闭环括号的个数
            for (int j = 0; j < value.size(); j++) {
                sb.append(")");
            }
            // 赋值
            // IF(H14="A10",10,
            // IF(H14="A10",10,IF(H14="B20",20,
            // IF(H14="A10",10,IF(H14="B20",20,IF(H14="C30",30)))
            for (int j = 0; j < value.size(); j++) {
                String s = String.format(Locale.ROOT, str, property[j], value.get(j));
                if (j != (value.size() - 1)) {
                    result.append(s).append(",");
                } else {
                    result.append(s).append(sb);
                }
            }
            // IF(D02="手机","13728897992",IF(D02="微信","13728897992",IF(D02="QQ","997241010")))
            System.out.println(result.toString());
            $4Cell.setCellFormula(result.toString());
            // 备注
            HSSFCell $5Cell = row.createCell(5);
            $5Cell.setCellValue(data.get(i).getRemark());
            $5Cell.setCellStyle(dataCellStyle);
        }
        try (OutputStream outputStream = new FileOutputStream(testData.getFileName())) {
            workbook.write(outputStream);
        }
    }

}
