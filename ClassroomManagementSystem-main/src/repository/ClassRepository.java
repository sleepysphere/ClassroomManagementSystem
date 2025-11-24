package repository;

import model.SchoolClass;
import java.util.List;

/**
 * Handles storage and retrieval of class information.
 */
public interface ClassRepository {

    SchoolClass findById(int id);

    List<SchoolClass> findAll();

    void save(SchoolClass c);
}
