package beSen.test.bean.model;

public class StudentDefinition  {

    private String className;

    private Student student;

    public StudentDefinition(Student student, String className) {
        this.className = className;
        this.student = student;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
