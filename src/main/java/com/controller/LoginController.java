package com.controller;

import com.enums.HttpResultEnum;
import com.pojo.vo.LoginVO;
import com.pojo.vo.UserVO;
import com.util.CacheManagerUtil;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pojo.dto.LoginDTO;
import com.service.LoginService;
import com.util.TokenGenerateUtil;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    private final CacheManagerUtil<String,UserVO> cacheManagerUtil;

    public LoginController(LoginService loginService, CacheManagerUtil<String, UserVO> cacheManagerUtil) {
        this.loginService = loginService;
        this.cacheManagerUtil = cacheManagerUtil;
    }

    /**
     * 登录
     * @param loginDTO  登录DTO
     * @return  JSON
     */
    @GetMapping("/login")
    public JSONObject login(@RequestBody LoginDTO loginDTO){
        JSONObject jsonObject=new JSONObject();
        int code= HttpResultEnum.SUCCESS.getCode();
        String msg="登录成功";
        //这里的‘loginService.verifyLogin()’方法我默认返回一个对象，实际根据自己的业务逻辑进行判定
        UserVO userVO = loginService.verifyLogin(loginDTO);
        if(userVO==null){
            code=HttpResultEnum.PARAM_NOT_ERROR.getCode();
            msg="账号或密码错误";
            jsonObject.put("code",code);
            jsonObject.put("msg",msg);
        }
        //生成token并缓存
        String token= TokenGenerateUtil.generateToken(loginDTO.getAccount());
        //存入缓存 有效期3小时
        cacheManagerUtil.put(token,userVO,1000*60*60*3L);
        //返回token和VO
        LoginVO loginVO=new LoginVO(token,userVO);
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        jsonObject.put("data",loginVO);
        return jsonObject;
    }

    /**
     * 获取缓存用户信息
     * @return  JSON
     */
    @GetMapping("/listCacheUserInfo")
    public JSONObject listCacheUserInfo(){
        JSONObject jsonObject=new JSONObject();
        int code= HttpResultEnum.SUCCESS.getCode();
        String msg="登录成功";
        List<String> keySet= cacheManagerUtil.getKeys();
        StringBuilder stringBuilder=new StringBuilder();
        keySet.forEach(key-> stringBuilder.append(key).append(": ").append(cacheManagerUtil.get(key)).append("\n"));
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        jsonObject.put("data",stringBuilder.toString());
        return jsonObject;
    }

}
