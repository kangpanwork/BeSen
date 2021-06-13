package beSen.resource.util;

import beSen.resource.BsResourceFile;
import beSen.resource.ResourceFile;

import java.io.FileNotFoundException;


public final class ResourceFileUtil extends BsResourceFile implements ResourceFile {

    public static ResourceFileUtil use() {
        return new ResourceFileUtil();
    }

    @Override
    public String getAbsolutePath(String relativePath) throws FileNotFoundException {
        return getFile(relativePath).getAbsolutePath();
    }

    @Override
    public String getParent(String relativePath) throws FileNotFoundException {
        return getFile(relativePath).getParent();
    }

    @Override
    public String getFileName(String relativePath) throws FileNotFoundException {
        return getFile(relativePath).getName();
    }
}
