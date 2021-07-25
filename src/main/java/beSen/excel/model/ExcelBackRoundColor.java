package beSen.excel.model;


/**
 * @author 康盼Java开发工程师
 * 参考 背景颜色.PNG
 */
public enum ExcelBackRoundColor {
    X1("IndexedColors.AQUA.getIndex()", 49, "青色"),
    X2("IndexedColors.AUTOMATIC.getIndex()", 64, "黑色"),
    X3("IndexedColors.BLUE.getIndex()", 4, "蓝色"),
    X4("IndexedColors.BLUE_GREY.getIndex()", 54, "灰色80%"),
    X5("IndexedColors.BRIGHT_GREEN.getIndex()", 3, "绿色"),
    X6("IndexedColors.BROWN.getIndex()", 60, "咖啡色"),
    X7("IndexedColors.CORAL.getIndex()", 29, "粉色"),
    X8("IndexedColors.CORNFLOWER_BLUE.getIndex()", 24, "淡灰色"),
    X9("IndexedColors.DARK_BLUE.getIndex()", 18, "深蓝色"),
    X10("IndexedColors.DARK_GREEN.getIndex()", 58, "暗绿色"),
    X11("IndexedColors.DARK_RED.getIndex()", 16, "深咖啡色"),
    X12("IndexedColors.DARK_TEAL.getIndex()", 56, "暗蓝色"),
    X13("IndexedColors.DARK_YELLOW.getIndex()", 19, "灰黄色"),
    X14("IndexedColors.GOLD.getIndex()", 51, "黄色"),
    X15("IndexedColors.GREEN.getIndex()", 42, "灰绿色"),
    X16("IndexedColors.GREY_25_PERCENT.getIndex()", 22, "灰色20%"),
    X17("IndexedColors.GREY_40_PERCENT.getIndex()", 55, "灰色40%"),
    X18("IndexedColors.GREY_50_PERCENT.getIndex()", 23, "灰色60%"),
    X19("IndexedColors.GREY_80_PERCENT.getIndex()", 63, "灰色90%"),
    X20("IndexedColors.INDIGO.getIndex()", 62, "蓝色"),
    X21("IndexedColors.LAVENDER.getIndex()", 46, "淡粉红色"),
    X22("IndexedColors.LEMON_CHIFFON.getIndex()", 26, "淡黄色"),
    X23("IndexedColors.LIGHT_BLUE.getIndex()", 48, "亮蓝色"),
    X24("IndexedColors.LEMON_CHIFFON.getIndex()", 26, "黄色"),
    X25("IndexedColors.LIGHT_BLUE.getIndex()", 48, "亮蓝色"),
    X26("IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex()", 31, "灰色10%"),
    X27("IndexedColors.LIGHT_GREEN.getIndex()", 42, "淡青色"),
    X28("IndexedColors.LIGHT_ORANGE.getIndex()", 52, "橙色"),
    X29("IndexedColors.LIGHT_TURQUOISE.getIndex()", 41, "青色"),
    X30("IndexedColors.LIGHT_YELLOW.getIndex()", 43, "黄色"),
    X31("IndexedColors.LIME.getIndex()", 50, "暗绿色"),
    X32("IndexedColors.MAROON.getIndex()", 25, "紫色"),
    X33("IndexedColors.OLIVE_GREEN.getIndex()", 59, "焦绿色"),
    X34("IndexedColors.ORANGE.getIndex()", 53, "橙色"),
    X35("IndexedColors.ORCHID.getIndex()", 28, "紫色"),
    X36("IndexedColors.PALE_BLUE.getIndex()", 44, "青色"),
    X37("IndexedColors.PINK.getIndex()", 14, "紫色"),
    X38("IndexedColors.PLUM.getIndex()", 61, "紫色"),
    X39("IndexedColors.RED.getIndex()", 10, "红色"),
    X40("IndexedColors.ROSE.getIndex()", 45, "粉色"),
    X41("IndexedColors.ROYAL_BLUE.getIndex()", 30, "蓝色"),
    X42("IndexedColors.SEA_GREEN.getIndex()", 57, "草绿色"),
    X43("IndexedColors.SKY_BLUE.getIndex()", 40, "青色"),
    X44("IndexedColors.TAN.getIndex()", 47, "淡粉色"),
    X45("IndexedColors.TEAL.getIndex()", 56, "藏青色"),
    X46("IndexedColors.TURQUOISE.getIndex()", 27, "青色"),
    X47("IndexedColors.VIOLET.getIndex()", 20, "紫色"),
    X48("IndexedColors.WHITE.getIndex()", 9, "白色"),
    X49("IndexedColors.YELLOW.getIndex()", 5, "黄色");


    private String fg;

    private int index;

    private String name;

    ExcelBackRoundColor(String fg, int index, String name) {
        this.fg = fg;
        this.index = index;
        this.name = name;
    }

    public String getFg() {
        return fg;
    }

    public void setFg(String fg) {
        this.fg = fg;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
