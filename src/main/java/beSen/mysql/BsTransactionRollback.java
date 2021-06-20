package beSen.mysql;

import beSen.bsHashMap.BsHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Connection;
import java.util.List;

/**
 * 获取事务，事务回滚
 */

@Component
public class BsTransactionRollback {

    @Autowired
    private ApplicationContext applicationContext;

    public <T> List<T> query(String sql, Class<T> cls, Object... args) {
        BsHashMap<String, Object> map = get();
        PlatformTransactionManager platformTransactionManager = (PlatformTransactionManager) map.get("platformTransactionManager");
        DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager) map.get("dataSourceTransactionManager");
        TransactionStatus transactionStatus = (TransactionStatus) map.get("transactionStatus");
        try {
            Connection connection = dataSourceTransactionManager.getDataSource().getConnection();
            BsSimpleExecutor bsSimpleExecutor = new BsSimpleExecutor();
            bsSimpleExecutor.setConnection(connection);
            return bsSimpleExecutor.query(sql, cls, args);

        } catch (Exception exception) {
            platformTransactionManager.rollback(transactionStatus);
        }
        return null;
    }

    public int count(String sql, Object... args) {
        BsHashMap<String, Object> map = get();
        PlatformTransactionManager platformTransactionManager = (PlatformTransactionManager) map.get("platformTransactionManager");
        DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager) map.get("dataSourceTransactionManager");
        TransactionStatus transactionStatus = (TransactionStatus) map.get("transactionStatus");
        try {
            Connection connection = dataSourceTransactionManager.getDataSource().getConnection();
            BsSimpleExecutor bsSimpleExecutor = new BsSimpleExecutor();
            bsSimpleExecutor.setConnection(connection);
            int result = bsSimpleExecutor.count(sql, args);
            return result;
        } catch (Exception exception) {
            platformTransactionManager.rollback(transactionStatus);
        }
        return 0;
    }

    private class BsSimpleExecutor extends AbstractSimpleExecutor {
        @Override
        public void setConnection(Connection connection) {
            super.setConnection(connection);
        }
    }

    private BsHashMap<String, Object> get() {
        // 获取事务管理
        PlatformTransactionManager platformTransactionManager = applicationContext.getBean("bsTxManager", PlatformTransactionManager.class);
        // 事务定义信息
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        // 设置事务超时时间
        defaultTransactionDefinition.setTimeout(100);
        // 设置事务隔离级别
        defaultTransactionDefinition.setIsolationLevel(BsTransactionDefinition.ISOLATION_READ_COMMITTED);
        // 设置事务传播行为
        defaultTransactionDefinition.setPropagationBehavior(BsTransactionDefinition.PROPAGATION_REQUIRED);
        // 事务具体的运行状态
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(defaultTransactionDefinition);
        DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager) platformTransactionManager;
        BsHashMap bsHashMap = BsHashMap.newHashMap(3);
        bsHashMap.put("platformTransactionManager", platformTransactionManager);
        bsHashMap.put("dataSourceTransactionManager", dataSourceTransactionManager);
        bsHashMap.put("transactionStatus", transactionStatus);
        return bsHashMap;
    }
}
