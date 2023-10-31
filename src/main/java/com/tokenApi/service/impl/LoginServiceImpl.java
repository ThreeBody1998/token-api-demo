package com.tokenApi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tokenApi.entity.User;
import com.tokenApi.mapper.UserMapper;
import com.tokenApi.pojo.dto.LoginDTO;
import com.tokenApi.pojo.vo.UserVO;
import com.tokenApi.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description 登录接口实现类
 * @author 周建泽
 * @date 2023/10/16
 */
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;

    @Override
    public UserVO verifyLogin(LoginDTO loginDTO) {
        List<User> userList=selectByAccountAndPassword(loginDTO.getAccount(),loginDTO.getPassword());
        if(userList.isEmpty()){
            return new UserVO();
        }
        User user=userList.get(0);
        return new UserVO(user);
    }

    @Override
    public List<User> selectByAccountAndPassword(String account, String password) {
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("account",account).eq("password",password);
        return userMapper.selectList(userQueryWrapper);
    }
}
