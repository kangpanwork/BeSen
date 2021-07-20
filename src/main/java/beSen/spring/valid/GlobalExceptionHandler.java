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
 *
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
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.info("lineNumber:{}, className:{}, fileName:{}, methodName:{},", stackTraceElement.getLineNumber(),
                    stackTraceElement.getClassName(),
                    stackTraceElement.getFileName(),
                    stackTraceElement.getMethodName());
        }
    }

    /**
     * log.info("e.getMessage:{}", e.getMessage());  e.getMessage:[parameter name can not be null];
     * log.info("e.printStackTrace:{}", stringWriter.toString());  e.printStackTrace:javax.validation.ValidationException: [parameter name can not be null];
     * <p>
     * e.printStackTrace(printWriter);
     * at beSen.spring.valid.ValidExceptionUtil.validField(ValidExceptionUtil.java:99)
     * at beSen.test.controller.CoffeeController.insert(CoffeeController.java:37)
     * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * at java.lang.reflect.Method.invoke(Method.java:498)
     * at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)
     * at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)
     * at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)
     * at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:879)
     * at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:793)
     * at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
     * at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)
     * at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)
     * at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
     * at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)
     * at javax.servlet.http.HttpServlet.service(HttpServlet.java:660)
     * at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
     * at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
     * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
     * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
     * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
     * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
     * at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)
     * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
     * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
     * at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)
     * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
     * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
     * at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)
     * at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
     * at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)
     * at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
     * at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)
     * at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)
     * at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
     * at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
     * at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
     * at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:367)
     * at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
     * at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868)
     * at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1639)
     * at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
     * at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
     * at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
     * at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
     * at java.lang.Thread.run(Thread.java:748)
     * 获取堆栈信息
     *
     * @param e
     */
    private void getStackTrace(Throwable e) {
        log.info("e.getMessage:{}", e.getMessage());
        StringWriter stringWriter = new StringWriter();
        try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
            e.printStackTrace(printWriter);
            log.info("e.printStackTrace:{}", stringWriter.toString());
        }
    }
}