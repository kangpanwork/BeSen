package beSen.mysql;

/**
 * spring 事务隔离级别及事务传播特性
 */
public interface BsTransactionDefinition {
    /**
     * DEFAULT （默认）
     * 默认的隔离级别，使用数据库默认的事务隔离级别
     */
    int ISOLATION_DEFAULT = -1;

    /**
     * READ_UNCOMMITTED （读未提交）
     * 这是事务最低的隔离级别，它允许另外一个事务可以看到这个事务未提交的数据。
     * 这种隔离级别会产生脏读，不可重复读和幻像读.
     */
    int ISOLATION_READ_UNCOMMITTED = 1;
    /**
     * READ_COMMITTED （读已提交）
     * 保证一个事务修改的数据提交后才能被另外一个事务读取，另外一个事务不能读取该事务未提交的数据。
     * 这种事务隔离级别可以避免脏读出现，但是可能会出现不可重复读和幻像读。
     */
    int ISOLATION_READ_COMMITTED = 2;
    /**
     * REPEATABLE_READ （可重复读）
     * 这种事务隔离级别可以防止脏读、不可重复读，但是可能出现幻像读。
     * 它除了保证一个事务不能读取另一个事务未提交的数据外，还保证了不可重复读。
     */
    int ISOLATION_REPEATABLE_READ = 4;
    /**
     * SERIALIZABLE（串行化）
     * 除了防止脏读、不可重复读外，还避免了幻像读
     */
    int ISOLATION_SERIALIZABLE = 8;

    /**
     * required（默认属性）
     * 如果存在一个事务，则支持当前事务。如果没有事务则开启一个新的事务。
     * 被设置成这个级别时，会为每一个被调用的方法创建一个逻辑事务域。
     * 如果前面的方法已经创建了事务，那么后面的方法支持当前的事务，如果当前没有事务会重新建立事务。
     */
    int PROPAGATION_REQUIRED = 0;

    /**
     * 支持当前事务，如果当前没有事务，就以非事务方式执行。
     */
    int PROPAGATION_SUPPORTS = 1;

    /**
     * 支持当前事务，如果当前没有事务，就抛出异常。
     */
    int PROPAGATION_MANDATORY = 2;

    /**
     * 新建事务，如果当前存在事务，把当前事务挂起。
     */
    int PROPAGATION_REQUIRES_NEW = 3;

    /**
     * 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
     */
    int PROPAGATION_NOT_SUPPORTED = 4;

    /**
     * 以非事务方式执行，如果当前存在事务，则抛出异常。
     */
    int PROPAGATION_NEVER = 5;

    /**
     * 支持当前事务，与当前事务同步提交或回滚。
     * 嵌套事务一个非常重要的概念就是内层事务依赖于外层事务。
     * 外层事务失败时，会回滚内层事务所做的动作。而内层事务操作失败并不会引起外层事务的回滚。
     */
    int PROPAGATION_NESTED = 6;

}
