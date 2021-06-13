package beSen.test.bsHashMap;

import beSen.bsHashMap.BsHashMap;
import beSen.bsHashMap.Node;

public class TestBsHashMap {
    public static void main(String[] args) {
        BsHashMap hashMap = BsHashMap.newHashMap(134);
        for (int i = 0; i < 133; i++) {
            hashMap.put("k" + i, "v" + i);
        }
        for (Object entry : hashMap) {
            Node node = (Node) entry;
            if (node != null) {
                Node e;
                while ((e = node.pop()) != null) {
                    System.out.println(e.getIndex() + ":" + e.getKey() + ":" + e.getValue());
                }
                System.out.println(node.getIndex() + ":" + node.getKey() + ":" + node.getValue());
            }

        }
    }
}
