package com.enums;

/**
 *  Http请求结果枚举类
 */
public enum HttpResultEnum {

    /**
     * 成功编号 200
     */
    SUCCESS(200,"操作成功"),
    /**
     * 参数为空 400
     */
    PARAM_IS_NULL(400,"参数为空"),

    /**
     * 参数不合法 401
     */
    PARAM_NOT_VALID(401,"参数不合法"),
    /**
     * 参数错误 402
     */
    PARAM_NOT_ERROR(402,"参数不合法"),
    /**
     * 操作不合法 402
     */
    OPERATE_VALID(403,"操作不合法"),
    /**
     * 权限不足 403
     */
    AUTHORITY_NOT_VALID(404,"权限不足"),
    /**
     * 未知异常 500
     */
    SERVER_ERROR(500,"服务器异常");

    /**
     * 根据code获取枚举
     * @param code  编号
     * @return
     */
    public static HttpResultEnum getHttpResultEnumByCode(Integer code){
        for(HttpResultEnum httpResultEnum:HttpResultEnum.values()){
            if(httpResultEnum.getCode().equals(code)){
                return httpResultEnum;
            }
        }
        return null;
    }

    private final Integer code;

    private final String msg;

    HttpResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
