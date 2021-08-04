package beSen.test.test;

/**
 * @author 康盼Java开发工程师
 * 给别人调用的 关于提交动作的需要调用的一些服务
 */
public interface IService {

    /**
     * 批量同意和驳回
     */
    void batchAgreeAndReject();

    /**
     * 单个同意和驳回
     */
    void agreeAndReject();

    /**
     * 全部同意的并且上一个节点完成调用：流程记录
     * @return
     */
    Object procedureRecord();


    /**
     * 同意和驳回调用：初始化数据
     */
    void initData();

    /**
     * 第一次同意的时候调用：行云关闭当前节点，开启下个节点
     */
    void closeCurrentNodeAndOpenNextNodeForYun();


    /**
     * 驳回时候调用：开启前一个节点
     */
    void openPreviousNode();

    /**
     * 全部时候调用：关闭当前节点
     */
    void closeCurrentNode();
}
