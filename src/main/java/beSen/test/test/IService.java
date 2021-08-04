package beSen.test.test;

/**
 * @author 康盼Java开发工程师
 */
public interface IService {

    /**
     * 同意和驳回
     */
    void agreeAndReject();

    /**
     * 是否全部提交
     * @return
     */
    boolean isAllCommit();

    /**
     * 流程记录
     * @return
     */
    Object procedureRecord();
}
