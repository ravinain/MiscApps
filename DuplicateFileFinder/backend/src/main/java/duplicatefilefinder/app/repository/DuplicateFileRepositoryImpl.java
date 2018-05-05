package duplicatefilefinder.app.repository;

import duplicatefilefinder.app.repository.custom.DuplicateFileRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Ravi Nain on 4/16/2018.
 */
@Repository
@Transactional
public class DuplicateFileRepositoryImpl implements DuplicateFileRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void deleteNonDuplicateFiles() {
        Query query = entityManager.createNativeQuery("delete from duplicate_file\n" +
                "where file_name in (\n" +
                "select df.file_name from (\n" +
                "SELECT file_name FROM duplicate_file \n" +
                "group by file_name\n" +
                "having count(file_name) = 1) df)");
        query.executeUpdate();
    }
}
