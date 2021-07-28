package beSen.test.excel;


import beSen.excel.model.ExcelBackRoundColor;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
            // 姓名
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

    /**
     * 有这样一个需求，假如有 A B C D E F 列 （列头不可以编辑）
     * A 输入框，可以编辑（界面显示）
     * B 下拉组件（是/否），可以下拉（界面显示）
     * C 下拉组件（是/否），可以下拉（界面显示）的前提是B下拉选是
     * D 输入框，可以编辑（界面显示）的前提是B下拉选否
     * E 下拉组件（是/否），可以编辑（界面显示）的前提是B下拉选是，C下拉选否
     * F 输入框，可以编辑（界面显示）的前提是B下拉选是，C下拉选是
     * <p>
     * 数据库中有两组数据 对应列分别是
     * A           B    C     D       E     F
     * 字符串 hello ; 否；null；数字 123；null；null；
     * 字符串 world ; 是；否；  null；   是；  null；
     * <p>
     * <p>
     * 这些数据来源于界面填写的 为 null 的情况是 因为条件触发导致该组件没有显示，无法通过界面填值
     * <p>
     * 起初的想法是通过excel函数动态控制单元格是否可以编辑 然后没有找到相关公式放弃了
     * 然后通过excel函数公式动态控制单元格颜色（红色表示不可以编辑） 然后没有找到相关公式放弃了
     * <p>
     * 现在是通过下拉多一个选项（不可编辑（勿选））
     *
     * @throws Exception
     */
    @Test
    public void generator() throws Exception {
        // 伪造数据
        List<String> headData = Lists.newArrayList("A", "B", "C", "D", "E", "F");
        beSen.test.excel.model.Test test1 =
                new beSen.test.excel.model.Test("hello", "否", null, 123, null, null);
        beSen.test.excel.model.Test test2 =
                new beSen.test.excel.model.Test("world", "是", "否", null, "是", null);
        // 生成列头 不让删除 不让新增
        ExcelData excelData = new ExcelData();
        excelData.setData(Lists.newArrayList(test1, test2));
        // 创建workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 先定义锁定的样式
        XSSFCellStyle lockStyle = workbook.createCellStyle();
        lockStyle.setLocked(true);
        lockStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        lockStyle.setFillForegroundColor((short) ExcelBackRoundColor.X16.getIndex());
        // 不锁定的样式
        XSSFCellStyle unlockStyle = workbook.createCellStyle();
        unlockStyle.setLocked(false);

        // 创建sheet页
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "sheet页01");
        // 设置sheet宽度
        sheet.setDefaultColumnWidth(15);
        // 设置title样式
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        // 设置填充方式
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 填充颜色
        titleStyle.setFillForegroundColor((short) ExcelBackRoundColor.X3.getIndex());
        // 创建表头行
        XSSFRow row = sheet.createRow(0);
        XSSFCell xssfCell;
        // 渲染表头数据
        for (int i = 0; i < headData.size(); i++) {
            xssfCell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headData.get(i));
            xssfCell.setCellValue(text);
            xssfCell.setCellStyle(titleStyle);
        }

        List<beSen.test.excel.model.Test> list = excelData.getData();
        XSSFDataValidationHelper dataValidationHelper = new XSSFDataValidationHelper(sheet);
        String[] defaultYes = new String[]{"是", "否"};
        String[] defaultNo = new String[]{"否", "是"};
        for (int i = 0; i < list.size(); i++) {
            // 创建数据列
            row = sheet.createRow(i + 1);
            // 第一个单元格 A 输入框，可以编辑
            xssfCell = row.createCell(0);
            xssfCell.setCellValue(list.get(i).getA());

            // B 下拉组件（是/否），可以下拉（界面显示）
            String bValue = list.get(i).getB();
            boolean bIsY = "是".equals(bValue);
            XSSFDataValidationConstraint constraint;
            if (bIsY) {
                constraint = (XSSFDataValidationConstraint) dataValidationHelper
                        .createExplicitListConstraint(defaultYes);
            } else {
                constraint = (XSSFDataValidationConstraint) dataValidationHelper
                        .createExplicitListConstraint(defaultNo);
            }
            // 设置哪些单元格需要下拉，列头row是0，第一行数据row是1，第一个单元格col是0
            CellRangeAddressList range = new CellRangeAddressList(i + 1, i + 1, 1, 1);
            XSSFDataValidation validation = (XSSFDataValidation) dataValidationHelper.createValidation(constraint, range);
            validation.createErrorBox("提示", "请从下拉列表选取");
            validation.setShowErrorBox(true);
            sheet.addValidationData(validation);


            // C 下拉组件（是/否），可以下拉（界面显示）的前提是B下拉选是
            String str = "IF(D" + i + 2 + "=\"%s\",\"%s\"";
            // IF(B2="是","默认值",IF(B2="否","不可编辑（勿选）"))
            String cValue = list.get(i).getC();
            if (bIsY) {
                // B 下拉选是的时候 必有默认值
                boolean cIsY = "是".equals(cValue);
                if (cIsY) {

                } else {

                }
            } else {
                // B 下拉选否的时候 没有默认值 为null 初始化默认值 为 是

            }

            // D 输入框，可以编辑（界面显示）的前提是B下拉选否
            // E 下拉组件（是/否），可以编辑（界面显示）的前提是B下拉选是，C下拉选否
            // F 输入框，可以编辑（界面显示）的前提是B下拉选是，C下拉选是


        }

        // 在线测试 https://rfmjopjucn.feishu.cn/file/boxcnmWg5vjpyfsum98UG0I9Sfb
        try (OutputStream outputStream = new FileOutputStream("test.xls")) {
            workbook.write(outputStream);
        }


    }

    @Test
    public void cascade() {
        Workbook book = new XSSFWorkbook();

        // 创建需要用户填写的sheet
        XSSFSheet sheetPro = (XSSFSheet) book.createSheet("省市县");
        Row row0 = sheetPro.createRow(0);
        row0.createCell(0).setCellValue("省");
        row0.createCell(1).setCellValue("市");
        row0.createCell(2).setCellValue("区");

        //得到第一级省名称，放在列表里
        String[] provinceArr = {"江苏省", "安徽省"};
        //依次列出各省的市、各市的县
        String[] cityJiangSu = {"南京市", "苏州市", "盐城市"};
        String[] cityAnHui = {"合肥市", "安庆市"};
        String[] countyNanjing = {"六合县", "江宁县"};
        String[] countySuzhou = {"姑苏区", "园区"};
        String[] countyYancheng = {"响水县", "射阳县"};
        String[] countyLiuhe = {"瑶海区", "庐阳区"};
        String[] countyAnQing = {"迎江区", "大观区"};
        //将有子区域的父区域放到一个数组中
        String[] areaFatherNameArr = {"江苏省", "安徽省", "南京市", "苏州市", "盐城市", "合肥市", "安庆市"};
        Map<String, String[]> areaMap = new HashMap<String, String[]>();
        areaMap.put("江苏省", cityJiangSu);
        areaMap.put("安徽省", cityAnHui);
        areaMap.put("南京市", countyNanjing);
        areaMap.put("苏州市", countySuzhou);
        areaMap.put("盐城市", countyYancheng);
        areaMap.put("合肥市", countyYancheng);
        areaMap.put("合肥市", countyLiuhe);
        areaMap.put("安庆市", countyAnQing);

        //创建一个专门用来存放地区信息的隐藏sheet页
        //因此也不能在现实页之前创建，否则无法隐藏。
        Sheet hideSheet = book.createSheet("area");
        //这一行作用是将此sheet隐藏，功能未完成时注释此行,可以查看隐藏sheet中信息是否正确
        //book.setSheetHidden(book.getSheetIndex(hideSheet), true);

        int rowId = 0;
        // 设置第一行，存省的信息
        Row provinceRow = hideSheet.createRow(rowId++);
        provinceRow.createCell(0).setCellValue("省列表");
        for (int i = 0; i < provinceArr.length; i++) {
            Cell provinceCell = provinceRow.createCell(i + 1);
            provinceCell.setCellValue(provinceArr[i]);
        }
        // 将具体的数据写入到每一行中，行开头为父级区域，后面是子区域。
        for (int i = 0; i < areaFatherNameArr.length; i++) {
            String key = areaFatherNameArr[i];
            String[] son = areaMap.get(key);
            Row row = hideSheet.createRow(rowId++);
            row.createCell(0).setCellValue(key);
            for (int j = 0; j < son.length; j++) {
                Cell cell = row.createCell(j + 1);
                cell.setCellValue(son[j]);
            }

            // 添加名称管理器
            String range = getRange(1, rowId, son.length);
            Name name = book.createName();
            //key不可重复
            name.setNameName(key);
            String formula = "area!" + range;
            name.setRefersToFormula(formula);
            System.out.println(name.getNameName() + ":" +name.getRefersToFormula());
        }

        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheetPro);
        // 省规则
        DataValidationConstraint provConstraint = dvHelper.createExplicitListConstraint(provinceArr);
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList provRangeAddressList = new CellRangeAddressList(1, 20, 0, 0);
        DataValidation provinceDataValidation = dvHelper.createValidation(provConstraint, provRangeAddressList);
        //验证
        provinceDataValidation.createErrorBox("error", "请选择正确的省份");
        provinceDataValidation.setShowErrorBox(true);
        provinceDataValidation.setSuppressDropDownArrow(true);
        sheetPro.addValidationData(provinceDataValidation);

        //对前20行设置有效性
        for (int i = 2; i < 20; i++) {
            setDataValidation("A", sheetPro, i, 2);
            setDataValidation("B", sheetPro, i, 3);
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream("cascade.xlsx");
            book.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }


    }

    /**
     * 设置有效性
     *
     * @param offset 主影响单元格所在列，即此单元格由哪个单元格影响联动
     * @param sheet
     * @param rowNum 行数
     * @param colNum 列数
     */
    public void setDataValidation(String offset, XSSFSheet sheet, int rowNum, int colNum) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        DataValidation data_validation_list;
        data_validation_list = getDataValidationByFormula(
                "INDIRECT($" + offset + (rowNum) + ")", rowNum, colNum, dvHelper);
        sheet.addValidationData(data_validation_list);
    }

    /**
     * 加载下拉列表内容
     *
     * @param formulaString
     * @param naturalRowIndex
     * @param naturalColumnIndex
     * @param dvHelper
     * @return
     */
    private DataValidation getDataValidationByFormula(
            String formulaString, int naturalRowIndex, int naturalColumnIndex, XSSFDataValidationHelper dvHelper) {

        // 加载下拉列表内容
        // 举例：若formulaString = "INDIRECT($A$2)" 表示规则数据会从名称管理器中获取key与单元格 A2 值相同的数据，
        //如果A2是江苏省，那么此处就是江苏省下的市信息。
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint(formulaString);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        int firstRow = naturalRowIndex - 1;
        int lastRow = naturalRowIndex - 1;
        int firstCol = naturalColumnIndex - 1;
        int lastCol = naturalColumnIndex - 1;
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                lastRow, firstCol, lastCol);
        System.out.println(formulaString + firstRow + lastRow + firstCol + lastCol);
        // 数据有效性对象
        // 绑定
        XSSFDataValidation data_validation_list = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
        data_validation_list.setEmptyCellAllowed(false);
        if (data_validation_list instanceof XSSFDataValidation) {
            data_validation_list.setSuppressDropDownArrow(true);
            data_validation_list.setShowErrorBox(true);
        } else {
            data_validation_list.setSuppressDropDownArrow(false);
        }
        // 设置输入信息提示信息
        data_validation_list.createPromptBox("下拉选择提示", "请使用下拉方式选择合适的值！");
        // 设置输入错误提示信息
        // data_validation_list.createErrorBox("选择错误提示", "你输入的值未在备选列表中，请下拉选择合适的值！");
        return data_validation_list;
    }

    /**
     * 计算formula
     *
     * @param offset   偏移量，如果给0，表示从A列开始，1，就是从B列
     * @param rowId    第几行
     * @param colCount 一共多少列
     * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
     */
    public String getRange(int offset, int rowId, int colCount) {
        char start = (char) ('A' + offset);
        if (colCount <= 25) {
            char end = (char) (start + colCount - 1);
            return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
        } else {
            char endPrefix = 'A';
            char endSuffix = 'A';
            // 26-51之间，包括边界（仅两次字母表计算）
            if ((colCount - 25) / 26 == 0 || colCount == 51) {
                // 边界值
                if ((colCount - 25) % 26 == 0) {
                    endSuffix = (char) ('A' + 25);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                }
            } else {// 51以上
                if ((colCount - 25) % 26 == 0) {
                    endSuffix = (char) ('A' + 25);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26 - 1);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26);
                }
            }
            return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
        }
    }


}
