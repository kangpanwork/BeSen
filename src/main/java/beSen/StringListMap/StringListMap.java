package beSen.StringListMap;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * String <-> list <-> map 相互转换
 *
 * @author 康盼Java开发工程师
 */
public class StringListMap {

    /**
     * 集合转字符串
     *
     * @param list
     * @param delimiter
     * @return
     */
    public String listToString(List<String> list, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(element -> stringBuilder.append(element).append(delimiter));
        // delete(start, end);
        stringBuilder.delete(stringBuilder.length() - delimiter.length(), stringBuilder.length());
        return stringBuilder.toString();
    }

    /**
     * list 转字符串 含前缀后缀
     *
     * @param list
     * @return
     */
    public static String listToString(List<String> list) {
        return list.stream().collect(Collectors.joining(",", "[", "]"));
    }


    /**
     * list to String
     *
     * @param list      分隔的集合
     * @param separator 分隔符
     * @param prefix    前缀符号
     * @param suffix    后缀符号
     * @return String 结果
     */
    public static String listToString(List<String> list, String separator, String prefix, String suffix) {
        StringJoiner stringJoiner = new StringJoiner(separator, prefix, suffix);
        list.forEach(element -> stringJoiner.add(element));
        return stringJoiner.toString();
    }

    /**
     * string -> list
     * 字符串 aabbcc ，按照长度为2，会分隔元素为 [aa] [bb] [cc] 的集合
     *
     * @param sequence 分隔的字符串 String 实现 CharSequence 接口
     * @param length   分隔的长度
     * @return List<String> 结果
     */
    public static List<String> stringToList(CharSequence sequence, int length) {
        Iterable<String> iterable = Splitter.fixedLength(length).split(sequence);
        List<String> list = new ArrayList<>();
        for (Iterator<String> iterator = iterable.iterator(); iterator.hasNext(); ) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * Splitter.on(separator).trimResults().omitEmptyStrings().splitToList(sequence);
     * trimResults() 去掉元素左右空字符串，omitEmptyStrings 去掉分隔符号之间的空元素。
     * 举个例子： " k ;;p" ，trimResults之后得到 [k] [空] [p] 三个元素，加上 omitEmptyStrings  会去除空元素
     *
     * @param separator 分隔符
     * @param sequence  分隔的字符串
     * @return List<String> 结果
     */
    public static List<String> stringToList(String separator, CharSequence sequence) {
        Iterable<String> iterable = Splitter.on(separator).trimResults().omitEmptyStrings().split(sequence);
        List<String> list = new ArrayList<>();
        Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * @param separator 分隔符
     * @param str       分隔的字符串
     * @return List 结果
     */
    public static List StringToList(String separator, String str) {
        return Arrays.stream(str.split(separator)).filter(e -> !e.isEmpty()).collect(Collectors.toList());
    }

    /**
     * 字符串 a,b,c,d,e,f,g 限制长度为3，得到集合元素为 [a] [b] [c,d,e,f,g]
     *
     * @param separator 分割符
     * @param length    限制长度
     * @param sequence  分隔的字符串
     * @return List<String> 结果
     */
    public static List<String> stringToList(String separator, int length, CharSequence sequence) {
        return Splitter.on(separator).limit(length).splitToList(sequence);
    }

    /**
     * 字符串 a,b:c,d;e 有不同分隔符， 逗号、 冒号、 分号等，利用正则表达式 [,|:|;] 分隔
     *
     * @param separatorPattern 多个分隔符
     * @param sequence         分隔的字符串
     * @return List<String>
     */
    public static List<String> stringToList(CharSequence sequence, String separatorPattern) {
        return Splitter.onPattern(separatorPattern).splitToList(sequence);
    }

    /**
     * 跳过null
     *
     * @param parts
     * @param separator
     * @return
     */
    public static String arrayToString(Object[] parts, String separator) {
        return Joiner.on(separator).skipNulls().join(parts);
    }

    /**
     * 替换null
     *
     * @param parts
     * @param separator
     * @param nullText
     * @return
     */
    public static String arrayToString(Object[] parts, String separator, String nullText) {
        return Joiner.on(separator).useForNull(nullText).join(parts);
    }

    /**
     * String数组转字符串
     *
     * @param parts
     * @param separator
     * @return
     */
    public static String arrayToString(String[] parts, CharSequence separator) {
        return String.join(separator, parts);
    }


    /**
     * mapToString
     *
     * @param map               分隔的map
     * @param separator         分隔符
     * @param keyValueSeparator 键值对分隔符
     * @return String 结果
     */
    public static String mapToString(Map map, String separator, String keyValueSeparator) {
        return Joiner.on(separator).withKeyValueSeparator(keyValueSeparator).join(map);
    }

    /**
     * StringToMap
     * 字符串 name=kangPan;age=10;six=boy 最后不能带分隔符，否则报错。
     * 分隔符是分号，键值对分隔符是等号，会拆分 key 是 name,value 是 kangPan 等键值对的 map
     *
     * @param separator             分隔符
     * @param withKeyValueSeparator 键值对分割符
     * @param sequence              分隔的字符串
     * @return Map 结果
     */
    public static Map StringToMap(String separator, String withKeyValueSeparator, CharSequence sequence) {
        return Splitter.on(separator).withKeyValueSeparator(withKeyValueSeparator).split(sequence);
    }


}
