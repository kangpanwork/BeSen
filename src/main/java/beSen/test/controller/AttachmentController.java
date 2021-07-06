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

    @GetMapping("/batchInsert")
    public int batchInsert() {
        List<AttachmentType> list = new ArrayList<>();
        AttachmentType a1 = new AttachmentType();
        a1.setType_id(1);
        a1.setType_name("画册");
        AttachmentType a2 = new AttachmentType();
        a2.setType_id(2);
        a2.setType_name("图书");

        List<Attachment> addList1 = new ArrayList<>();
        Attachment t1 = new Attachment();
        t1.setDoc_id("080980970709");
        t1.setDoc_name("梵高.画册");
        t1.setType_id(1);
        addList1.add(t1);
        a1.setList(addList1);

        List<Attachment> addList2 = new ArrayList<>();
        Attachment t2 = new Attachment();
        t2.setDoc_id("432432432432424");
        t2.setDoc_name("梵高.图书");
        t2.setType_id(2);
        addList2.add(t2);
        a2.setList(addList2);

        list.add(a1);
        list.add(a2);

        List<AttachmentType> insertList = new ArrayList<>();
        List<AttachmentType> updateList = new ArrayList<>();

        List<Attachment> insertList2 = new ArrayList<>();
        List<Attachment> updateList2 = new ArrayList<>();


        for(AttachmentType attachmentType : list) {
            if(attachmentType.getType_id() > 0) {
                updateList.add(attachmentType);
            } else {
                insertList.add(attachmentType);
            }
        }


//        List<AttachmentType> result = attachmentMapper.selectAll();
//        if (result != null && result.size() > 0) {
//            List<Integer> typeIds = result.stream().map(AttachmentType::getType_id).collect(Collectors.toList());
//            attachmentMapper.batchDelete(typeIds);
//        }
        attachmentMapper.batchInsert3(addList2);
        attachmentMapper.batchInsert3(addList1);

        return 0;
    }
}
