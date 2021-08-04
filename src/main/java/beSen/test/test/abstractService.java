package beSen.test.test;

/**
 * @author 康盼Java开发工程师  节点关闭要抽取出来
 */
public abstract class AbstractService implements IService {

    public void agreeOrRejectOption(String option) {
        agreeAndReject();
        if ("agree".equals(option)) {
            boolean isAllCommit = isAllCommit();
            if (isAllCommit) {
                Object obj = procedureRecord();
                saveRecord(obj);
                closeCurrentNode();
            }
            boolean isFirstCommit = isFirstCommit();
            if (isFirstCommit) {
                closeCurrentNodeAndOpenNextNodeForYun();
            }
        } else {
            openPreviousNode();
        }
        initData();
    }

    /**
     * 行云关闭当前节点，开启下个节点
     */
    protected abstract void closeCurrentNodeAndOpenNextNodeForYun();


    /**
     * 是否是第一个同意
     * @return
     */
    protected abstract boolean isFirstCommit();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 开启前一个节点
     */
    protected abstract void openPreviousNode();

    /**
     * 关闭当前节点
     */
    protected abstract void closeCurrentNode();

    /**
     * 保存流程记录
     *
     * @param obj
     */
    protected abstract void saveRecord(Object obj);
}
