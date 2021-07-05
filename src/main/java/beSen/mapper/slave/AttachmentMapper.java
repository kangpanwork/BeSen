package beSen.mapper.slave;

import beSen.mapper.model.AttachmentType;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public interface AttachmentMapper {
//    /**
//     * 插入附件类型表
//     *
//     * @param list
//     * @return
//     */
//    int batchInsert(List<AttachmentType> list);

    /**
     * 查询
     *
     * @return
     */
    List<AttachmentType> selectAll();
}
