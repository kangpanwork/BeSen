package beSen.test.bean.model;

public class Student implements Comparable<Student> {

    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Student() {
    }

    @Override
    public int compareTo(Student o) {
        if(o.score < this.score)
            return 1;
        else if (o.score > this.score)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
                "name=" + name  +
                ", score=" + score +
                '}';
    }
}
