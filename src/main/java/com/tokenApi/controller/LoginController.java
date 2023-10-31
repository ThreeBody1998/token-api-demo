package com.tokenApi.controller;

import com.tokenApi.enums.HttpResultEnum;
import com.tokenApi.pojo.custom.ResponseResult;
import com.tokenApi.pojo.vo.LoginVO;
import com.tokenApi.pojo.vo.UserVO;
import com.tokenApi.util.CacheManagerUtil;
import com.tokenApi.util.TokenGenerateUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.tokenApi.pojo.dto.LoginDTO;
import com.tokenApi.service.LoginService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    private final CacheManagerUtil<String,UserVO> cacheManagerUtil;

    /**
     * 登录
     * @param loginDTO  登录DTO
     * @return  JSON
     */
    @GetMapping("/login")
    public ResponseResult<LoginVO> login(@RequestBody LoginDTO loginDTO){
        ResponseResult<LoginVO> responseResult=new ResponseResult<>();
        UserVO userVO = loginService.verifyLogin(loginDTO);
        if(userVO==null){
            return responseResult.setHttpResultEnum(HttpResultEnum.PARAM_IS_ERROR);
        }
        //生成token并缓存
        String token=TokenGenerateUtil.generateToken(userVO.toString());
        //存入缓存 有效期3小时
        cacheManagerUtil.put(token,userVO,CacheManagerUtil.TTL);
        //返回token和VO
        LoginVO loginVO=new LoginVO(token,userVO);
        return responseResult.setData(loginVO);
    }

    /**
     * 获取缓存用户信息
     * @return  JSON
     */
    @GetMapping("/listCacheUserInfo")
    public ResponseResult<String> listCacheUserInfo(){
        ResponseResult<String> responseResult=new ResponseResult<>();
        List<String> keySet= cacheManagerUtil.getKeys();
        StringBuilder stringBuilder=new StringBuilder();
        keySet.forEach(key-> stringBuilder.append(key).append(": ").append(cacheManagerUtil.get(key)).append("\n"));
        return responseResult.setData(stringBuilder.toString());
    }

    /**
     * 测试路由
     * @return  JSON
     */
    @GetMapping("/testRoute")
    public ResponseResult<String> testRoute(){
        ResponseResult<String> responseResult=new ResponseResult<>();
        return responseResult.setData("成功进入");
    }
}
