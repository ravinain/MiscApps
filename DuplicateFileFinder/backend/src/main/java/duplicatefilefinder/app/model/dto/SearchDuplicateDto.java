package duplicatefilefinder.app.model.dto;

/**
 * Created by Ravi Nain on 4/14/2018.
 */
public class SearchDuplicateDto {

    private String folderPaths;
    private String fileTypeFilter;
    private boolean refreshCache;

    public String getFolderPaths() {
        return folderPaths;
    }

    public void setFolderPaths(String folderPaths) {
        this.folderPaths = folderPaths;
    }

    public String getFileTypeFilter() {
        return fileTypeFilter;
    }

    public void setFileTypeFilter(String fileTypeFilter) {
        this.fileTypeFilter = fileTypeFilter;
    }

    public boolean isRefreshCache() {
        return refreshCache;
    }

    public void setRefreshCache(boolean refreshCache) {
        this.refreshCache = refreshCache;
    }
}
