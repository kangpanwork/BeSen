package beSen.bean.resource.impl;

import beSen.bean.resource.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 通过 file 获取
 */
public class FileSystemResource implements Resource {

    private final File file;

    private final String path;

    public File getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }


}
