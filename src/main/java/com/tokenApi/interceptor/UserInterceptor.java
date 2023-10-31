package com.tokenApi.interceptor;

import com.tokenApi.enums.HttpResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokenApi.pojo.custom.ResponseResult;
import com.tokenApi.pojo.vo.UserVO;
import com.tokenApi.util.CacheManagerUtil;
import com.tokenApi.util.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 拦截器
 * @author 周建泽
 * @date 2022/5/31
 */
@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    /**
     * token在请求头中的存储位置
     */
    private static final String TOKEN_HEADER_STORAGE_NAME="Authorization";

    private final ObjectMapper objectMapper;

    private final CacheManagerUtil<String, UserVO> cacheManagerUtil;

    public UserInterceptor(ObjectMapper objectMapper, CacheManagerUtil<String, UserVO> cacheManagerUtil) {
        this.objectMapper = objectMapper;
        this.cacheManagerUtil = cacheManagerUtil;
    }

    /**
     * 请求处理之前对用户信息进行处理
     * @param request   请求
     * @param response  响应
     * @param handler   处理器
     * @return  是否通过
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token=request.getHeader(TOKEN_HEADER_STORAGE_NAME);
        //验证token是否存在
        if(!StringUtils.hasText(token)){
            sendResponse(response,HttpResultEnum.TOKEN_IS_EMPTY);
            return false;
        }
        //验证token是否有效
        try {
            UserVO userVO= cacheManagerUtil.get(token);
            if(userVO==null){
                sendResponse(response,HttpResultEnum.TOKEN_IS_INVALID);
                return false;
            }
            ContextUtil.setCurrentUser(userVO);
            return true;
        } catch (Exception e){
            sendResponse(response,HttpResultEnum.TOKEN_IS_INVALID);
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {
        ContextUtil.clearCurrentUser();
    }

    /**
     * 手动设置响应
     * @param response  响应
     * @param httpResultEnum    响应结果枚举
     * @throws IOException  IO异常
     */
    public void sendResponse(HttpServletResponse response,HttpResultEnum httpResultEnum) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        ResponseResult<String> responseResult=new ResponseResult<>(httpResultEnum);
        response.getWriter().write(objectMapper.writeValueAsString(responseResult.toJsonString()));
    }


}
