package com.tokenApi.service;

import com.tokenApi.entity.User;
import com.tokenApi.pojo.dto.LoginDTO;
import com.tokenApi.pojo.vo.UserVO;

import java.util.List;

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

    /**
     * 根据账号密码查询
     * @param account   账号
     * @param password  密码
     * @return  用户列表
     */
    List<User> selectByAccountAndPassword(String account, String password);
}
