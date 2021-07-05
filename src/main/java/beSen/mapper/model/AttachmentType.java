package beSen.mapper.model;

import java.util.List;

/**
 * 附件类型
 * @author 康盼Java开发工程师
 */
public class AttachmentType {
    private int type_id;
    private String type_name;
    private List<Attachment> attachmentList;

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
