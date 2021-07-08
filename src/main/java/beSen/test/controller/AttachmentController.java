package beSen.test.controller;

import beSen.mapper.model.Attachment;
import beSen.mapper.model.AttachmentType;
import beSen.mapper.slave.AttachmentMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 康盼Java开发工程师
 */
@RestController
@RequestMapping("/attach")
public class AttachmentController {

    @Autowired
    private AttachmentMapper attachmentMapper;





    /**
     * 劣质的写法: VO 命名不规范 不能以下划线命名 映射不了
     * [{"attachments":[{"docId":"43243422","docName":"3242.pdf","id":0,"typeId":0},{"docId":"432443","docName":"555.pfd","id":0,"typeId":0}],"typeId":0,"typeName":"PDF"},{"attachments":[{"docId":"G888fds","docName":"1.jpg","id":0,"typeId":0},{"docId":"sdf32","docName":"2.jpg","id":0,"typeId":0}],"typeId":0,"typeName":"JPG"}]
     *
     * @return
     */
    @GetMapping("/batchInsert")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int batchInsert(List<AttachmentType> list) {
        // org.apache.ibatis.executor.ExecutorException: No constructor found in 添加无参构造方法
        List<AttachmentType> searchList = attachmentMapper.selectAttachmentType();
        String nowTypeIds =  list.stream().map(ele -> String.valueOf(ele.getTypeId())).collect(Collectors.joining());
        List<AttachmentType> insertTypeList = list.stream().filter(ele -> ele.getTypeId() == 0).collect(Collectors.toList());
        if (insertTypeList.size() > 0) {
            attachmentMapper.batchInsert(insertTypeList);
        }
        List<AttachmentType> updateTypeList = list.stream().filter(ele -> ele.getTypeId() > 0).collect(Collectors.toList());
        if (updateTypeList.size() > 0) {
        }
        List<AttachmentType> deleteTypeList =  searchList.stream().filter(ele -> !nowTypeIds.contains(String.valueOf(ele.getTypeId()))).collect(Collectors.toList());
        if (deleteTypeList.size() > 0) {
        }
        list.stream().forEach(ele -> ele.getAttachments().forEach(e -> e.setTypeId(ele.getTypeId())));
        List<Attachment> attachmentList = list.stream().flatMap(ele -> ele.getAttachments().stream()).collect(Collectors.toList());
        List<Attachment> insertList = attachmentList.stream().filter(ele -> ele.getId() == 0).collect(Collectors.toList());
        attachmentMapper.batchInsertAtt(insertList);
        String nowIds = attachmentList.stream().filter(ele -> ele.getId() > 0).map(ele -> String.valueOf(ele.getId())).collect(Collectors.joining());
        if (insertList.size() > 0) {
            attachmentMapper.batchInsertAtt(insertList);
        }
        List<Attachment> deleteList = searchList.stream().flatMap(ele -> ele.getAttachments().stream()).filter(ele -> !nowIds.contains(String.valueOf(ele.getId()))).collect(Collectors.toList());
        if (deleteList.size() > 0) {
            attachmentMapper.batchDeleteAtt(deleteList);
        }
        List<Attachment> updateList = attachmentList.stream().filter(ele -> nowIds.contains(String.valueOf(ele.getId()))).collect(Collectors.toList());
        if (updateList.size() > 0) {
            attachmentMapper.batchUpdateAtt(deleteList);
        }
        return 0;


    }
}
