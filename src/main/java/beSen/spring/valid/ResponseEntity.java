package beSen.spring.valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 康盼Java开发工程师
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回码
     */
    private Integer status;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 时间
     */
    @JsonFormat(pattern="yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime time;
}
