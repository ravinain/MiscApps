package duplicatefilefinder.app.controller;

import duplicatefilefinder.app.model.bo.DuplicateFile;
import duplicatefilefinder.app.model.dto.SearchDuplicateDto;
import duplicatefilefinder.app.service.DuplicateFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ravi Nain on 4/15/2018.
 */
@RestController("/")
public class DuplicateFileController {

    private final DuplicateFileService duplicateFileService;

    @Autowired
    public DuplicateFileController(DuplicateFileService duplicateFileService) {
        this.duplicateFileService = duplicateFileService;
    }


    public List<DuplicateFile> loadAndFindDuplicateFiles(SearchDuplicateDto searchDto) {
        return duplicateFileService.loadAndFindDuplicateFiles(searchDto);
    }

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public List<DuplicateFile> getDuplicateFiles() {
        return duplicateFileService.getDuplicateFiles();
    }
}
