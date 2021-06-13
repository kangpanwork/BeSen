package beSen.veryins.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//CREATE TABLE `t_photo` (
//        `id` bigint(20) NOT NULL AUTO_INCREMENT,
//        `name` varchar(1000) DEFAULT NULL,
//        `photo` mediumblob,
//        `category` varchar(50) DEFAULT NULL,
//        `next` varchar(1000) DEFAULT NULL,
//        `time` timestamp NULL DEFAULT NULL,
//        PRIMARY KEY (`id`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/**
 * 图片信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Photo {
    private Long id;
    private String name;
    private byte[] photo;
    private String category;
    @JsonFormat(pattern="yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date time;
    private String next;
}
