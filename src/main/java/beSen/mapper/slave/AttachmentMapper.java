package beSen.mapper.slave;

import beSen.mapper.model.Attachment;
import beSen.mapper.model.AttachmentType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public interface AttachmentMapper {
    /**
     * 插入附件类型表 oracle写法 测试
     *
     * @param list
     * @return
     */
    int batchInsertTest(List<AttachmentType> list);


    /**
     * 插入附件类型表
     *
     * @param list
     * @return
     */
    int batchInsert(List<AttachmentType> list);

    /**
     * 插入附件表
     *
     * @param list
     * @return
     */
    int batchInsertAtt(List<Attachment> list);


    /**
     * 查询附件类型及附件
     *
     * @return
     */
    List<AttachmentType> selectAttachmentType();

    /**
     * 根据type_id批量删除附件类型表
     *
     * @param list
     * @return
     */
    int batchDeleteAtt(List<Attachment> list);

    /**
     * 批量更新附件表
     *
     * @param list
     * @return
     */
    int batchUpdateAtt(List<Attachment> list);

}
