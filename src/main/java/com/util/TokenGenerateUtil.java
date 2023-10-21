package com.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * description token生成工具类
 * @author 周建泽
 * @date 2023/10/13
 */
@Slf4j
public class TokenGenerateUtil {
    /**
     * 盐值-替换为你自己的盐值
     */
    private static final String SALT_VALUE="saltValue";
    /**
     * 生成盐值的算法名称
     */
    private static final String SALT_ALGORITHM = "SHA-1";
    /**
     * hash算法名称
     */
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * 生成token
     * @param data  传入的数据
     * @return  返回token
     */
    public static String generateToken(String data) {
        String token = null;
        try {
            //时间戳
            String timeStamp=String.valueOf(System.currentTimeMillis());
            // 添加盐
            String dataWithSalt = data + SALT_VALUE+timeStamp;
            // 创建SHA-1散列
            MessageDigest saltDigest = MessageDigest.getInstance(SALT_ALGORITHM);
            saltDigest.update(SALT_VALUE.getBytes());
            byte[] saltBytes = saltDigest.digest();
            // 使用Base64编码
            String saltBase64 = Base64.getEncoder().encodeToString(saltBytes);
            // 将数据与盐的散列值结合，然后计算SHA-256散列
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update((dataWithSalt + saltBase64).getBytes());
            // 使用Base64编码
            byte[] byteData = md.digest();
            token = Base64.getEncoder().encodeToString(byteData);
        } catch (NoSuchAlgorithmException e) {
            log.error("生成token失败，未找到对应的算法:{}",e.getMessage());
        }
        return token;
    }

    public static void main(String[] args) {
        System.out.println(TokenGenerateUtil.generateToken("123"));
    }
}
