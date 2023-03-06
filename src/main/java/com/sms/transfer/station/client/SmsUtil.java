/*
 * Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution. Neither the name of
 * the dreamlu.net developer nor the names of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission. Author: Chill 庄骞 (smallchill@163.com)
 */
package com.sms.transfer.station.client;

import java.net.URLEncoder;
import java.security.Key;
import java.time.LocalTime;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * 短信服务工具类
 *
 * @author Chill
 */
public class SmsUtil {

    public static final String PARAM_KEY = "code";
    public static final String SEND_SUCCESS = "短信发送成功";
    public static final String SEND_FAIL = "短信发送失败";
    public static final String VALIDATE_SUCCESS = "短信校验成功";
    public static final String VALIDATE_FAIL = "短信校验失败";
    public static final String ALGORITHM = "PBEWithMD5AndDES";// 加密算法
    /**
     * 定义迭代次数为1000次
     */
    private static final int ITERATIONCOUNT = 1000;

    /**
     * 加密密码字符串
     *
     * @param ciphertext
     *            系统名
     * @param password
     *            密文密码
     * @return 加密后的明文字符串
     * @throws Exception
     */
    public static String getencryptPassWord(String ciphertext, String password) {
        LocalTime time = LocalTime.now();
        String salt = time.toString().substring(0, 2) + ":00:00";
        return encrypt(ciphertext, password, salt);
    }

    /**
     * 加密明文字符串
     *
     * @param plaintext
     *            系统名
     * @param password
     *            明文密码
     * @param salt
     *            盐值
     * @return 加密后的密文字符串
     * @throws Exception
     */
    public static String encrypt(String plaintext, String password, String salt) {

        Key key = getPBEKey(URLEncoder.encode(plaintext));
        byte[] encipheredData = null;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt.getBytes(), ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
            encipheredData = cipher.doFinal(password.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytesToHexString(encipheredData);
    }

    /**
     * 根据PBE密码生成一把密钥
     *
     * @param password
     *            生成密钥时所使用的密码
     * @return Key PBE算法密钥
     */
    private static Key getPBEKey(String password) {
        // 实例化使用的算法
        SecretKeyFactory keyFactory;
        SecretKey secretKey = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            // 设置PBE密钥参数
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            // 生成密钥
            secretKey = keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return secretKey;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param src
     *            字节数组
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
