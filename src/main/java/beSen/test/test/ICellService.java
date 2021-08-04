package beSen.test.test;

import java.util.List;

/**
 * 跟数据库有关的
 * @author 康盼Java开发工程师
 */
public interface ICellService {


    /**
     * 同意和驳回
     */
    void agreeAndReject();

    /**
     * 查询子节点完成数量和总数及上一个大节点的状态
     * @return
     */
    String queryCountAndPreNodeInfo();

    /**
     * 流程记录
     * @return
     */
    Object procedureRecord();


    /**
     * 批量更新子节点状态
     */
    void batchUpdate();


    /**
     * 设置默认值
     */
    void defaultGeneratorData();


    /**
     * 查询初始化的进行中数据
     * @return
     */
    List<Object> queryInitStartData();

    /**
     * 更新主节点状态
     */
    void updateNode();


}
