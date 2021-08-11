package beSen.test.test;


/**
 * @author 康盼Java开发工程师  节点关闭要抽取出来
 */
public abstract class AbstractService implements IService {

    public void agreeOrRejectOption(String option) {
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
     * 是否全部提交
     * @return
     */
    protected abstract boolean isAllCommit();

    /**
     * 是否是第一个同意
     * @return
     */
    protected abstract boolean isFirstCommit();

    /**
     * 保存流程记录
     *
     * @param obj
     */
    protected abstract void saveRecord(Object obj);
}
