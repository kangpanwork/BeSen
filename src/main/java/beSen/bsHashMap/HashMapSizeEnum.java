package beSen.bsHashMap;


public enum HashMapSizeEnum {

    MAXIMUM_500(500, 1 << 3),
    MAXIMUM_2000(2000, 1 << 4),
    MAXIMUM_3000(3000, 1 << 8),
    MAXIMUM_5000(5000, 1 << 16),
    MAXIMUM_1000(1000, 1 << 32);

    private int maxSize;

    private int initialCapacity;

    HashMapSizeEnum(int maxSize, int initialCapacity) {
        this.maxSize = maxSize;
        this.initialCapacity = initialCapacity;
    }

    public static int initialCapacity(int maxSize) {
        for (HashMapSizeEnum hashMapSizeEnum : values()) {
            if (maxSize <= hashMapSizeEnum.maxSize) {
                return hashMapSizeEnum.initialCapacity;
            }
        }
        return -1;
    }
}
