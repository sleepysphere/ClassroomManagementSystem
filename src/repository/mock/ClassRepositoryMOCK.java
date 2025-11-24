package repository.mock;

import repository.ClassRepository;
import model.SchoolClass;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock version of ClassRepository for testing controller logic.
 */
public class ClassRepositoryMOCK implements ClassRepository {

    private List<SchoolClass> classes = new ArrayList<>();

    @Override
    public SchoolClass findById(int id) {
        return classes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<SchoolClass> findAll() {
        return classes;
    }

    @Override
    public void save(SchoolClass c) {
        classes.add(c);
    }
}
