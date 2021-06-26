package beSen.generatorProject.model;

/**
 * 启动类模型
 */
public class ApplicationInfo {
    private String packageName;
    private String className;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ApplicationInfo(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
    }

    public ApplicationInfo() {
    }
}
