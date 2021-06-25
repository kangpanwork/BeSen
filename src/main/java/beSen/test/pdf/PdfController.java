package beSen.test.pdf;

import beSen.pdf.config.PdfExportConfig;
import beSen.pdf.util.PDFUtil;
import beSen.test.pdf.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfExportConfig pdfExportConfig;

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPdf() throws Exception {
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setStuNo("stuNO1.");
        student.setStuName("小明");
        student.setStuAge(20);
        student.setStuSex("男");
        student.setStuClass("班级（一）");
        students.add(student);
        Map<String, List<?>> dataMap = new HashMap<>();
        dataMap.put("students", students);
        HttpHeaders headers = new HttpHeaders();
        String htmlStr = PDFUtil.freemarkerRender(dataMap, pdfExportConfig.getFltTemplate());
        byte[] pdfBytes = PDFUtil.createPdf(htmlStr, pdfExportConfig.getFont());
        headers.setContentDispositionFormData("attachment", "student.pdf");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
    }
}
