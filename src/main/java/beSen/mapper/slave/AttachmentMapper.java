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
     * 插入附件类型表
     *
     * @param list
     * @return
     */
    int batchInsert(List<AttachmentType> list);

    /**
     * 批量插入附件类型表
     *
     * @param list
     * @return
     */
    int batchInsert2(List<AttachmentType> list);

    /**
     * 批量插入附件表
     *
     * @param list
     * @return
     */
    int batchInsert3(List<Attachment> list);


    /**
     * 根据type_id批量删除附件类型表
     *
     * @param list
     * @return
     */
    int batchDelete(List<Integer> list);

    /**
     * 查询附件类型表
     *
     * @return
     */
    List<AttachmentType> selectAll();

    /**
     * 根据类型查找附件类型表
     *
     * @param type_name
     * @return
     */
    AttachmentType select(@Param("type_name")String type_name);

    /**
     * 根据type_id查询附件
     *
     * @param type_id
     * @return
     */
    List<Attachment> selectAll2(int type_id);

    /**
     * 批量更新附件类型表
     *
     * @param list
     * @return
     */
    int batchUpdate(List<AttachmentType> list);

    /**
     * 批量更新附件表
     *
     * @param list
     * @return
     */
    int batchUpdate2(List<Attachment> list);

}
