
package beSen.bsHashMap;

import java.util.Iterator;

/**
 * 存放键值对的数据结构（数组 + 链表）
 * 没有限制链表长度，没有红黑树实现
 *
 * @param <K> 键
 * @param <V> 值
 */
public class BsHashMap<K, V> implements Iterable<Node> {
    public Iterator<Node> iterator() {
        return new HashMapForEach();
    }

    /**
     * 数组
     */
    private Node<K, V>[] table;

    /**
     * 节点个数
     */
    private int size;

    /**
     * 最大节点数
     */
    private int maxSize;

    /**
     * 省去扩容机制，性能比 java.util.HashMap 差
     * 根据添加的节点数量来初始化数组大小
     *
     * @param maxSize
     * @return
     * @see java.util.HashMap
     */
    public static BsHashMap newHashMap(int maxSize) {
        int initialCapacity = HashMapSizeEnum.initialCapacity(maxSize);
        if (-1 == initialCapacity) {
            throw new IllegalArgumentException("抱歉！请使用正版HashMap，为了性能考虑，暂不支持添加这么多元素...");
        }
        return new BsHashMap(initialCapacity, maxSize);
    }

    /**
     * 构造函数，初始化数组大小及节点数量
     *
     * @param initialCapacity 数组大小
     * @param maxSize         最大节点数量
     */
    private BsHashMap(int initialCapacity, int maxSize) {
        this.maxSize = maxSize;
        table = new Node[initialCapacity];
    }

    /**
     * 节点数量
     *
     * @return 数量
     */
    public int size() {
        return size;
    }

    /**
     * 容量是否为空，节点数为0
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 根据索引获取节点的值
     *
     * @param key 键
     * @return 值
     */
    public V get(K key) {
        // 获取数组索引位置
        int index = getIndex(key);
        // 找到对应的节点
        Node<K, V> node = table[index];
        // 遍历数组中的节点
        while (node != null) {
            // 当 key 相同时候就返回节点的值
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
            // 不相同继续遍历下一个节点
            node = node.getNext();
        }
        return null;
    }

    /**
     * 添加节点
     *
     * @param key   键
     * @param value 值
     * @return 值
     */
    public V put(K key, V value) {
        ++size;
        if (size > maxSize) {
            throw new IndexOutOfBoundsException("抱歉！添加的元素大于设置的大小...");
        }
        // 获取数组位置
        int index = getIndex(key);
        // 判断是否为空
        if (null == table[index]) {
            // 数组索引位置生成一个链表
            table[index] = new Node<>(index, key, value, null);
        } else {
            // 插入新的节点
            Node<K, V> node = table[index];
            node.push(node, new Node<>(index, key, value, null));
        }
        return value;
    }

    /**
     * 判断节点中是否存在对应的 key
     *
     * @param key
     * @return
     */
    public boolean containsKey(Object key) {
        int index = getIndex((K) key);
        Node<K, V> e = table[index];
        if (e == null) {
            return false;
        }
        while (e != null) {
            if (e.getKey().equals(key)) {
                return true;
            }
            e = e.getNext();
        }
        return false;
    }

    /**
     * 清空
     */
    public void clear() {
        if (table != null && size > 0) {
            size = 0;
            for (int i = 0; i < table.length; ++i) {
                table[i] = null;
            }
        }
    }

    /**
     * 获取数组索引 (size - 1) & hash 运算, 索引范围在 0 ~ (size -1)
     *
     * @param key
     * @return
     */
    private final int getIndex(K key) {
        return (table.length - 1) & hash(key);
    }

    private final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 数组索引，用来判断是否有下个节点
     */
    private int i;

    /**
     * 用来 foreach 遍历
     */
    private class HashMapForEach implements Iterator<Node> {

        @Override
        public boolean hasNext() {
            int len = table.length;
            if (i >= len) {
                return false;
            }
            ++i;
            return true;
        }

        @Override
        public Node next() {
            return table[i - 1];
        }
    }
}

