package beSen.test.bean.model;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StudentDao {

    private static TreeMap<Student, StudentDefinition> map = new TreeMap<>();

    static {
        Student s1 = new Student("小明", 100);
        Student s2 = new Student("小红", 87);
        Student s3 = new Student("小张", 26);
        Student s4 = new Student("小李", 75);
        Student s5 = new Student("小康", 80);
        Student s6 = new Student("小白", 98);
        Student s7 = new Student("小刘", 67);
        Student s8 = new Student("小胡", 53);
        Student s9 = new Student("小三", 99);
        Student s10 = new Student("小四", 49);
        Student s11 = new Student("小五", 97);
        Student s12 = new Student("小小", 59);
        Student s13 = new Student("小六", 84);
        Student s14 = new Student("小戴", 79);
        Student s15 = new Student("小周", 88);
        Student s16 = new Student("小二", 76);
        Student s17 = new Student("小一", 97);
        Student s18 = new Student("小虎", 83);
        Student s19 = new Student("小狗", 78);
        Student s20 = new Student("小猫", 60);

        map.put(s1, new StudentDefinition(s1, "班级一"));
        map.put(s2, new StudentDefinition(s2, "班级一"));
        map.put(s3, new StudentDefinition(s3, "班级二"));
        map.put(s4, new StudentDefinition(s4, "班级一"));
        map.put(s5, new StudentDefinition(s5, "班级二"));
        map.put(s6, new StudentDefinition(s6, "班级一"));
        map.put(s7, new StudentDefinition(s7, "班级二"));
        map.put(s8, new StudentDefinition(s8, "班级一"));
        map.put(s9, new StudentDefinition(s9, "班级二"));
        map.put(s10, new StudentDefinition(s10, "班级二"));
        map.put(s11, new StudentDefinition(s11, "班级一"));
        map.put(s12, new StudentDefinition(s12, "班级二"));
        map.put(s13, new StudentDefinition(s13, "班级二"));
        map.put(s14, new StudentDefinition(s14, "班级一"));
        map.put(s15, new StudentDefinition(s15, "班级一"));
        map.put(s16, new StudentDefinition(s16, "班级二"));
        map.put(s17, new StudentDefinition(s17, "班级一"));
        map.put(s18, new StudentDefinition(s18, "班级二"));
        map.put(s19, new StudentDefinition(s19, "班级二"));
        map.put(s20, new StudentDefinition(s20, "班级一"));

    }

    /**
     * 查询成绩高于或者等于这个学生的集合
     *
     * @param student
     * @return
     */
    public List<Student> queryScoreMoreThanThis(Student student) {
        SortedMap<Student, StudentDefinition> result = map.tailMap(student);
        // for (Map.Entry entry : result.entrySet());
        // List<Student> list = new ArrayList<>();
        //for(Iterator iterator = result.keySet().iterator();iterator.hasNext();) {
        //    Student s = (Student) iterator.next();
        //    list.add(s);
        // }
        return result.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /**
     * 查询成绩低于这个学生的集合
     *
     * @param student
     * @return
     */
    public List<Student> queryScoreLessThanThis(Student student) {
        SortedMap<Student, StudentDefinition> result = map.headMap(student);
        return result.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /**
     * 查询成绩介于这两个学生的集合
     *
     * @param s1
     * @param s2
     * @return
     */
    public List<Student> queryScoreLessThanThis(Student s1,Student s2) {
        SortedMap<Student, StudentDefinition> result = map.subMap(s1, s2);
        return result.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
