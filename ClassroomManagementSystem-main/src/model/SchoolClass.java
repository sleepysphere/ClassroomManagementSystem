package model;

public class SchoolClass {
    private int id;
    private String name;
    private boolean requiresLab;
    private int studentCount;

    public SchoolClass(int id, String name, boolean requiresLab, int studentCount) {
        this.id = id;
        this.name = name;
        this.requiresLab = requiresLab;
        this.studentCount = studentCount;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean requiresLab() { return requiresLab; }
    public int getStudentCount() { return studentCount; }

    public void setName(String name) { this.name = name; }
    public void setRequiresLab(boolean requiresLab) { this.requiresLab = requiresLab; }
    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }
}

