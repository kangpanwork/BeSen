package beSen.test.StringListMap;

import beSen.StringListMap.StringListMap;
import beSen.test.StringListMap.model.People;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 康盼Java开发工程师
 */
public class StringListMapTest {

    static List<People> list;

    @Before
    public void init() {
        list = Lists.newArrayList(
                new People(1L, "周杰伦", "男", 30, 12),
                new People(2L, "汪峰", "男", 30, 10),
                new People(3L, "刘亦菲", "女", 20, 15),
                new People(4L, "蔡依林", "女", 18, 18),
                new People(5L, "张杰", "男", 30, 20),
                new People(6L, "薛之谦", "男", 28, 30)
        );
    }

    @Test
    public void test1() {
        // [周杰伦,汪峰,刘亦菲,蔡依林,张杰,薛之谦]
        System.out.println(
                StringListMap.listToString(list.stream().map(People::getName).collect(Collectors.toList()))
        );
    }

    @Test
    public void test2() {
        // (周杰伦:汪峰:刘亦菲:蔡依林:张杰:薛之谦)
        System.out.println(
                StringListMap.listToString(list.stream().map(People::getName).collect(Collectors.toList()),":","(",")")
        );
    }

    @Test
    public void test3() {
        // 蔡依林:18,周杰伦:30,薛之谦:28,刘亦菲:20,张杰:30,汪峰:30
        Map<String,Integer> map = list.stream().collect(Collectors.toMap(People::getName,People::getAge,(a1, a2) -> a2));
        System.out.println(StringListMap.mapToString(map,",",":"));
    }

    @Test
    public void test4() {
      StringListMap.StringToMap(",",":","蔡依林:18,周杰伦:30,薛之谦:28,刘亦菲:20,张杰:30,汪峰:30").forEach(
              (k,v) -> System.out.println(k + ":" + v)
      );
    }

    /**
     * 升序去重
     */
    @Test
    public void test5() {
        list.stream().filter(StringListMap.distinct(People::getAge)).sorted(Comparator.comparing(People::getAge)).forEach(System.out::println);
    }

}
