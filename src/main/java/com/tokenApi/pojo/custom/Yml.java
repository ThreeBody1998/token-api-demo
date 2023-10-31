package com.tokenApi.pojo.custom;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/**
 * description yml配置文件对象
 * @author 周建泽
 * @date 2023/10/21
 */
@Configuration
@Getter
public class Yml {
    /**
     * jwt key
     */
    @Value("${jwt.key}")
    private String key;
    /**
     * jwt ttl
     */
    @Value("${jwt.ttl}")
    private Long ttl;
}
