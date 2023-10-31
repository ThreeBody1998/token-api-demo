package com.api.pojo.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * description 登录DTO
 * @author 周建泽
 * @date 2023/10/16
 */
@Data
@Validated
public class LoginDTO {
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
