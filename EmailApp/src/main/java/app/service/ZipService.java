package app.service;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by Ravi Nain on 3/11/2018.
 */
@Service
public class ZipService {

    private final Logger logger = LoggerFactory.getLogger(ZipService.class);

    private final Environment environment;
    private final ZipParameters zipParameters;

    @Autowired
    public ZipService(Environment environment,
                      ZipParameters zipParameters) {
        this.environment = environment;
        this.zipParameters = zipParameters;
    }

    public void zipAndAddProtection(String sourceFilePath) throws ZipException{
        String zipFilePath = getZipFilePath(sourceFilePath);
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
            zipFile.addFile(new File(sourceFilePath), zipParameters);
        } catch (ZipException e) {
            logger.error("Error while zipping file: ", e);
            throw e;
        }
    }

    public String getZipFilePath(String sourceFilePath) {
        String sourceFileName = sourceFilePath.substring(sourceFilePath.lastIndexOf(File.separator) + 1);
        return environment.getProperty("java.io.tmpdir") + sourceFileName + ".zip";
    }
}
