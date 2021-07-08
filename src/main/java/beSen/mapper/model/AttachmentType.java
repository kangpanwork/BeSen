package beSen.mapper.model;



import java.util.List;

/**
 * 附件类型
 * @author 康盼Java开发工程师
 */
public class AttachmentType {
    private int typeId;
    private String typeName;
    private List<Attachment> attachments;

    public AttachmentType(String typeName) {
        this.typeName = typeName;
    }

    public AttachmentType() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
