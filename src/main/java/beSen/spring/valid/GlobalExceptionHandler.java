package beSen.spring.valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

/**
 * RestControllerAdvice 可以指定 basePackages 的 controller
 * @author 康盼Java开发工程师
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /**
     * ValidationException 异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleException(Exception exception) {
        printTrace(exception);
        return ResponseEntity.builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value()).build();
    }

    private void printTrace(Throwable e) {
        getStackTrace(e);
        for(StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.info("lineNumber:{}, className:{}, fileName:{}, methodName:{},",stackTraceElement.getLineNumber(),
                    stackTraceElement.getClassName(),
                    stackTraceElement.getFileName(),
                    stackTraceElement.getMethodName());
        }
    }

    /**
     * 获取堆栈信息
     * @param e
     */
    private void getStackTrace(Throwable e) {
        log.info("e.getMessage:{}", e.getMessage());
        StringWriter stringWriter = new StringWriter();
        try(PrintWriter printWriter = new PrintWriter(stringWriter)) {
            e.printStackTrace(printWriter);
            log.info("e.printStackTrace:{}", stringWriter.toString());
        }
    }
}
