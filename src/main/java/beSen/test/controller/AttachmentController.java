package beSen.test.controller;

import beSen.mapper.model.Attachment;
import beSen.mapper.model.AttachmentType;
import beSen.mapper.slave.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private AttachmentType t1 = new AttachmentType("PDF");
    private AttachmentType t2 = new AttachmentType("JPG");

    private Attachment a1 = new Attachment("43243422","3242.pdf",0);
    private Attachment a2 = new Attachment("432443","555.pfd",0);
    private Attachment a3 = new Attachment("G888fds","1.jpg",0);
    private Attachment a4 = new Attachment("sdf32","2.jpg",0);
    private Attachment a5 = new Attachment("op343","54.pdf",0);



    /**
     * 劣质的写法: VO 命名不规范 不能以下划线命名 映射不了
     *
     * @return
     */
    @GetMapping("/batchInsert")
    public int batchInsert() {

        // 第一组数据 新增
        List<AttachmentType> t_list_1 = new ArrayList<>();
        List<Attachment> a_list_1 = new ArrayList<>();
        a_list_1.add(a1);a_list_1.add(a2);
        List<Attachment> a_list_2 = new ArrayList<>();
        a_list_2.add(a3);a_list_2.add(a4);
        t1.setAttachments(a_list_1);t2.setAttachments(a_list_2);
        t_list_1.add(t1);t_list_1.add(t2);

        attachmentMapper.batchInsert(t_list_1);
        t1.getAttachments().forEach(ele -> ele.setTypeId(t1.getTypeId()));
        t2.getAttachments().forEach(ele -> ele.setTypeId(t2.getTypeId()));
        List<Attachment> attachmentList = t_list_1.stream().flatMap(ele -> ele.getAttachments().stream()).collect(Collectors.toList());
        attachmentMapper.batchInsertAtt(attachmentList);

        // 第二组数据 删除 和 更新
        List<AttachmentType> t_list_2 = new ArrayList<>();
        // t1的附件删除了a1添加了a5, t2的附件已经全部删除了
        t1.getAttachments().remove(a1);
        t1.getAttachments().remove(a2);
        a2.setDocName("更新.pdf");
        t1.getAttachments().add(a2);
        t1.getAttachments().add(a5);
        t2.getAttachments().clear();
        t_list_2.add(t1);t_list_2.add(t2);
        // org.apache.ibatis.executor.ExecutorException: No constructor found in 添加无参构造方法
        List<AttachmentType> searchList = attachmentMapper.selectAttachmentType();
        // 为新增的附件赋值typeId
        t1.getAttachments().forEach(ele -> ele.setTypeId(t1.getTypeId()));
        // 先获取目前有的附件
        List<Attachment> attachments = t_list_2.stream().flatMap(ele -> ele.getAttachments().stream()).collect(Collectors.toList());
        // 获取它们的id
        String nowIds = attachments.stream().filter(ele -> ele.getId() > 0).map(ele -> String.valueOf(ele.getId())).collect(Collectors.joining());
        // 获取没有id的数据(新增)
        List<Attachment> insertList = attachments.stream().filter(ele -> ele.getId() == 0).collect(Collectors.toList());
        if (insertList.size() > 0) {
            attachmentMapper.batchInsertAtt(insertList);
        }
        List<Attachment> deleteList = searchList.stream().flatMap(ele -> ele.getAttachments().stream()).filter(ele -> !nowIds.contains(String.valueOf(ele.getId()))).collect(Collectors.toList());
        if (deleteList.size() > 0) {
            attachmentMapper.batchDeleteAtt(deleteList);
        }
        List<Attachment> updateList = searchList.stream().flatMap(ele -> ele.getAttachments().stream()).filter(ele -> nowIds.contains(String.valueOf(ele.getId()))).collect(Collectors.toList());
        if (updateList.size() > 0) {
            attachmentMapper.batchUpdateAtt(deleteList);
        }
        return 0;


    }
}
