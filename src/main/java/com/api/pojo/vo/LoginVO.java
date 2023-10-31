package com.api.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * description 登录VO
 * @author 周建泽
 * @date 2023/10/16
 */
@AllArgsConstructor
@Data
public class LoginVO {
    /**
     * token
     */
    private String token;
    /**
     * 用户信息
     */
    private UserVO user;
}
