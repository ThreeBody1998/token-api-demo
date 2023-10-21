package com.service.impl;

import com.pojo.dto.LoginDTO;
import com.pojo.vo.UserVO;
import com.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * description 登录接口实现类
 * @author 周建泽
 * @date 2023/10/16
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public UserVO verifyLogin(LoginDTO loginDTO) {
        UserVO userVO = new UserVO();
        userVO.setUserName("张三");
        userVO.setSex("男");
        return userVO;
    }
}
