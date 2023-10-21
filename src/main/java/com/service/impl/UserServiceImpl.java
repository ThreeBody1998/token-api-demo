package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pojo.entity.User;
import com.service.UserService;
import com.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zhoujianze
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-21 18:24:29
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




