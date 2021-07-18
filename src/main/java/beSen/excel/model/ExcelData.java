package beSen.excel.model;


import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public class ExcelData<T> {
    /**
     * 创建sheet页的名称
     */
    private String sheetName;
    /**
     * 列头数据
     */
    private List<String> headData;
    /**
     * excel数据
     */
    private List<T> data;
    /**
     * 文件名称
     */
    private String fileName;

    public List<String> getHeadData() {
        return headData;
    }

    public void setHeadData(List<String> headData) {
        this.headData = headData;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
