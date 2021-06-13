/*版本v1*/

package beSen.bsHashMap;

/**
 * HashMap 链表实现
 *
 * @param <K>
 * @param <V>
 */
public final class Node<K, V> {
    private final K key;
    private final int index;
    private V value;
    private Node<K, V> next;

    public Node(int index, K key, V value, Node<K, V> next) {
        this.index = index;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public int getIndex() {
        return index;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    /**
     * 判断是否是最后一个节点
     *
     * @return
     */
    public boolean isEnd() {
        return next == null;
    }

    /**
     * 获取下个节点
     *
     * @return
     */
    public Node pop() {
        Node node = next;
        if (!isEnd()) {
            next = next.next;
        }
        return node;
    }

    /**
     * 末尾处添加元素
     *
     * @param node
     * @param next
     */
    public void push(Node node, Node next) {
        while (node != null) {
            Node<K, V> tab = node.next;
            if (tab == null) {
                node.next = next;
                break;
            }
            node = node.next;
        }
    }

}
