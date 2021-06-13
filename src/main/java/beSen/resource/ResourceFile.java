package beSen.resource;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 备注：资源文件相对路径是相对于 resource 路径
 */
public interface ResourceFile {
    /**
     * 获取资源文件
     *
     * @param relativePath 资源文件相对路径
     * @return 资源文件
     */
    File getFile(String relativePath) throws FileNotFoundException;

    /**
     * 获取资源绝对路径
     *
     * @param relativePath 资源文件相对路径
     * @return 资源绝对路径
     */
    String getAbsolutePath(String relativePath) throws FileNotFoundException;

    /**
     * 获取资源父级目录
     *
     * @param relativePath 资源文件相对路径
     * @return 资源父级目录
     */
    String getParent(String relativePath) throws FileNotFoundException;

    /**
     * 获取资源文件名称
     *
     * @param relativePath 资源文件相对路径
     * @return 文件名称
     */
    String getFileName(String relativePath) throws FileNotFoundException;
}