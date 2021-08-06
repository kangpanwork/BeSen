package beSen.bloomFilter;


import com.google.common.hash.Funnels;

import java.util.BitSet;

/**
 * 思想：有多个计算哈希不同的对象SimpleHash
 * 初始这样对象的一个数组
 * 当添加的时候，对添加的值进行hash计算，每个SimpleHash对象计算的不一样
 * 利用BitSet set 进去
 *
 * 看是否存在的时候，每个都去get下，如果由一个不存在就肯定不存在，全部存在则存在
 *
 *
 * @author 康盼Java开发工程师
 */
public class BloomFilter {


    private static final int capacity = 2 << 3;

    /**
     * 一个Bitset类创建一种特殊类型的数组来保存位值。BitSet中数组大小会随需要增加。
     */
    private BitSet bits = new BitSet(capacity);

    private SimpleHash[] arr = new SimpleHash[SEEDS.length];

    private static final int[] SEEDS = new int[]{1,2,3};

    public BloomFilter () {
        for (int i = 0; i < SEEDS.length; i++) {
            arr[i] = new SimpleHash(capacity, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     */
    public void add(Object value) {
        for (SimpleHash simpleHash : arr) {
            bits.set(simpleHash.hash(value), true);
        }
    }


    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash simpleHash : arr) {
            ret = ret && bits.get(simpleHash.hash(value));
            if (!ret) {
                return false;
            }
        }
        return true;
    }

    public static class SimpleHash {
        private int cap;
        private int seed;
        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ h >>> 16));
        }
    }



    public static void main(String[] args) {
        String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        BloomFilter bloomFilter = new BloomFilter();
        System.out.println(bloomFilter.contains(value1));
        System.out.println(bloomFilter.contains(value2));
        bloomFilter.add(value1);
        bloomFilter.add(value2);
        System.out.println(bloomFilter.contains(value1));
        System.out.println(bloomFilter.contains(value2));


        // 创建布隆过滤器对象
        // 创建了一个最多存放 最多 1500个整数的布隆过滤器，并且我们可以容忍误判的概率为百分之（0.01）
        com.google.common.hash.BloomFilter<Integer> filter = com.google.common.hash.BloomFilter.create(
                Funnels.integerFunnel(),
                1500,
                0.01);
        // 判断指定元素是否存在
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        // 将元素添加进布隆过滤器
        filter.put(1);
        filter.put(2);
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));

        BitSet bitset = new BitSet(8);
        // 将数组索引为1的位置设置为true
        bitset.set(1,true);
        System.out.println(bitset.get(1));


//        127.0.0.1:6379> BF.ADD myFilter java
//        (integer) 1
//        127.0.0.1:6379> BF.ADD myFilter javaguide
//        (integer) 1
//        127.0.0.1:6379> BF.EXISTS myFilter java
//        (integer) 1
//        127.0.0.1:6379> BF.EXISTS myFilter javaguide
//        (integer) 1
//        127.0.0.1:6379> BF.EXISTS myFilter github
//        (integer) 0
        // errorRate count
        String init = "redis.call('bf.reserve', KEYS[1], ARGV[1], ARGV[2])";
        String add = "redis.call('bf.add', KEYS[1], ARGV[1])";
        String isContains = "redis.call('bf.exists', KEYS[1], ARGV[1])";
    }


}
