package com.api.pojo.vo;

import com.api.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description 用户VO类
 * @author 周建泽
 * @date 2023/10/21
 */
@NoArgsConstructor
@Data
public class UserVO {
    /**
     * 用户名
     */
    private String userName;

    public UserVO(User user){
        this.userName=user.getName();
    }
}
