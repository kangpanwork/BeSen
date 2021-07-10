package beSen.test.controller;

import beSen.mapper.model.Attachment;
import beSen.mapper.model.AttachmentType;
import beSen.mapper.slave.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 康盼Java开发工程师
 */
@RestController
@RequestMapping("/attachmentController")
public class AttachmentController {

    public AttachmentController() {

    }

    /**
     * 构造注入的，使用要加个无参构造方法，CGLIB
     *
     */
    @Autowired
    private AttachmentMapper attachmentMapper;





    /**
     * 劣质的写法: VO 命名不规范 不能以下划线命名 映射不了
     * 插入测试JSON
     * [{"attachments":[{"docId":"43243422","docName":"3242.pdf","id":0,"typeId":0},{"docId":"432443","docName":"555.pfd","id":0,"typeId":0}],"typeId":0,"typeName":"PDF"},{"attachments":[{"docId":"G888fds","docName":"1.jpg","id":0,"typeId":0},{"docId":"sdf32","docName":"2.jpg","id":0,"typeId":0}],"typeId":0,"typeName":"JPG"}]
     * 更新测试JSON
     * [{"attachments": [{"docId": "43243422","docName": "3242.pdf","id": 1,"typeId": 1}, {"docId": "222","docName": "2.pfd","id": 2,"typeId": 1}],"typeId": 1,"typeName": "PDF"}, {"attachments": [{"docId": "jpg1","docName": "1.jpg","id": 3,"typeId": 2}],"typeId": 2,"typeName": "JPG"}]
     * @return
     */
    @PostMapping("/batchInsert")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int batchInsert(@RequestBody List<AttachmentType> list) {
        // org.apache.ibatis.executor.ExecutorException: No constructor found in 添加无参构造方法
        // 查询数据库中现有的数据
        List<AttachmentType> searchList = attachmentMapper.selectAttachmentType();
        // 获取传参中附件类型的 ID 字符串
        String nowTypeIds =  list.stream().map(ele -> String.valueOf(ele.getTypeId())).collect(Collectors.joining());
        // 当 ID==0 说明时要新增
        List<AttachmentType> insertTypeList = list.stream().filter(ele -> ele.getTypeId() == 0).collect(Collectors.toList());
        // 这行代码不能放在 batchInsert 之后，否则之前为 0 的插入后都大于0
        List<AttachmentType> updateTypeList = list.stream().filter(ele -> ele.getTypeId() > 0).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(insertTypeList)) {
            attachmentMapper.batchInsert(insertTypeList);
        }

        if (!CollectionUtils.isEmpty(updateTypeList)) {
            attachmentMapper.batchUpdate(updateTypeList);
        }
        List<AttachmentType> deleteTypeList =  Optional.ofNullable(searchList).filter(li -> !CollectionUtils.isEmpty(li)).map(li -> li.stream()).orElse(new ArrayList<AttachmentType>().stream()).filter(ele -> !nowTypeIds.contains(String.valueOf(ele.getTypeId()))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteTypeList)) {
            attachmentMapper.batchDelete(deleteTypeList);
        }
        list.stream().forEach(ele -> ele.getAttachments().forEach(e -> e.setTypeId(ele.getTypeId())));
        List<Attachment> attachmentList = list.stream().flatMap(ele -> ele.getAttachments().stream()).collect(Collectors.toList());
        List<Attachment> insertList = attachmentList.stream().filter(ele -> ele.getId() == 0).collect(Collectors.toList());
        List<Attachment> updateList = attachmentList.stream().filter(ele -> ele.getId() > 0).collect(Collectors.toList());
        // 这行代码不能放在插入之后，因为插入之后，id 会变成大于 0
        String nowIds = attachmentList.stream().filter(ele -> ele.getId() > 0).map(ele -> String.valueOf(ele.getId())).collect(Collectors.joining());

        if(!CollectionUtils.isEmpty(insertList)) {
            attachmentMapper.batchInsertAtt(insertList);
        }

        List<Attachment> deleteList = Optional.ofNullable(searchList).filter(li -> !CollectionUtils.isEmpty(li)).map(li -> li.stream()).orElse(new ArrayList<AttachmentType>().stream()).flatMap(ele -> ele.getAttachments().stream()).filter(ele -> !nowIds.contains(String.valueOf(ele.getId()))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteList)) {
            attachmentMapper.batchDeleteAtt(deleteList);
        }
        if (!CollectionUtils.isEmpty(updateList)) {
            attachmentMapper.batchUpdateAtt(updateList);
        }
        return 0;


    }

    @PostMapping("/test")
    public int test() {
        return 0;
    }

}
