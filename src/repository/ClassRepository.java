package repository;

import repository.*;
import model.SchoolClass;
import java.util.ArrayList;
import java.util.List;

public class ClassRepository{

    private List<SchoolClass> classes = new ArrayList<>();

    public SchoolClass findById(int id) {
        return classes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public List<SchoolClass> findAll() {
        return classes;
    }
    
    public void save(SchoolClass c) {
        classes.add(c);
    }
}
