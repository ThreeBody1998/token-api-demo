package com.service;

import com.pojo.dto.LoginDTO;
import com.pojo.vo.UserVO;

/**
 * description 登录接口类
 * @author 周建泽
 * @date 2023/10/16
 */
public interface LoginService {
    /**
     * 登录校验
     * @return  布尔值
     */
    UserVO verifyLogin(LoginDTO loginDTO);
}
