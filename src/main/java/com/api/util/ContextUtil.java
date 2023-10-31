package com.api.util;

import com.api.pojo.vo.UserVO;
import org.springframework.lang.Nullable;
import java.util.Optional;

/**
 * description 上下文工具类
 * @author 周建泽
 * @date 2023/10/27
 */
public final class ContextUtil {
    /**
     * 获取当前用户id
     * @return  用户id
     */
    public static String getCurrentUserName() {
        return Optional.ofNullable(getCurrentUser()).map(UserVO::getUserName).orElseThrow(() -> new RuntimeException("获取当前用户失败"));
    }

    /**
     * 用户线程变量
     */
    private static final ThreadLocal<UserVO> USER_HOLDER = new ThreadLocal<>();

    /**
     * set方法
     * @param user  user
     */
    public static void setCurrentUser(UserVO user) {
        USER_HOLDER.set(user);
    }

    /**
     * get方法
     * @return  UserVO
     */
    @Nullable
    public static UserVO getCurrentUser() {
        return USER_HOLDER.get();
    }

    /**
     * 清除当前用户
     */
    public static void clearCurrentUser() {
        USER_HOLDER.remove();
    }

    /**
     * 工具类不需要生成实例
     */
    private ContextUtil() {
    }


}
