package beSen.mapper.model;

import java.util.List;

/**
 * 附件类型
 * @author 康盼Java开发工程师
 */
public class AttachmentType {
    private int typeId;
    private String typeName;
    private List<Attachment> list;

//    public int getType_id() {
//        return type_id;
//    }
//
//    public void setType_id(int type_id) {
//        this.type_id = type_id;
//    }
//
//    public String getType_name() {
//        return type_name;
//    }
//
//    public void setType_name(String type_name) {
//        this.type_name = type_name;
//    }


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

    public List<Attachment> getList() {
        return list;
    }

    public void setList(List<Attachment> list) {
        this.list = list;
    }
}
