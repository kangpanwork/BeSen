package beSen.mapper.slave;

import beSen.mapper.model.AttachmentType;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public interface AttachmentMapper {
    /**
     * 插入附件类型表
     *
     * @param list
     * @return
     */
    int batchInsert(List<AttachmentType> list);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert2(List<AttachmentType> list);


    /**
     * 根据type_id批量删除
     *
     * @param list
     * @return
     */
    int batchDelete(List<Integer> list);

    /**
     * 查询
     *
     * @return
     */
    List<AttachmentType> selectAll();
}
