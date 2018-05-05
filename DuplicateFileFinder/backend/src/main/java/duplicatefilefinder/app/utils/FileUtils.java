package duplicatefilefinder.app.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Ravi Nain on 4/15/2018.
 */
public final class FileUtils {

    private FileUtils(){}

    public static String getFileType(String fileName) {
        boolean hasFileType = StringUtils.defaultIfEmpty(fileName, "").indexOf('.') != -1;

        if (hasFileType) {
            int lastDotIndex = fileName.lastIndexOf('.');
            if (lastDotIndex == fileName.length() - 1) {
                return "";
            }

            return fileName.substring(lastDotIndex + 1, fileName.length());
        }

        return StringUtils.EMPTY;
    }

    public static String getFileNameWithoutExtension(String fileName) {
        boolean hasFileType = StringUtils.defaultIfEmpty(fileName, "").indexOf('.') != -1;

        if (hasFileType) {
            int lastDotIndex = fileName.lastIndexOf('.');
            if (lastDotIndex == fileName.length() - 1) {
                return fileName;
            }

            return fileName.substring(0, lastDotIndex);
        }

        return fileName;
    }
}
