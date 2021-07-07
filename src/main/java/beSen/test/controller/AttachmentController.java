package beSen.test.controller;

import beSen.mapper.model.AttachmentType;
import beSen.mapper.slave.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@RestController
@RequestMapping("/attach")
public class AttachmentController {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @GetMapping("/selectAll")
    public List<AttachmentType> attachmentTypeList() {
        return attachmentMapper.selectAll();
    }

    /**
     * 劣质的写法: VO 命名不规范 不能以下划线命名 映射不了
     *
     * @return
     */
    @GetMapping("/batchInsert")
    public int batchInsert() {
//        List<AttachmentType> list = new ArrayList<>();
//        AttachmentType a1 = new AttachmentType();
//        a1.setType_name("画册");
//        AttachmentType a2 = new AttachmentType();
//        a2.setType_name("图书");
//        list.add(a1);
//        list.add(a2);
//
//        List<AttachmentType> insertList = new ArrayList<>();
//        List<AttachmentType> updateList = new ArrayList<>();
//        List<Integer> deleteList = new ArrayList<>();
//
//        List<AttachmentType> result = attachmentMapper.selectAll();
//        String type_ids = list.stream().filter(e -> e.getType_id() > 0).map(e -> String.valueOf(e.getType_id())).collect(Collectors.joining(","));
//        if (type_ids != null && !type_ids.isEmpty()) {
//            deleteList = result.stream().filter(e -> !type_ids.contains(String.valueOf(e.getType_id()))).map(AttachmentType::getType_id).collect(Collectors.toList());
//        }
//        for (AttachmentType attachmentType : list) {
//            if (attachmentType.getType_id() > 0) {
//                updateList.add(attachmentType);
//            } else {
//                insertList.add(attachmentType);
//            }
//        }
//
//        if (!CollectionUtils.isEmpty(deleteList)) {
//            attachmentMapper.batchDelete(deleteList);
//        }
//        if (!CollectionUtils.isEmpty(insertList)) {
//            attachmentMapper.batchInsert2(insertList);
//        }
//        if (!CollectionUtils.isEmpty(updateList)) {
//            attachmentMapper.batchUpdate(updateList);
//        }
        return 0;
    }


    /**
     * 劣质的写法
     *
     * @return
     */
    @GetMapping("/batchInsert2")
    public int batchInsert2() {
        AttachmentType attachmentType1 = attachmentMapper.select("画册");
        AttachmentType attachmentType2 = attachmentMapper.select("图书");
//        int type_id1 = attachmentType1.getType_id();
//        int type_id2 = attachmentType2.getType_id();
//
//
//        List<Attachment> addList1 = new ArrayList<>();
//        Attachment t1 = new Attachment();
//        t1.setDoc_id("080980970709");
//        t1.setDoc_name("梵高.画册1");
//        t1.setType_id(type_id1);
//        Attachment t2 = new Attachment();
//        t2.setDoc_id("660970709");
//        t2.setDoc_name("梵高.画册2");
//        t2.setType_id(type_id1);
//        addList1.add(t1);
//        addList1.add(t2);
//
//        List<Attachment> addList2 = new ArrayList<>();
//        Attachment tt1 = new Attachment();
//        tt1.setDoc_id("432432432432424");
//        tt1.setDoc_name("梵高.图书1");
//        tt1.setType_id(type_id2);
//        Attachment tt2 = new Attachment();
//        tt2.setDoc_id("42342536111");
//        tt2.setDoc_name("梵高.图书2");
//        tt2.setType_id(type_id2);
//        addList2.add(tt1);
//        addList2.add(tt2);
//        attachmentMapper.batchInsert3(addList1);
//        attachmentMapper.batchInsert3(addList2);
        return 0;
    }
}
