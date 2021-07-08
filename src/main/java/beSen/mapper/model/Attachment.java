package beSen.mapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 附件
 * @author 康盼Java开发工程师
 */
@Data
public class Attachment {
    private int id;
    private String docId;
    private String docName;
    private int typeId;

    public Attachment() {
    }

    public Attachment(String docId, String docName) {
        this.docId = docId;
        this.docName = docName;
    }

    public Attachment(String docId, String docName, int typeId) {
        this.docId = docId;
        this.docName = docName;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
