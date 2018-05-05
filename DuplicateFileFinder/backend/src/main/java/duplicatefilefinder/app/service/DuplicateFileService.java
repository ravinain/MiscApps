package duplicatefilefinder.app.service;

import duplicatefilefinder.app.model.bo.DuplicateFile;
import duplicatefilefinder.app.model.dto.SearchDuplicateDto;
import duplicatefilefinder.app.repository.DuplicateFileRepository;
import duplicatefilefinder.app.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Ravi Nain on 4/15/2018.
 */
@Service
public class DuplicateFileService {

    private final DuplicateFileRepository duplicateFileRepository;
    private static final int BATCH_SIZE = 100;
    private static final Logger LOGGER = LoggerFactory.getLogger(DuplicateFileService.class);

    @Autowired
    public DuplicateFileService(DuplicateFileRepository duplicateFileRepository) {
        this.duplicateFileRepository = duplicateFileRepository;
    }

    public List<DuplicateFile> loadAndFindDuplicateFiles(SearchDuplicateDto searchDuplicateDto) {
        List<DuplicateFile> duplicateFiles = new ArrayList<>();
        duplicateFileRepository.deleteAll();
        for (String path : searchDuplicateDto.getFolderPaths().split(";")) {
            scanPathAndFindFiles(path, duplicateFiles);
        }

        if (!duplicateFiles.isEmpty()) {
            duplicateFileRepository.saveAll(duplicateFiles);
        }

        duplicateFileRepository.deleteNonDuplicateFiles();

        return duplicateFileRepository.findAll();
    }

    public void scanPathAndFindFiles(String path, List<DuplicateFile> duplicateFiles) {
        File file = new File(path);

        if (file.exists()) {
            if (file.isFile()) {
                addFile(duplicateFiles, file);
            } else {
                scanFolder(duplicateFiles, file);
            }
        } else {
            LOGGER.error("Invalid file path {}", path);
        }
    }

    private void scanFolder(List<DuplicateFile> duplicateFiles, File file) {
        Optional<File[]> optionalFiles = Optional.ofNullable(file.listFiles());
        optionalFiles.ifPresent(files -> Stream.of(files).forEach(dirFile -> {
            scanPathAndFindFiles(dirFile.getPath(), duplicateFiles);
        }));
    }

    private void addFile(List<DuplicateFile> duplicateFiles, File file) {
        DuplicateFile duplicateFile = new DuplicateFile();
        duplicateFile.setFileName(FileUtils.getFileNameWithoutExtension(file.getName()));
        duplicateFile.setFilePath(file.getAbsolutePath());
        duplicateFile.setFileType(FileUtils.getFileType(file.getName()));
        duplicateFile.setSize(file.length());
        duplicateFiles.add(duplicateFile);

        if (duplicateFiles.size() >= BATCH_SIZE) {
            duplicateFileRepository.saveAll(duplicateFiles);
            duplicateFiles.clear();
        }
    }

    public List<DuplicateFile> getDuplicateFiles() {
        return duplicateFileRepository.findAll();
    }
}
