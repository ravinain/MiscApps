package duplicatefilefinder.app.repository;

import duplicatefilefinder.app.model.bo.DuplicateFile;
import duplicatefilefinder.app.repository.custom.DuplicateFileRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ravi Nain on 4/15/2018.
 */
public interface DuplicateFileRepository extends JpaRepository<DuplicateFile, Long>, DuplicateFileRepositoryCustom{
}
