package com.wj.goods.util;

import com.alibaba.druid.pool.vendor.MockExceptionSorter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    //密码进行加密
    public static String getMd5String(String originStr) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] utf8s = md5.digest(originStr.getBytes("utf8"));

        return null;
    }

}
