package beSen.test.bean.model;

import java.util.List;


public class StudentService {

    private Student student;
    private StudentDao studentDao;

    /**
     * CGLIB动态代理需要无参构造函数
     */
    public StudentService() {
    }


    public StudentService(Student student, StudentDao studentDao) {
        this.student = student;
        this.studentDao = studentDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> queryScoreLessThanThis(Student student) {
        return studentDao.queryScoreLessThanThis(student);
    }
}
